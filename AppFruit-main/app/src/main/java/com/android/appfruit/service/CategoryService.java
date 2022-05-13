package com.android.appfruit.service;


import com.android.appfruit.entity.ListProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {


    @GET("api/v1/products/list?category=1")
    Call<ListProductResponse> getCategoryProduct();
}

