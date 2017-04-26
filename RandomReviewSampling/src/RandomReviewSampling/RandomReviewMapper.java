package RandomReviewSampling;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class RandomReviewMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    private int randomThreshHold = 0;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        randomThreshHold++;
        if (randomThreshHold < 115) {
            if (randomThreshHold % 2 == 0) {
                context.write(NullWritable.get(), value);
            }
        }
    }
}
