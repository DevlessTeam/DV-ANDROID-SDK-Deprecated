package io.devless.androidsdk.remote;


import io.devless.androidsdk.FetchQueryResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bubu on 4/25/16.
 */
interface ApiService {

    String subUrl = "/api/v1/service/";
    String AUTH_TOKEN = "devless-token";

    @GET("{service}/db")
    Call<FetchQueryResult> getData(
            @Header(AUTH_TOKEN) String authKey,
            @Path("service") String service,
            @Query("table") String table);

    @PATCH("{service}/db")
    Call<ResponseBody> update(
            @Header(AUTH_TOKEN) String authKey,
            @Path("service") String service,
            @Query("table") String table,
            @Body RequestBodyTypes.UpdatePayload payload
    );

    @HTTP(method = "DELETE", path = "{service}/db", hasBody = true)
    Call<ResponseBody> delete(
            @Header(AUTH_TOKEN) String authKey,
            @Path("service") String service,
            @Query("table") String table,
            @Body RequestBodyTypes.DeletePayload payload
    );

    @POST("{service}/db")
    Call<ResponseBody> insert(
            @Header(AUTH_TOKEN) String authKey,
            @Path("service") String service,
            @Query("table") String table,
            @Body RequestBodyTypes.InsertPayload payload
    );

    @POST("{service}/rpc")
    Call<ResponseBody> rpc(
            @Header(AUTH_TOKEN) String authKey,
            @Path("service") String service,
            @Query("action") String action,
            @Body RequestBodyTypes.RPCPayload payload
    );

    class Creator {
        static ApiService apiService(String endpoint) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint + subUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }

    }
}
