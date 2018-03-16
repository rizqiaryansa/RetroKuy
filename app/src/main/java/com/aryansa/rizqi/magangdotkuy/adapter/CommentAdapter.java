package com.aryansa.rizqi.magangdotkuy.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aryansa.rizqi.magangdotkuy.R;
import com.aryansa.rizqi.magangdotkuy.model.CommentModel;

import java.util.List;

/**
 * Created by RizqiAryansa on 3/16/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    List<CommentModel> listComment;
    Context context;

    public CommentAdapter(List<CommentModel> listComment, Context context) {
        this.listComment = listComment;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_comment,
                parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, final int position) {
        holder.tvName.setText(listComment.get(position).getName());
        holder.tvBody.setText(listComment.get(position).getBody());
        holder.tvEmail.setText(listComment.get(position).getEmail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked comment id : " +
                                listComment.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvName, tvBody, tvEmail;

        public CommentViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cardView);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvBody = (TextView) v.findViewById(R.id.tvBody);
            tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        }
    }
}
