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

import com.aryansa.rizqi.magangdotkuy.adapter.CommentAdapter;
import com.aryansa.rizqi.magangdotkuy.connectivity.ApiClient;
import com.aryansa.rizqi.magangdotkuy.connectivity.ApiInterface;
import com.aryansa.rizqi.magangdotkuy.model.CommentModel;
import com.aryansa.rizqi.magangdotkuy.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCommentActivity extends AppCompatActivity {

    private static final String TAG = DetailCommentActivity.class.getSimpleName();
    public static String EXTRA_POST = "EXTRA_POST";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView tvComment;
    LinearLayout lyout;

    ApiInterface apiService;

    private int idPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comment);

        if (getIntent().getExtras() != null) {
            idPost = getIntent().getIntExtra(EXTRA_POST, 0);
        }

        lyout = (LinearLayout) findViewById(R.id.ly_layout);
        tvComment = (TextView) findViewById(R.id.tv_post);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.VISIBLE);

        connectComment();
    }

    private void connectComment() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CommentModel>> call = apiService.getComments(idPost);
        call.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call,
                                   Response<List<CommentModel>> response) {
                List<CommentModel> listComment = response.body();

                tvComment.setText("POST ID : " + idPost);
                recyclerView.setAdapter(new CommentAdapter(listComment,
                        DetailCommentActivity.this));
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                call.cancel();
            }
        });
    }
}
