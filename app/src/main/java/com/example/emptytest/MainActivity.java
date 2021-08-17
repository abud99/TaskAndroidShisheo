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
import java.util.concurrent.Callable;

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
    private Parser parser;
    private final String url = "https://gateway-dev.shisheo.com/social/api/web/post/arina/test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client = new OkHttpClient();
        view = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        view.setHasFixedSize(true);
        view.setLayoutManager(layoutManager);
        Observable.fromCallable(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                return response;

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new Observer<Response>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onNext(@NonNull Response response) {
                String json = null;
                if (response.isSuccessful()){
                    try {
                        json = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    parser = new Parser(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new CustomAdapter(Parser.list);
                view.setAdapter(adapter);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.searchView);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        return true;
    }








}