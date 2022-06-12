package com.android.appfruit.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.appfruit.R;
import com.android.appfruit.entity.Account;
import com.android.appfruit.entity.Category;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.service.MyProfileService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private MyProfileService myProfileService;
    private View view;
    private String token = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initData(); // kéo thông tin categories từ api.
        return view;
    }

    private void initData() {
        // cấu hình để call api.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SharedPreferences settings = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = settings.getString("token", "");
        String refreshToken = settings.getString("refreshToken", "");
        Log.d("refreshToken", refreshToken);
        // khởi tạo retrofit để call api trường hợp chưa.
        if (myProfileService == null) {
            myProfileService = RetrofitGenerator.createService(MyProfileService.class,token);
        }
        try {
            Response<List<Account>> AccountResponse = myProfileService.getAll(3).execute();
            // trường hợp thành công.
            if (AccountResponse.isSuccessful()) {
                // thì lấy danh sách categories.
                myProfileService = (MyProfileService) AccountResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("", e.getMessage());
        }
    }
}