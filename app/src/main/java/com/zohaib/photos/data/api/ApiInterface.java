package com.zohaib.photos.data.api;

import com.zohaib.photos.data.model.Photo;
import com.zohaib.photos.data.model.PixabayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(".")
    Call<PixabayResponse> getPhotos(@Query("per_page") String per_page, @Query("key") String key);

}
