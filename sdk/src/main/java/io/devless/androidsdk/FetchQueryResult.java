package io.devless.androidsdk;

/**
 * Created by bubu on 10/3/16.
 */


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchQueryResult<T> {

    @SerializedName("status_code")

    private Integer statusCode;
    @SerializedName("message")

    private String message;
    @SerializedName("payload")

    private Payload<T> payload;

    /**
     * @return The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode The status_code
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The payload
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     * @param payload The payload
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }


    public class Payload<T> {

        @SerializedName("properties")

        private Properties properties;
        @SerializedName("results")

        private List<T> results = null;

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
        public List<T> getResults() {
            return results;
        }

        /**
         * @param results The results
         */
        public void setResults(List<T> results) {
            this.results = results;
        }

    }


    public class Properties {

        @SerializedName("count")

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
