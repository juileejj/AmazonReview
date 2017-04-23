package DistinctCategory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by hadoop on 4/23/17.
 */
public class DistinctCategory {

    public static void main(String args[])
    {
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "Rating Partitioning Pattern");
            job.setJarByClass(DistinctCategory.class);
            job.setMapperClass(DistinctMapper.class);
            job.setReducerClass(DistinctReducer.class);
            job.setCombinerClass(DistinctReducer.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
