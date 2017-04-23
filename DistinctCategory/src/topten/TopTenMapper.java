package topten;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by hadoop on 4/23/17.
 */
public class TopTenMapper extends Mapper<Object,Text,NullWritable,Text> {

    private TreeMap<Integer,Text> recordMap = new TreeMap<>();
    protected void map(Object key, Text value, Context context)
    {
        try {
            String[] valueArray = value.toString().split("\t");
            String category = valueArray[0];
            String count = valueArray[1];
            recordMap.put(Integer.parseInt(count), new Text(category));
            if (recordMap.size() > 10) {
                recordMap.remove(recordMap.firstKey());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for(Text text:recordMap.values())
        {
            context.write(NullWritable.get(),text);
        }
    }
}
