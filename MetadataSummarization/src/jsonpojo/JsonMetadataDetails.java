package jsonpojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 4/21/17.
 */
public class JsonMetadataDetails {
    private List<String> also_bought;
    private List<String> also_viewed;
    private List<String> bought_together;
    private List<String> buy_after_viewing;

    public JsonMetadataDetails() {
        this.also_bought = new ArrayList<String>();
        this.also_viewed = new ArrayList<String>();
        this.bought_together = new ArrayList<String>();
        this.buy_after_viewing = new ArrayList<String>();
    }

    public List<String> getAlso_bought() {
        return also_bought;
    }

    public void setAlso_bought(List<String> also_bought) {
        this.also_bought = also_bought;
    }

    public List<String> getAlso_viewed() {
        return also_viewed;
    }

    public void setAlso_viewed(List<String> also_viewed) {
        this.also_viewed = also_viewed;
    }

    public List<String> getBought_together() {
        return bought_together;
    }

    public void setBought_together(List<String> bought_together) {
        this.bought_together = bought_together;
    }

    public List<String> getBuy_after_viewing() {
        return buy_after_viewing;
    }

    public void setBuy_after_viewing(List<String> buy_after_viewing) {
        this.buy_after_viewing = buy_after_viewing;
    }
}
