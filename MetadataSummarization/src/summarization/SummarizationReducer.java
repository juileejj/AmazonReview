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
    CustomWritable result = new CustomWritable();
    @Override
    protected void reduce(Text key, Iterable<CustomWritable> values, Context context) throws IOException, InterruptedException {
        float sum=0;
        float count=0;
        float minimum=5;
        float maximum=0;
        for (CustomWritable value : values) {
            if (minimum >= value.getMinimumRating()) {
                minimum = value.getMinimumRating();
            }
            if (maximum <= value.getMaximumRating()) {
                maximum = value.getMaximumRating();
            }
            sum += value.getReviewCount() * value.getAverageRating();
            count+= value.getReviewCount();
        }

        result.setAverageRating(sum/count);
        result.setMaximumRating(maximum);
        result.setMinimumRating(minimum);
        result.setReviewCount(((int) count));

        StringBuilder builder = new StringBuilder();
        builder.append(key).append(",");
        builder.append(Float.toString(sum / count)).append(",");
        builder.append(Float.toString(maximum)).append(",");
        builder.append(Float.toString(minimum));
        context.write(new Text(builder.toString()), NullWritable.get());
    }
}
