package DistinctCategory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import topten.TopTenMapper;

import java.io.IOException;

/**
 * Created by hadoop on 4/23/17.
 */
public class DistinctCategory {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

            Configuration conf = new Configuration();
            Job job1 = Job.getInstance(conf, "Distinct");
            job1.setJarByClass(DistinctCategory.class);
            job1.setMapperClass(DistinctMapper.class);
            job1.setReducerClass(DistinctReducer.class);
            job1.setCombinerClass(DistinctReducer.class);
            job1.setMapOutputKeyClass(Text.class);
            job1.setMapOutputValueClass(LongWritable.class);
            job1.setOutputKeyClass(Text.class);
            job1.setOutputValueClass(LongWritable.class);
            FileInputFormat.addInputPath(job1, new Path(args[0]));
            FileOutputFormat.setOutputPath(job1, new Path(args[1]));
            boolean complete = job1.waitForCompletion(true);

            Configuration conf2 = new Configuration();
            if(complete)
            {
                Job job2 = Job.getInstance(conf2,"top ten");
                job2.setJarByClass(DistinctCategory.class);
                job2.setMapperClass(TopTenMapper.class);
                job2.setMapOutputKeyClass(NullWritable.class);
                job2.setMapOutputValueClass(Text.class);
                job2.setNumReduceTasks(0);
                FileInputFormat.addInputPath(job2,new Path(args[1]));
                FileOutputFormat.setOutputPath(job2, new Path(args[2]));
                System.exit(job2.waitForCompletion(true)? 0 :1);
        }

    }
}
