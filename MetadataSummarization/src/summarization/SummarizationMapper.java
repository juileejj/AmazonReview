package summarization;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jsonpojo.JsonReview;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import writable.CustomWritable;

import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class SummarizationMapper extends Mapper<Object, Text, Text, CustomWritable> {
    private Gson gson;

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        gson = new Gson();
        try {
            JsonReview fromRow = gson.fromJson(value.toString(), JsonReview.class);
            System.out.println(fromRow);
            Text asin = new Text(fromRow.getAsin());
            System.out.println();
            Float rating = Float.parseFloat(fromRow.getOverall());
            CustomWritable valueToEmit = new CustomWritable(1, rating, rating, rating);
            context.write(asin, valueToEmit);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
