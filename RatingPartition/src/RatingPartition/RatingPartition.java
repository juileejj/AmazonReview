package RatingPartition;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class RatingPartition {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "Rating Partitioning Pattern");
            job.setJarByClass(RatingPartition.class);
            job.setMapperClass(PartitionMapper.class);
            job.setReducerClass(PartitionReducer.class);
            job.setPartitionerClass(PartitionPartitioner.class);
            job.setMapOutputKeyClass(DoubleWritable.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(DoubleWritable.class);
            job.setOutputValueClass(Text.class);
            job.setNumReduceTasks(5);
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
