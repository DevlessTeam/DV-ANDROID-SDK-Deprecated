package io.devless.android.sdk;

import android.app.Application;

import io.devless.androidsdk.Devless;

/**
 * Created by bubu on 10/3/16.
 */

public class DevlessDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Devless.initialize("http://devless.io:5056", "e7aa041a56dddc05271bd82bb82c7c6f");
    }
}
