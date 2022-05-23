package com.android.appfruit.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.appfruit.R;
import com.android.appfruit.adapter.CategoryAdapter;
import com.android.appfruit.entity.Category;
import com.android.appfruit.entity.ListCategoryResponse;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class SpringFragment extends Fragment {
    private CategoryService categoryService;
    private RecyclerView recyclerView;
    private View view;
    private ListCategoryResponse listCategoryResponse;
    private List<Category> categories;
    private Context currentContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_spring, container,false);
        // Inflate the layout for this fragment
        initData();
        initView();
        return view;
    }
    private void initView(){
//        categories = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rw_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
        Log.i("Hello", "onCreate:2222 ----");
        recyclerView.setAdapter(new CategoryAdapter(currentContext, categories));

    }
    private void  initData(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        categories = new ArrayList<>();
        if (categoryService == null){
            categoryService = RetrofitGenerator.createService(CategoryService.class);
        }
        try {
            Log.i("Hello", "onCreate: ----");
            Response<ListCategoryResponse> CategoryResponse = categoryService.getSpring().execute();
            if (CategoryResponse.isSuccessful()){
                listCategoryResponse = CategoryResponse.body();
                categories = listCategoryResponse.getData();
                Log.i("Hello", "onCreate:12 ----" + categories.size());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}