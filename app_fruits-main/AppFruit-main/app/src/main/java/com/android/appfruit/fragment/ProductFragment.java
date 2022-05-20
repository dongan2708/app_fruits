package com.android.appfruit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.R;
import com.android.appfruit.adapter.ProductAdapter;
import com.android.appfruit.entity.ListProductResponse;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.ProductService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ProductFragment extends Fragment {
    private ProductService productService;
    private RecyclerView recyclerView;
    private View view;
    private ListProductResponse responseProduct;
    private List<Product> products;
    private Context currentContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        System.out.println(currentContext);
        view = inflater.inflate(R.layout.activity_productfragment, container, false);
        initData();
        initView();
        // Inflate the layout for this fragment
        return view;
    }

    private void initView(){
        //products = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_list_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
        Log.i("Hello", "onCreate:2222 ----");
        recyclerView.setAdapter(new ProductAdapter(currentContext, products));

    }
    private void  initData(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        products = new ArrayList<>();
        if (productService == null){
            productService = RetrofitGenerator.createService(ProductService.class);
        }
        try {
            Log.i("Hello", "onCreate: ----");
            Response<ListProductResponse> responseProductResponse = productService.getAll().execute();
            if (responseProductResponse.isSuccessful()){
                responseProduct = responseProductResponse.body();
                products = responseProduct.getData();
                Log.i("Hello", "onCreate:12 ----" + products.size());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}