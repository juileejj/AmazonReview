package category_partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by hadoop on 4/21/17.
 */
public class PartitionReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      for(Text v : values) {
          context.write(key, v);
      }
    }
}
