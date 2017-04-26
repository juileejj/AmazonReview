package writable;

import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class CustomWritable implements Writable {
  private  Integer count;
  private  Float average;
  private  Float min;
  private  Float max;


    public Integer getCount() {
        return count;
    }

    public Float getAverage() {
        return average;
    }

    public Float getMin() {
        return min;
    }

    public Float getMax() {
        return max;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(count);
        dataOutput.writeFloat(average);
        dataOutput.writeFloat(min);
        dataOutput.writeFloat(max);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        count = dataInput.readInt();
        average = dataInput.readFloat();
        min = dataInput.readFloat();
        max = dataInput.readFloat();
    }

    public CustomWritable(Integer count, Float average) {
        this.count = count;
        this.average = average;
    }

    public CustomWritable(Integer count, Float average, Float min, Float max) {
        this.count = count;
        this.average = average;
        this.min = min;
        this.max = max;
    }

}
