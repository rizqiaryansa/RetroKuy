package com.aryansa.rizqi.magangdotkuy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aryansa.rizqi.magangdotkuy.DetailCommentActivity;
import com.aryansa.rizqi.magangdotkuy.R;
import com.aryansa.rizqi.magangdotkuy.model.PostModel;

import java.util.List;

/**
 * Created by RizqiAryansa on 3/16/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostModel> listPost;
    private Context context;

    public PostAdapter(List<PostModel> listPost, Context context) {
        this.listPost = listPost;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_post,
                parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostAdapter.PostViewHolder holder, final int position) {

        holder.tvTitle.setText(listPost.get(position).getTitle());
        holder.tvBody.setText(listPost.get(position).getBody());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCommentActivity.class);
                intent.putExtra(DetailCommentActivity.EXTRA_POST, listPost.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

   class PostViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvTitle, tvBody;

        public PostViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cardView);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvBody = (TextView) v.findViewById(R.id.tvBody);
        }
    }
}
