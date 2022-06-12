package com.android.appfruit.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.R;
import com.android.appfruit.adapter.CartAdapter;
import com.android.appfruit.adapter.CategoryAdapter;
import com.android.appfruit.entity.CartItem;
import com.android.appfruit.entity.Category;
import com.android.appfruit.entity.Product;
import com.android.appfruit.entity.ShoppingCart;
import com.android.appfruit.service.CartService;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ShoppingCartFragment extends Fragment {

    private View view;
    private Context currentContext;
    private static RecyclerView recyclerView;
    public static Product currentProduct;
    private static FrameLayout noItemDefault;
    private List<CartItem> items;
    private CartService cartService;
    private CartAdapter cartAdapter;
    private String token = null;

    public ShoppingCartFragment() {
        items = new ArrayList<>();
    }


    private ShoppingCart initData() {
        try {
            Response<ShoppingCart> CartItemResponse = null;
            CartItemResponse = cartService.getCart().execute();
            if (CartItemResponse.isSuccessful()) {
                items = CartItemResponse.body().getOrderDetails();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Cart fragment", e.getMessage());
        }
        return new ShoppingCart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_cart, container,false);
        config();
        initData();
        Log.d("data", "received");
        initView();
        Log.d("success", "success");

        return view;
    }

    private void config() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences settings = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = settings.getString("token", "");
        String refreshToken = settings.getString("refreshToken", "");
        Log.d("token", token);
        Log.d("refreshToken", refreshToken);
        if (cartService == null) {
            cartService = RetrofitGenerator.createService(CartService.class,token);
        }
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view_list_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(currentContext));
        recyclerView.setAdapter(new CartAdapter(currentContext, items));
    }
}
