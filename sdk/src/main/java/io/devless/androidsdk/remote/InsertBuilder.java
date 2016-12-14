package io.devless.androidsdk.remote;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bubu on 10/3/16.
 */

public class InsertBuilder<T> {
    private String table;
    private List<T> list = new ArrayList<>();

    public InsertBuilder<T> setTable(String table) {
        this.table = table;
        return this;
    }

    public InsertBuilder<T> add(T value) {
        list.add(value);
        return this;
    }


    public RequestBodyTypes.InsertPayload build() {
        RequestBodyTypes.InsertPayload payload = new RequestBodyTypes.InsertPayload();
        RequestBodyTypes.InsertResource<T> r = new RequestBodyTypes.InsertResource<>();
        if (table != null)
            r.setName(this.table);

        r.setInsertObject(list);

        payload.setInsertResource(Collections.<RequestBodyTypes.InsertResource>singletonList(r));

        System.out.println(new Gson().toJson(payload));

        return payload;
    }

}
