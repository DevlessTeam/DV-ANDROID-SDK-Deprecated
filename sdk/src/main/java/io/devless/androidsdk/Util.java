package io.devless.androidsdk;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bubu on 10/3/16.
 */

public class Util {

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    public interface DevlessQueryCallback {

        void onSuccess(QueryResult queryResult);

        void onFail(ErrorResult errorResult);

        void onError(Throwable throwable);

    }


    public static abstract class DevlessCallback<T> {
        void onSuccess(List<T> t) {
        }

        void onResult(FetchQueryResult fetchQueryResult) {
        }

        void onError(Throwable throwable) {
        }
    }
}
