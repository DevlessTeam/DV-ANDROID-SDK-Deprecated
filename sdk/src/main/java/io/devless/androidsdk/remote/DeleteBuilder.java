package io.devless.androidsdk.remote;

import android.support.v4.util.Pair;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bubu on 10/3/16.
 */

public class DeleteBuilder {
    String table;
    Pair<String, String> where;
    List<Pair<String, String>> setList;

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


        ArrayList<RequestBodyTypes.DeleteParam> deleteParams = new ArrayList<>();
        RequestBodyTypes.DeleteParam p = new RequestBodyTypes.DeleteParam();
        p.setWhere(where.first + "," + where.second);
        p.setDelete(true);


        deleteParams.add(p);
        r.setDeleteParams(Arrays.asList(p));
        payload.setDeleteResource(Arrays.asList(r));

        System.out.println(new Gson().toJson(payload));

        return payload;
    }

}
