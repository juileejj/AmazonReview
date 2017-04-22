package category_partition;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/**
 * Created by hadoop on 4/22/17.
 */
public class PartitionPartitioner extends Partitioner<DoubleWritable,Text> implements Configurable{

    private Configuration conf=null;
    @Override
    public int getPartition(DoubleWritable key, Text value, int i) {
      int partition = (int) key.get()-1;
        return partition;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }


}
