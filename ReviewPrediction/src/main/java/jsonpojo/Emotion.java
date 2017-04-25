package jsonpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hadoop on 4/24/17.
 */
public class Emotion {
    @SerializedName("anger")
    @Expose
    private Double anger;
    @SerializedName("disgust")
    @Expose
    private Double disgust;
    @SerializedName("fear")
    @Expose
    private Double fear;
    @SerializedName("joy")
    @Expose
    private Double joy;
    @SerializedName("sadness")
    @Expose
    private Double sadness;

    public Double getAnger() {
        return anger;
    }

    public void setAnger(Double anger) {
        this.anger = anger;
    }

    public Double getDisgust() {
        return disgust;
    }

    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }

    public Double getFear() {
        return fear;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public Double getJoy() {
        return joy;
    }

    public void setJoy(Double joy) {
        this.joy = joy;
    }

    public Double getSadness() {
        return sadness;
    }

    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }
}
