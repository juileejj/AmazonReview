package PriceBinning;

import com.google.gson.Gson;
import jsonpojo.JsonMetadata;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class BinningMapper extends Mapper<Object, Text, Text, NullWritable> {
    private Gson gson;
    private MultipleOutputs<Text, NullWritable> multipleOutputs;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        multipleOutputs = new MultipleOutputs<>(context);
    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        gson = new Gson();
        JsonMetadata metadata = gson.fromJson(value.toString(), JsonMetadata.class);
        Double price = 0.00;
        if (metadata.getPrice() != null) {
            price = Double.parseDouble(metadata.getPrice());
        }
        if (price > Double.parseDouble("0")) {
            if (price <= Double.parseDouble("6.00")) {
                multipleOutputs.write("bins", value, NullWritable.get(), "< $6");
            } else if (price <= Double.parseDouble("12.00")) {
                multipleOutputs.write("bins", value, NullWritable.get(), "< $12");
            } else if (price <= Double.parseDouble("25.00")) {
                multipleOutputs.write("bins", value, NullWritable.get(), "< $25");
            } else if (price <= Double.parseDouble("50.00")) {
                multipleOutputs.write("bins", value, NullWritable.get(), "< $50");
            } else {
                multipleOutputs.write("bins", value, NullWritable.get(), "> $100");
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        multipleOutputs.close();
    }
}
