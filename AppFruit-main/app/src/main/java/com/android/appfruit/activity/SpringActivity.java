package com.android.appfruit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.android.appfruit.R;
import com.android.appfruit.adapter.CategoryAdapter;
import com.android.appfruit.adapter.ProductAdapter;
import com.android.appfruit.entity.ListProductResponse;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SpringActivity extends AppCompatActivity {

    private CategoryService categoryService;
    private RecyclerView recyclerView;
    private ListProductResponse responseProduct;
    private List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        initData();
        initView();
    }

    private void initView() {
        //products = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i("Hello", "onCreate:2222 ----");
        recyclerView.setAdapter(new ProductAdapter(this, products));

    }
    private void initData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        products = new ArrayList<>();
        if (categoryService == null){
            categoryService = RetrofitGenerator.createService(CategoryService.class);
        }
        try {
            Log.i("Hello", "Get categories from api");
            Response<ListProductResponse> responseProductResponse = categoryService.getSpring().execute();
            if (responseProductResponse.isSuccessful()){
                responseProduct = responseProductResponse.body();
                products = responseProduct.getData();
//                Log.i("Hello", "onCreate:12 ----" + categories.size());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}