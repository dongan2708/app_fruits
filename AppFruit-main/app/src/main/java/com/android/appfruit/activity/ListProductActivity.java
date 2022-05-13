package com.android.appfruit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import com.android.appfruit.R;
import com.android.appfruit.adapter.ProductAdapter;
import com.android.appfruit.entity.Category;
import com.android.appfruit.entity.ListProductResponse;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.service.ProductService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ListProductActivity extends AppCompatActivity {

    private ProductService productService;
    private RecyclerView recyclerView;
    private ListProductResponse responseProduct;
    private List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        initView();
        initData();
        
    }


    private void initView(){
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdapter(this, products));

    }
    private void  initData(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        products = new ArrayList<>();
        if (productService == null){
            productService = RetrofitGenerator.createService(ProductService.class);
        }
        try {
            Response<ListProductResponse> responseProductResponse = productService.getList().execute();
            if (responseProductResponse.isSuccessful()){
                products = responseProductResponse.body().getData();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}