package jsonpojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hadoop on 4/21/17.
 */
public class JsonMetadata {
    private String asin;
    private String title;
    private String price;
    private String imUrl;
    private JsonMetadataDetails related;
    private Map<String, String> salesRank;
    private String brand;
    private List<List<String>> categories;

    public JsonMetadata() {
        salesRank = new HashMap<String, String>();
        categories = new ArrayList<List<String>>();
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public JsonMetadataDetails getRelated() {
        return related;
    }

    public void setRelated(JsonMetadataDetails related) {
        this.related = related;
    }

    public Map<String, String> getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(Map<String, String> salesRank) {
        this.salesRank = salesRank;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<List<String>> getCategories() {
        return categories;
    }

    public void setCategories(List<List<String>> categories) {
        this.categories = categories;
    }
}
