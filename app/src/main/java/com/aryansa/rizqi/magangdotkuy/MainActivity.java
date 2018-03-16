package com.aryansa.rizqi.magangdotkuy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aryansa.rizqi.magangdotkuy.adapter.PostAdapter;
import com.aryansa.rizqi.magangdotkuy.connectivity.ApiClient;
import com.aryansa.rizqi.magangdotkuy.connectivity.ApiInterface;
import com.aryansa.rizqi.magangdotkuy.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView tvPost;
    LinearLayout lyout;

    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lyout = (LinearLayout) findViewById(R.id.ly_layout);
        tvPost = (TextView) findViewById(R.id.tvTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.VISIBLE);

        connectPost();
    }

    private void connectPost() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call <List<PostModel>> call = apiService.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                List<PostModel> listPost = response.body();

                recyclerView.setAdapter(new PostAdapter(listPost, MainActivity.this));
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                call.cancel();
            }
        });
    }
}
