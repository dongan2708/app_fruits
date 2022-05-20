package com.android.appfruit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.R;
import com.android.appfruit.adapter.CategoryAdapter;
import com.android.appfruit.adapter.ProductAdapter;
import com.android.appfruit.entity.Category;
import com.android.appfruit.entity.ListCategoryResponse;
import com.android.appfruit.entity.ListProductResponse;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CategoryFragment extends Fragment {
    private CategoryService categoryService;
    private RecyclerView recyclerView;
    private View view;
    private List<Category> listCategoryResponse;
    private List<Category> categories;
    private Context currentContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        view = inflater.inflate(R.layout.activity_categoryfragment, container,false);
        // Inflate the layout for this fragment
        initView();
        initData();

        //bindEventToButton();
        return view;
    }
    private void initView(){
        //products = new ArrayList<>();
//        recyclerView = view.findViewById(R.id.recycler_view_category);
//        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
//        Log.i("Hello", "onCreate:2222 ----");
//        recyclerView.setAdapter(new CategoryAdapter(currentContext, categories));

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
            Response<List<Category>> CategoryResponse = categoryService.getAll().execute();
            if (CategoryResponse.isSuccessful()){
                categories = CategoryResponse.body();
                //categories = listCategoryResponse.getData();
                Log.i("Hello", "onCreate:12 ----" + categories.size());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//    private void bindEventToButton() {
//        Button btn1 = findViewById(R.id.button1);
//        Button btn2 = findViewById(R.id.button2);
//        Button btn3 = findViewById(R.id.button3);
//        Button btn4 = findViewById(R.id.button4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryFragment.this, SpringActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, SummerActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, AutumnActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, WinterActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}
