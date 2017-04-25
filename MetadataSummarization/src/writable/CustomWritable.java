package writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by hadoop on 4/24/17.
 */
public class CustomWritable implements Writable {
    Integer reviewCount;
    Float averageRating;
    Float minimumRating;
    Float maximumRating;

    public CustomWritable() {
    }

    public CustomWritable(Integer reviewCount, Float averageRating) {
        this.reviewCount = reviewCount;
        this.averageRating = averageRating;
    }

    public CustomWritable(Integer reviewCount, Float averageRating, Float minimumRating, Float maximumRating) {
        this.reviewCount = reviewCount;
        this.averageRating = averageRating;
        this.minimumRating = minimumRating;
        this.maximumRating = maximumRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public Float getMinimumRating() {
        return minimumRating;
    }

    public void setMinimumRating(Float minimumRating) {
        this.minimumRating = minimumRating;
    }

    public Float getMaximumRating() {
        return maximumRating;
    }

    public void setMaximumRating(Float maximumRating) {
        this.maximumRating = maximumRating;
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "reviewCount=" + reviewCount +
                ", averageRating=" + averageRating +
                ", minimumRating=" + minimumRating +
                ", maximumRating=" + maximumRating +
                '}';
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(reviewCount);
        dataOutput.writeFloat(averageRating);
        dataOutput.writeFloat(minimumRating);
        dataOutput.writeFloat(maximumRating);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        reviewCount = dataInput.readInt();
        averageRating = dataInput.readFloat();
        minimumRating = dataInput.readFloat();
        maximumRating = dataInput.readFloat();
    }

}
