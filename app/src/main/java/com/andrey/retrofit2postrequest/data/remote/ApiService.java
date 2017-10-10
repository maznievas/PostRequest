package com.andrey.retrofit2postrequest.data.remote;

import com.andrey.retrofit2postrequest.data.model.Post;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sts on 10.10.17.
 */

public interface ApiService {

    @POST("/posts")
    @FormUrlEncoded
    Observable<Post> savePost(@Field("title") String title,
                              @Field("body") String body,
                              @Field("userId") long userId);
}
