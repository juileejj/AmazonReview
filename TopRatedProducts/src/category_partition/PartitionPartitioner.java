package category_partition;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by hadoop on 4/22/17.
 */
public class PartitionPartitioner extends Partitioner<Text,Text> implements Configurable{

    private Configuration conf=null;
    private ArrayList<String> categoryList=null;

    @Override
    public int getPartition(Text key, Text value, int i) {
        int partition=10;
            if(categoryList.contains(key.toString()))
            {
              partition=categoryList.indexOf(key.toString());
            }
        return partition;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
        try {
            getCategoryCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    private ArrayList<String> getCategoryCache() throws IOException {

        try {
            Path[] files = DistributedCache.getLocalCacheFiles(getConf());
            //System.out.println(files.toString());
            categoryList = new ArrayList<>();
            if (files != null && files.length > 0) {
                for (Path file : files) {
                    try {
                        File myFile = new File(file.toUri());
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile.toString()));
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                           categoryList.add(line.trim());
                        }
                    } catch (IOException ex) {
                        System.err.println("Exception while reading  file: " + ex.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Exception in mapper setup: " + ex.getMessage());
        }
return categoryList;
    }

}
