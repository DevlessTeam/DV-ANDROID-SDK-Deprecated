package io.devless.androidsdk.remote;

import android.support.v4.util.Pair;

import com.google.gson.Gson;

import java.util.Collections;

/**
 * Created by bubu on 10/3/16.
 */

public class DeleteBuilder {
    private String table;
    private Pair<String, String> where;

    public DeleteBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    public DeleteBuilder where(String key, String value) {
        where = new Pair<>(key, value);
        return this;
    }

    public RequestBodyTypes.DeletePayload build() {
        RequestBodyTypes.DeletePayload payload = new RequestBodyTypes.DeletePayload();
        RequestBodyTypes.DeleteResource r = new RequestBodyTypes.DeleteResource();
        if (table != null)
            r.setName(this.table);


        RequestBodyTypes.DeleteParam p = new RequestBodyTypes.DeleteParam();
        p.setWhere(where.first + "," + where.second);
        p.setDelete(true);

        r.setDeleteParams(Collections.singletonList(p));
        payload.setDeleteResource(Collections.singletonList(r));

        System.out.println(new Gson().toJson(payload));

        return payload;
    }

}
