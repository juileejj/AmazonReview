package jsonpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hadoop on 4/24/17.
 */
public class MySentimental {
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("entities")
    @Expose
    private List<Object> entities = null;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Object> getEntities() {
        return entities;
    }

    public void setEntities(List<Object> entities) {
        this.entities = entities;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

}
