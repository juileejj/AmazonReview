package MahoutUserBased;

import com.google.gson.Gson;
import jsonpojo.JsonReview;
import org.apache.commons.csv.CSVParser;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 4/25/17.
 */
public class MahoutUserBased {

    static UserBasedRecommender recommender = null;
    static  MemoryIDMigrator thing2long = new MemoryIDMigrator();
    static String DATA_FILE_NAME ="/home/hadoop/Juilee/Project/AmazonReview/MahoutUserBased/input/reviews.json";

    static void initRecommender()
    {
          DataModel dataModel;
        try {

            Map<Long,List<Preference>> preferecesOfUsers = new HashMap<Long,List<Preference>>();
            BufferedReader br = br = new BufferedReader(new FileReader(DATA_FILE_NAME));
            String line = "";
            Gson gson = new Gson();

            while((line = br.readLine()) != null) {
                List<Preference> userPrefList;
                JsonReview jsonReview = gson.fromJson(line.toString(), JsonReview.class);
                //System.out.print(jsonReview.getReviewerID());
                String person = jsonReview.getReviewerID();
                String likeName = jsonReview.getAsin();

                long userLong = thing2long.toLongID(person);
                thing2long.storeMapping(userLong, person);

                long itemLong = thing2long.toLongID(likeName);
                thing2long.storeMapping(itemLong, likeName);

                if((userPrefList = preferecesOfUsers.get(userLong)) == null) {
                    userPrefList = new ArrayList<Preference>();
                    preferecesOfUsers.put(userLong, userPrefList);
                }
                float prefValue = Float.parseFloat(jsonReview.getOverall());
                userPrefList.add(new GenericPreference(userLong, itemLong, prefValue));
            }


            FastByIDMap<PreferenceArray> preferecesOfUsersFastMap = new FastByIDMap<PreferenceArray>();
            for(Map.Entry<Long, List<Preference>> entry : preferecesOfUsers.entrySet()) {
                preferecesOfUsersFastMap.put(entry.getKey(), new GenericUserPreferenceArray(entry.getValue()));
            }

            dataModel = new GenericDataModel(preferecesOfUsersFastMap);
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.9, similarity, dataModel);
            recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] recommendThings(String personName) throws TasteException {
        List<String> recommendations = new ArrayList<String>();
        try {

            List<RecommendedItem> items = recommender.recommend(thing2long.toLongID(personName), 10);
            for(RecommendedItem item : items) {
                recommendations.add(thing2long.toStringID(item.getItemID()));
            }
        } catch (TasteException e) {
            throw e;
        }
        return recommendations.toArray(new String[recommendations.size()]);
    }
    public static void main(String args[])
    {
        System.out.println("");
        MahoutUserBased recommendation = new MahoutUserBased();
        recommendation.initRecommender();
        try {
            for (String result : recommendation.recommendThings("A2XVJBSRI3SWDI")) {
                System.out.println(result);
            }
        } catch (TasteException e) {
            e.printStackTrace();
        }
    }
}
