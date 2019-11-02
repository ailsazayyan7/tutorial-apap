package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsDetail {

    @JsonProperty("results")
    private List<RecipeDetail> resultDetailsList;

    public List<RecipeDetail> getResultDetailsList() {
        return resultDetailsList;
    }

    public void setResultDetailsList(List<RecipeDetail> resultDetailsList) {
        this.resultDetailsList = resultDetailsList;
    }
}
