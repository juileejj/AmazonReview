package ReviewCSV;

import com.google.gson.Gson;
import jsonpojo.JsonReview;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by hadoop on 4/23/17.
 */
public class CSVMapper extends Mapper<Object,Text,NullWritable,Text> {
    @Override
    protected void map(Object key, Text value,Context context) throws IOException, InterruptedException {
        Gson gson = new Gson();
        JsonReview reviewDetails = gson.fromJson(value.toString(), JsonReview.class);
        StringBuilder review = new StringBuilder();
        review.append(reviewDetails.getAsin()).append(",");
        review.append(reviewDetails.getOverall()).append(",");
        review.append(reviewDetails.getReviewText());
        context.write(NullWritable.get(),new Text(review.toString()));
    }
}
