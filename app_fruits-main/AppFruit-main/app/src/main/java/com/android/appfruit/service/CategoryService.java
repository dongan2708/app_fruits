package com.android.appfruit.service;


import com.android.appfruit.entity.Category;
import com.android.appfruit.entity.ListCategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("api/v1/categories/list")
    Call<List<Category>> getAll();
    @GET("api/v1/products/list?categoryId=1")
    Call<ListCategoryResponse> getSpring();
    @GET("api/v1/products/list?categoryId=2")
    Call<ListCategoryResponse> getSummer();
    @GET("api/v1/products/list?categoryId=3")
    Call<ListCategoryResponse> getAutumn();
    @GET("api/v1/products/list?categoryId=4")
    Call<ListCategoryResponse> getWinter();

}

