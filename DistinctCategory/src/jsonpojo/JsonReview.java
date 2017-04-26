package jsonpojo;

import java.util.List;

/**
 * Created by hadoop on 4/22/17.
 */
public class JsonReview {
    private String reviewerId;
    private String asin;
    private String reviewerName;
    private String reviewText;
    private String overall;
    private String summary;
    private String unixReviewTime;
    private String reviewTime;
    private List<String> helpful;

    public void setOverall(String overall) {
        this.overall = overall;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUnixReviewTime() {
        return unixReviewTime;
    }

    public void setUnixReviewTime(String unixReviewTime) {
        this.unixReviewTime = unixReviewTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public List<String> getHelpful() {
        return helpful;
    }

    public void setHelpful(List<String> helpful) {
        this.helpful = helpful;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getOverall() {
        return overall;
    }
}
