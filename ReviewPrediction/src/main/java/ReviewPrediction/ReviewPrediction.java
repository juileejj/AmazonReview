package ReviewPrediction;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import jsonpojo.JsonReview;
import jsonpojo.MySentimental;
import java.io.*;

/**
 * Created by hadoop on 4/23/17.
 */
public class ReviewPrediction {
    public static File file = new File("/home/hadoop/Juilee/Project/AmazonReview/ReviewPrediction/input/sentiment_out.json");


    static void parseFile() throws Exception {

        String jsonFile = "/home/hadoop/Juilee/Project/AmazonReview/ReviewPrediction/input/sentiment_in";
        BufferedReader br = null;
        String line = "";
        Gson gson = new Gson();

        try {
            br = new BufferedReader(new FileReader(jsonFile));
            while ((line = br.readLine()) != null) {

                JsonReview jsonReview = gson.fromJson(line.toString(), JsonReview.class);
               getSentimentalResullts(jsonReview.getReviewText());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void getSentimentalResullts(String text) throws IOException {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "0cc6b80d-621a-4450-936c-4e941b72db94",
                "6SpQDjAmQKVT");

        //String text = "Perfect red tutu for the price. I baught it as part of my daughters Halloween costume and it looked great on her";

        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(2)
                .build();

        Features features = new Features.Builder()
                .entities(entitiesOptions)
                .keywords(keywordsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();

        extractSentimentToFile(response.toString());
        System.out.println(response);
    }

    public static void extractSentimentToFile(String inputToWrite) throws IOException {
        Gson gson = new Gson();
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        MySentimental toWrite = gson.fromJson(inputToWrite, MySentimental.class);
        fw.append(gson.toJson(toWrite));
        fw.close();
    }

    public static void main(String args[])
    {
        try {
            parseFile();
           // extractSentimentToFile("{\"language\":\"en\",\"entities\":[],\"keywords\":[{\"relevance\":0.951141,\"text\":\"Perfect red tutu\",\"emotion\":{\"anger\":0.09151,\"disgust\":0.035575,\"fear\":0.005118,\"joy\":0.615381,\"sadness\":0.105576},\"sentiment\":{\"score\":0.864112}},{\"relevance\":0.720576,\"text\":\"Halloween costume\",\"emotion\":{\"anger\":0.020421,\"disgust\":0.047749,\"fear\":0.055691,\"joy\":0.684762,\"sadness\":0.068274},\"sentiment\":{\"score\":0.660991}}]}");
           // getSentimentalResullts("Perfect red tutu for the price. I baught it as part of my daughters Halloween costume and it looked great on her");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
