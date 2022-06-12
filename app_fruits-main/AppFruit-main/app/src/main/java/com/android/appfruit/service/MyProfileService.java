package com.android.appfruit.service;

import com.android.appfruit.entity.Account;
import com.android.appfruit.entity.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

public interface MyProfileService {
    @GET("api/v1/account/detail/{id}")
    Call<List<Account>> getAll(
            @Part("id") int id
    );
}
