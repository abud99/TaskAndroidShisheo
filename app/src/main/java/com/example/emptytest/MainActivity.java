package com.example.emptytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Parser parse;
    private RecyclerView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final String url = "https://gateway-dev.shisheo.com/social/api/web/post/arina/test";
    private Request request;
    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client = new OkHttpClient();
        view = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        view.setHasFixedSize(true);
        observable = Observable.just("notsure");
        view.setLayoutManager(layoutManager);
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribeOn(Schedulers.io());


         observer = new Observer<String>() {
             @Override
             public void onSubscribe(@NonNull Disposable d) {
                 request = new Request.Builder()
                         .url(url)
                         .build();
             }

             @Override
             public void onNext(@NonNull String s) {

             }

             @Override
             public void onError(@NonNull Throwable e) {

             }

             @Override
             public void onComplete() {
                 client.newCall(request).enqueue(new Callback() {
                     @Override
                     public void onFailure(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull IOException e) {

                     }

                     @Override
                     public void onResponse(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        parse = new Parser(response.body().string());
                                        adapter = new CustomAdapter(Parser.list);
                                        view.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });


                        }
                     }
                 });
             }
         };

        observable.subscribe(observer);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.searchView);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        return true;
    }








}