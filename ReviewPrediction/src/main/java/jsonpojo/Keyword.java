package jsonpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hadoop on 4/24/17.
 */
public class Keyword {
    @SerializedName("relevance")
    @Expose
    private Double relevance;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("emotion")
    @Expose
    private Emotion emotion;
    @SerializedName("sentiment")
    @Expose
    private Sentiment sentiment;

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }
}
