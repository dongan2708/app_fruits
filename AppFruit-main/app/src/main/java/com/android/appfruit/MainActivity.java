package com.android.appfruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.SearchView;

import com.android.appfruit.adapter.ProductAdapter;
import com.android.appfruit.entity.ListProductResponse;
import com.android.appfruit.entity.Product;
import com.android.appfruit.service.ProductService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    public static ArrayList<Product> fruitsList = new ArrayList<>();
    private RecyclerView listView;
    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchWidgets();
        setUpList();

    }

    private void initSearchWidgets()
    {
        searchView = (SearchView) findViewById(R.id.shapeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                currentSearchText = s;
                ArrayList<Product> filteredShapes = new ArrayList<Product>();

                for(com.android.appfruit.entity.Product fruits: fruitsList)
                {
                    if(fruits.getName().toLowerCase().contains(s.toLowerCase()))
                    {
                        if(selectedFilter.equals("all"))
                        {
                            filteredShapes.add(fruits);
                        }
                        else
                        {
                            if(fruits.getName().toLowerCase().contains(selectedFilter))
                            {
                                filteredShapes.add(fruits);
                            }
                        }
                    }
                }
                ProductAdapter adapter = new ProductAdapter(getApplicationContext(), fruitsList);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setUpList()
    {
        listView = (RecyclerView) findViewById(R.id.shapesListView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);
        adapter = new ProductAdapter(getApplicationContext(), fruitsList);
        listView.setAdapter(adapter);
    }

    //private void setUpOnclickListener()
    {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
//            {
//                User selectShape = (User) (listView.getItemAtPosition(position));
//                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
//               showDetail.putExtra("id",selectShape.getId());
//                startActivity(showDetail);
//            }
//        });

    }

    private void setUpOnclickListener()
    {
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
//            {
//                Product selectShape = (Product) (listView.getItemAtPosition(position));
//                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
//               showDetail.putExtra("id",selectShape.getId());
//                startActivity(showDetail);
//            }
//        });

    }

   private void filterList(String status) {
//        status = status.toLowerCase();
//        selectedFilter = status;
//        Log.d("Filter", "-----" + status);
//        ArrayList<Product> filteredShapes = new ArrayList<Product>();
//
//        for(Product fruits: fruitsList)
//        {
//            Log.d("Name", "-----" + fruits.getName());
//            if(fruits.getName().toLowerCase().contains(status))
//            {
//                if(currentSearchText.equals(""))
//                {
//                    filteredShapes.add(fruits);
//                }
//                else
//                {
//                    if(fruits.getName().toLowerCase().contains(currentSearchText.toLowerCase()))
//                    {
//                        filteredShapes.add(fruits);
//                    }
//                }
//            }
//        }
//
//        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), products);
//        listView.setAdapter(adapter);
//    }


//    public void allFilterTapped(View view)
//    {
//        selectedFilter = "all";
//        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), products);
//        listView.setAdapter(adapter);
//    }

//    public void triangleFilterTapped(View view)
//    {
//        filterList("Apple");
//    }

//    public void squareFilterTapped(View view)
//    {
//        filterList("Dragon");
//    }

//    public void octagonFilterTapped(View view)
//    {
//        filterList("Bananas");
//    }

//    public void rectangleFilterTapped(View view)
//    {
//        filterList("Guava");
//    }

//    public void circleFilterTapped(View view)
//    {
//        filterList("Grapes");
//    }
}
}