package io.devless.android.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bubu on 10/3/16.
 */

public class Result {

    @SerializedName("properties")
    private Properties properties;
    @SerializedName("results")
    private List<Object> results = new ArrayList<Object>();

    /**
     * @return The properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties The properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @return The results
     */
    public List<Object> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Object> results) {
        this.results = results;
    }


    private class Properties {

        @SerializedName("count")
        @Expose
        private Integer count;

        /**
         * @return The count
         */
        public Integer getCount() {
            return count;
        }

        /**
         * @param count The count
         */
        public void setCount(Integer count) {
            this.count = count;
        }

    }
}
