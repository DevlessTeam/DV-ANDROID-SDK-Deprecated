package io.devless.androidsdk.remote;

import android.support.v4.util.Pair;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bubu on 10/3/16.
 */

public class UpdateBuilder {
    String table;
    Pair<String, String> where;
    List<Pair<String, String>> setList;

    public UpdateBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    public UpdateBuilder where(String key, String value) {
        where = new Pair<>(key, value);
        return this;
    }

    public UpdateBuilder set(String key, String value) {
        if (setList == null)
            setList = new ArrayList<>();
        setList.add(new Pair<>(key, value));
        return this;
    }

    public RequestBodyTypes.UpdatePayload build() {
        RequestBodyTypes.UpdatePayload payload = new RequestBodyTypes.UpdatePayload();
        RequestBodyTypes.UpdateResource r = new RequestBodyTypes.UpdateResource();
        if (table != null)
            r.setName(this.table);

        ArrayList<RequestBodyTypes.UpdateParam> updateParams = new ArrayList<>();
        RequestBodyTypes.UpdateParam p = new RequestBodyTypes.UpdateParam();
        p.setWhere(where.first + "," + where.second);

        ArrayList<Map<String, String>> dts = new ArrayList<>();
        if (setList != null)
            for (Pair<String, String> each : setList) {
                Map<String, String> d = new HashMap<>();
                d.put(each.first, each.second);
                dts.add(d);
            }

        p.setData(dts);

        updateParams.add(p);
        r.setParams(updateParams);
        payload.setUpdateResource(Arrays.asList(r));

        System.out.println(new Gson().toJson(payload));

        return payload;
    }

}
