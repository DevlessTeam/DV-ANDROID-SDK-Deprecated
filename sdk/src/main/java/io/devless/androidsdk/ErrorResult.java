package io.devless.androidsdk;

/**
 * Created by bubu on 12/13/16.
 */

import com.google.gson.annotations.SerializedName;

public class ErrorResult extends QueryResult {


    public ErrorResult setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ErrorResult setMessage(String message) {
        this.message = message;
        return this;
    }

    @SerializedName("payload")

    private Payload payload;

    /**
     * @return The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }


    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }


    /**
     * @return The payload
     */
    public Payload getPayload() {
        return payload;
    }


    public class Payload {

        @SerializedName("file")

        private String file;
        @SerializedName("line")

        private Integer line;

        /**
         * @return The file
         */
        public String getFile() {
            return file;
        }

        /**
         * @return The line
         */
        public Integer getLine() {
            return line;
        }


    }
}
