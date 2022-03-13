package com.zohaib.photos.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PixabayResponse {

    @SerializedName("hits")
    public List<Photo> items;

}
