package com.android.appfruit.service;


import com.android.appfruit.entity.ListProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {

    @GET("api/v1/products/list")
    Call<ListProductResponse> getAll();

    @GET("api/v1/products/list")
    Call<ListProductResponse> getByCategory(@Query("categoryId") int id);
}
