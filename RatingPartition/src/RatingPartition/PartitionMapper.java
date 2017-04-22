package RatingPartition;

import com.google.gson.Gson;
import jsonpojo.JsonReview;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by hadoop on 4/21/17.
 */
public class PartitionMapper extends Mapper<Object,Text,DoubleWritable,Text> {
public void map(Object key , Text value ,Context context) throws IOException, InterruptedException {
    Gson gson = new Gson();
    JsonReview reviewDetails = gson.fromJson(value.toString(), JsonReview.class);
   /* System.out.println(reviewDetails);*/
    context.write(new DoubleWritable(Double.parseDouble(reviewDetails.getOverall())), new Text(reviewDetails.getAsin()));
}
}
