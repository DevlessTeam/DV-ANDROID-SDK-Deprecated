package io.devless.androidsdk.remote;

import io.devless.androidsdk.FetchQueryResult;
import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * Created by bubu on 4/25/16.
 */
public class ApiCalls {

    private String endpoint;
    private String apiKey;

    private ApiCalls(String endpoint, String apiKey) {
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }

    public static ApiCalls init(String endpoint, String apiKey) {
        return new ApiCalls(endpoint, apiKey);
    }

    private static ApiService getNetworkService(String endpoint) {
        return ApiService.Creator.apiService(endpoint);
    }

    public Call<FetchQueryResult> getData(String service, String table) {
        return getNetworkService(endpoint)
                .getData(apiKey, service, table);
    }

    public Call<ResponseBody> update(String service, String table, RequestBodyTypes.UpdatePayload payload) {
        return getNetworkService(endpoint)
                .update(apiKey, service, table, payload);
    }

    public Call<ResponseBody> delete(String service, String table, RequestBodyTypes.DeletePayload payload) {
        return getNetworkService(endpoint)
                .delete(apiKey, service, table, payload);
    }
}
