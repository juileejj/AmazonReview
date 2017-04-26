package ReviewPrediction;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import jsonpojo.JsonReview;
import jsonpojo.Keyword;
import jsonpojo.MySentimental;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 4/23/17.
 */
public class ReviewPrediction {
    public static File file = new File("/home/hadoop/Juilee/Project/AmazonReview/ReviewPrediction/input/sentiment_out.json");
    public static File plotlyFile = new File("/home/hadoop/Juilee/Project/AmazonReview/ReviewPrediction/input/plotly_out.csv");

    static Integer count =0;
    static void parseFile() throws Exception {

        String jsonFile = "/home/hadoop/Juilee/Project/AmazonReview/ReviewPrediction/input/sentiment_input";
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


    static void getSentimentalResullts(String text) throws IOException {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "0cc6b80d-621a-4450-936c-4e941b72db94",
                "6SpQDjAmQKVT");
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

        Map<Integer, Double> integerDoubleMap = extractSentimentToFile(response.toString());
        if (!plotlyFile.exists()) {
            plotlyFile.createNewFile();
        }
        FileWriter plotlyFileWriter = new FileWriter(plotlyFile.getAbsoluteFile(),true);
        for (Map.Entry<Integer, Double> entry : integerDoubleMap.entrySet()) {
            plotlyFileWriter.append(entry.getKey().toString());
            plotlyFileWriter.append(",");
            plotlyFileWriter.append(entry.getValue().toString());
            plotlyFileWriter.append("\n");
        }
        plotlyFileWriter.close();
        System.out.println(response);
    }

     static Map<Integer, Double> extractSentimentToFile(String inputToWrite) throws IOException {
        Gson gson = new Gson();
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        MySentimental toWrite = gson.fromJson(inputToWrite, MySentimental.class);
        Map<Integer, Double> toPlotly = new HashMap<Integer, Double>();
        List<Keyword> keywords = toWrite.getKeywords();
        for (Keyword sample : keywords) {
            count ++;
            Double score = sample.getSentiment().getScore();
            toPlotly.put(count, score);
        }
        fw.append(gson.toJson(toWrite));
        fw.close();

        return toPlotly;
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
