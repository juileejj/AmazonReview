package summarization;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import writable.CustomWritable;

import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class SummarizationReducer extends Reducer<Text, CustomWritable, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<CustomWritable> values, Context context) throws IOException, InterruptedException {
        float sum=0;
        float min=5;
        float max=0;
        float count=0;
        for (CustomWritable value : values) {
            if (min >= value.getMin()) {
                min = value.getMin();
            }
            if (max <= value.getMax()) {
                max = value.getMax();
            }
            sum += value.getCount() * value.getAverage();
            count+= value.getCount();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key).append(",");
        stringBuilder.append(Float.toString(sum / count)).append(",");
        stringBuilder.append(Float.toString(max)).append(",");
        stringBuilder.append(Float.toString(min));
        context.write(new Text(stringBuilder.toString()), NullWritable.get());
    }
}
