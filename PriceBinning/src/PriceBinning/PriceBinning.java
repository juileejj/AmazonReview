package PriceBinning;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hadoop on 4/23/17.
 */
public class PriceBinning {

    public static void main(String args[])
    {
        Configuration configuration = new Configuration();
        try {
            Job job = Job.getInstance(configuration, "Price wise binning job");
            job.setJarByClass(PriceBinning.class);

            job.setMapperClass(BinningMapper.class);

            job.setNumReduceTasks(0);

            MultipleOutputs.addNamedOutput(job, "bins",
                    TextOutputFormat.class,
                    Text.class,
                    NullWritable.class);

            MultipleOutputs.setCountersEnabled(job, true);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(PriceBinning.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

