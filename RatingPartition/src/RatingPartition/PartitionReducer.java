package RatingPartition;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by hadoop on 4/21/17.
 */
public class PartitionReducer extends Reducer<DoubleWritable, Text, NullWritable, Text> {
    public void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      for(Text v : values) {
          context.write(NullWritable.get(), v);
      }
    }
}
