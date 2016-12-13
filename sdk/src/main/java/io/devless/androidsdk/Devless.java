package io.devless.androidsdk;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import io.devless.androidsdk.remote.ApiCalls;
import io.devless.androidsdk.remote.RequestBodyTypes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bubu on 10/3/16.
 */

public class Devless {
    private ApiCalls apiCalls;

    private static final int UPDATE_SUCCESS = 619;
    private static final int DELETE_SUCCESS = 636;


    private static Devless devless;

    private Devless(String url, String apiKey) {
        apiCalls = ApiCalls.init(url, apiKey);
    }

    public static void initialize(String url, String apiKey) {
        if (devless == null)
            devless = new Devless(url, apiKey);
        else throw new RuntimeException("Devless instance has already been instantiated");
    }

    public static Devless get() {
        if (devless == null)
            throw new RuntimeException("Devless instance has not been instantiated");
        else return devless;
    }


    public <T> List<T> getData(String service, String table, final Class<T[]> cls, final Util.DevlessCallback callback) {
        apiCalls.getData(service, table)
                .enqueue(new Callback<FetchQueryResult>() {
                    @Override
                    public void onResponse(Call<FetchQueryResult> call, Response<FetchQueryResult> response) {

                        if (response.isSuccessful()) {
//                                FetchQueryResult fetchQueryResult = new Gson().fromJson(response.body().string(), FetchQueryResult.class);
                            FetchQueryResult fetchQueryResult = response.body();
                            callback.onResult(fetchQueryResult);
                            callback.onSuccess(Util.stringToArray(fetchQueryResult.getPayload().getResults().toString(), cls));
//                                callback.onSuccess(fetchQueryResult.getPayload().getResults());

                        } else {
                            JsonObject errorBody;
                            try {
                                errorBody = new JsonParser()
                                        .parse(response.errorBody().string())
                                        .getAsJsonObject();
                                callback.onError(new Throwable(errorBody.getAsString()));
                            } catch (IOException ignored) {
                                callback.onError(ignored);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<FetchQueryResult> call, Throwable t) {
                        callback.onError(t);
                    }
                });
        return null;
    }

    public void update(String service, RequestBodyTypes.UpdatePayload payload, final Util.DevlessQueryCallback callback) {
        // payload resource is an array, it is supposed to be an object but it is not scheduled for fixing in the mean time
        apiCalls.update(service, payload.getUpdateResource().get(0).getName(), payload)
                .enqueue(responseCallback(UPDATE_SUCCESS, callback));
    }

    public void delete(String service, RequestBodyTypes.DeletePayload payload, final Util.DevlessQueryCallback callback) {
        // payload resource is an array, it is supposed to be an object but it is not scheduled for fixing in the mean time
        apiCalls.delete(service, payload.getDeleteResource().get(0).getName(), payload)
                .enqueue(responseCallback(DELETE_SUCCESS, callback));
    }

    private Callback<ResponseBody> responseCallback(final int success_code, final Util.DevlessQueryCallback callback) {

        return new Callback<ResponseBody>() {

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t);
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        QueryResult qr = new Gson().fromJson(res, QueryResult.class);

                        if (qr.getStatusCode() == success_code) {
                            callback.onSuccess(qr);
                        } else if (qr.getStatusCode() == 700) {
                            callback.onFail(new Gson().fromJson(res, ErrorResult.class));
                        } else {
                            callback.onFail(new ErrorResult().setMessage(qr.getMessage()).setStatusCode(qr.getStatusCode()));
                        }

                    } catch (IOException e) {
                        callback.onError(e);
                    }

                } else {
                    JsonObject errorBody;
                    try {
                        System.out.println(response.errorBody().string());
                        errorBody = new JsonParser()
                                .parse(response.errorBody().string())
                                .getAsJsonObject();
                        callback.onError(new Throwable(errorBody.getAsString()));
                    } catch (IOException ignored) {
                        callback.onError(ignored);
                    }
                }
            }
        };
    }
}
