package io.devless.android.sdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import io.devless.androidsdk.Devless;
import io.devless.androidsdk.ErrorResult;
import io.devless.androidsdk.QueryResult;
import io.devless.androidsdk.Util;
import io.devless.androidsdk.remote.DeleteBuilder;
import io.devless.androidsdk.remote.InsertBuilder;
import io.devless.androidsdk.remote.RequestBodyTypes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text_view);


        Devless.get()
                .getData("inventory", "products", Inventory[].class, new Util.DevlessCallback<Inventory>() {
                    public void onSuccess(List<Inventory> res) {

                        if (res.size() > 0)
                            textView.setText(res.get(0).getName());

                    }

                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        textView.setText("Error:   " + throwable.toString());
                    }
                });

        //updating a field

//        RequestBodyTypes.UpdatePayload payload = new UpdateBuilder()
//                .setTable("products")
//                .where("id", "2")
//                .set("name", "James")
//                .build();
//
//        Devless.get()
//                .update("inventory", payload, new Util.DevlessQueryCallback() {
//
//                    @Override
//                    public void onSuccess(QueryResult queryResult) {
//                        System.out.println(">>>>>>>>pathching pass>>>>>>>>>>>>.." + queryResult.getMessage() + " status code: " + queryResult.getStatusCode());
//                    }
//
//                    @Override
//                    public void onFail(ErrorResult errorResult) {
//                        System.out.println(">>>>>>>>pathching failed>>>>>>>>>>>>.." + errorResult.getMessage());
//                    }
//
//                    public void onError(Throwable throwable) {
//                        throwable.printStackTrace();
//
//                        textView.setText("Error:   " + throwable.toString());
//                    }
//                });

        RequestBodyTypes.DeletePayload deletePayload = new DeleteBuilder()
                .setTable("products")
                .where("id", "2")
                .build();

        Devless.get()
                .delete("inventory", deletePayload, new Util.DevlessQueryCallback() {

                    @Override
                    public void onSuccess(QueryResult queryResult) {
                        System.out.println(">>>>>>>> delete success>>>>>>>>>>>>.." + queryResult.getMessage() + " status code: " + queryResult.getStatusCode());
                    }

                    @Override
                    public void onFail(ErrorResult errorResult) {
                        System.out.println(">>>>>>>> delete failed>>>>>>>>>>>>.." + errorResult.getMessage() + " status code: " + errorResult.getStatusCode());
                    }

                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        textView.setText("Error:   " + throwable.toString());
                    }
                });

        Inventory inventory = new Inventory();
        inventory.setName("Kofi");

        RequestBodyTypes.InsertPayload insertPayload = new InsertBuilder<Inventory>()
                .setTable("products")
                .add(inventory)
                .build();

        Devless.get()
                .insert("inventory", insertPayload, new Util.DevlessQueryCallback() {

                    @Override
                    public void onSuccess(QueryResult queryResult) {
                        System.out.println(">>>>>>>> insert success>>>>>>>>>>>>.." + queryResult.getMessage() + " status code: " + queryResult.getStatusCode());
                    }

                    @Override
                    public void onFail(ErrorResult errorResult) {
                        System.out.println(">>>>>>>> insert failed>>>>>>>>>>>>.." + errorResult.getMessage() + " status code: " + errorResult.getStatusCode());
                    }

                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        textView.setText("Error:   " + throwable.toString());
                    }
                });
    }
}
