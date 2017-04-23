package DistinctCategory;

import com.google.gson.Gson;
import jsonpojo.JsonMetadata;
import jsonpojo.JsonReview;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by hadoop on 4/21/17.
 */
public class DistinctMapper extends Mapper<Object,Text,Text,NullWritable> {
public void map(Object key , Text value ,Context context) throws IOException, InterruptedException {
    Gson gson = new Gson();
    JsonMetadata metadata = gson.fromJson(value.toString(), JsonMetadata.class);
   /* System.out.println(reviewDetails);*/
    List<List<String>> categories = metadata.getCategories();
    for(List<String> list:categories)
    {
        for(String category:list)
        {
            context.write(new Text(category.trim()), NullWritable.get());
}
    }
            }
}
