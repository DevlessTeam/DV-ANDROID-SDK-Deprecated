package io.devless.androidsdk;

/**
 * Created by bubu on 12/13/16.
 */

import com.google.gson.annotations.SerializedName;

public class QueryResult {

    public QueryResult setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public QueryResult setMessage(String message) {
        this.message = message;
        return this;
    }

    @SerializedName("status_code")

    protected Integer statusCode;
    @SerializedName("message")

    protected String message;

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

}
