package com.android.appfruit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.appfruit.MainActivity;
import com.android.appfruit.R;
import com.android.appfruit.adapter.CategoryAdapter;
import com.android.appfruit.entity.Category;
import com.android.appfruit.service.CategoryService;
import com.android.appfruit.util.RetrofitGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CategoryFragment extends Fragment {
    private CategoryService categoryService;
    private View view;
    private List<Category> listCategoryResponse;
    private List<Category> categories;
    private Context currentContext;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentContext = container.getContext();
        view = inflater.inflate(R.layout.fragment_category, container,false);
        // Inflate the layout for this fragment
        initData();
        initView();
//        bindEventToButton();
        return view;
    }
    private void initView(){
//        categories = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_list_category);
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
//        Button btn1 = view.findViewById(R.id.button1);
//        Button btn2 = view.findViewById(R.id.button2);
//        Button btn3 = view.findViewById(R.id.button3);
//        Button btn4 = view.findViewById(R.id.button4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button textView = view.findViewById(R.id.button1);
//                textView.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout,MainActivity.springFragment , SpringFragment.class.getName())
//                        .commit());
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button textView = view.findViewById(R.id.button2);
//                textView.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout,MainActivity.autumnFragment , AutumnFragment.class.getName())
//                        .commit());
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button textView = view.findViewById(R.id.button3);
//                textView.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout,MainActivity.summerFragment , SummerFragment.class.getName())
//                        .commit());
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button textView = view.findViewById(R.id.button4);
//                textView.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout,MainActivity.winterFragment , WinterFragment.class.getName())
//                        .commit());
//            }
//        });
//    }
}
