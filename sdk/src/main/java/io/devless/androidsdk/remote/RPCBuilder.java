package io.devless.androidsdk.remote;

import com.google.gson.Gson;

/**
 * Created by bubu on 10/3/16.
 */

public class RPCBuilder<T> {
    private String service;
    private String method;
    private RequestBodyTypes.RPCPayload<T> payload;

    public RPCBuilder setService(String service) {
        this.service = service;
        return this;
    }

    public RPCBuilder setMethod(String method) {
        this.method = method;
        return this;
    }

    public RPCBuilder setPayload(T t) {
        this.payload = new RequestBodyTypes.RPCPayload<>();
        payload.setT(t);
        return this;
    }

    public String getService() {
        return service;
    }

    public String getMethod() {
        return method;
    }

    public RequestBodyTypes.RPCPayload<T> getPayload() {
        return payload;
    }

    public RPCBuilder<T> build() {
        RequestBodyTypes.RPCPayload<T> payload = new RequestBodyTypes.RPCPayload<>();
        int id = (int) (Math.random() * 1000000000 + 10000000);
        payload.setId(id);

        System.out.println(new Gson().toJson(payload));

        return this;
    }

}
