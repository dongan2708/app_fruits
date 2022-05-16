package com.android.appfruit.service;


import com.android.appfruit.entity.ListProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("api/v1/products/list?categoryId=1")
    Call<ListProductResponse> getSpring();
    @GET("api/v1/products/list?categoryId=2")
    Call<ListProductResponse> getSummer();
    @GET("api/v1/products/list?categoryId=3")
    Call<ListProductResponse> getAutumn();
    @GET("api/v1/products/list?categoryId=4")
    Call<ListProductResponse> getWinter();

}

