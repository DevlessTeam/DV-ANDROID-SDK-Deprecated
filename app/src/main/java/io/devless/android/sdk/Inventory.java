package io.devless.android.sdk;

/**
 * Created by bubu on 12/13/16.
 */

import com.google.gson.annotations.SerializedName;

public class Inventory {

    @SerializedName("id")
    
    private String id;
    @SerializedName("devless_user_id")
    
    private String devlessUserId;
    @SerializedName("name")
    
    private String name;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The devlessUserId
     */
    public String getDevlessUserId() {
        return devlessUserId;
    }

    /**
     * @param devlessUserId The devless_user_id
     */
    public void setDevlessUserId(String devlessUserId) {
        this.devlessUserId = devlessUserId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }


}
