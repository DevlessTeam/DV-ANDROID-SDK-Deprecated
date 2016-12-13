package io.devless.androidsdk.remote;

/**
 * Created by bubu on 4/25/16.
 */
public interface ResultCallback<T> {
    void onError(Throwable t);

    void onPaymentSuccessful(T t);

    void onPaymentFailed(T t);
}

