package com.aryansa.rizqi.magangdotkuy.connectivity;

import com.aryansa.rizqi.magangdotkuy.model.CommentModel;
import com.aryansa.rizqi.magangdotkuy.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by RizqiAryansa on 3/16/2018.
 */

public interface ApiInterface {

    @GET("posts")
    Call <List<PostModel>> getPosts();

    @GET("posts/{id}/comments")
    Call <List<CommentModel>> getComments(@Path("id") int commentId);
}
