package ReviewCSV;

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
public class ReviewCSV {
     public static void main(String args[])
     {
          try {
               Configuration conf = new Configuration();
               Job job = Job.getInstance(conf, "Review CSV");
               job.setJarByClass(ReviewCSV.class);
               job.setMapperClass(CSVMapper.class);
               job.setMapOutputKeyClass(NullWritable.class);
               job.setMapOutputValueClass(Text.class);
               job.setNumReduceTasks(0);
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
