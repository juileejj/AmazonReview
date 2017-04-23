package DistinctCategory;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by hadoop on 4/21/17.
 */
public class DistinctReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long value =0;
        for(LongWritable val:values)
        {
            value+=val.get();
        }
        context.write(key, new LongWritable(value));
    }
}
