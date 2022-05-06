package com.android.appfruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.android.appfruit.adapter.ShapeAdapter;
import com.android.appfruit.entity.Fruits;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    public static ArrayList<Fruits> fruitsList = new ArrayList<Fruits>();

    private RecyclerView listView;

    private String selectedFilter = "all";
    private String currentSearchText = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchWidgets();
        setupData();
        setUpList();
        setUpOnclickListener();
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
                ArrayList<Fruits> filteredShapes = new ArrayList<Fruits>();

                for(Fruits fruits: fruitsList)
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
                ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), filteredShapes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    private void setupData()
    {
        Fruits apple = new Fruits(R.drawable.apple,"Apple","10000");
        fruitsList.add(apple);
        Fruits banana = new Fruits(R.drawable.bananas,"Bananas","10000");
        fruitsList.add(banana);
        Fruits dragon = new Fruits(R.drawable.dragon_fruits,"Dragon","10000");
        fruitsList.add(dragon);
        Fruits grapes = new Fruits(R.drawable.grapes,"Grapes","10000");
        fruitsList.add(grapes);
        Fruits guava = new Fruits(R.drawable.guava_fruit,"Guava","10000");
        fruitsList.add(guava);
        Fruits apple4 = new Fruits(R.drawable.apple1,"Apple","10000");
        fruitsList.add(apple4);
        Fruits banana1 = new Fruits(R.drawable.bananas1,"Bananas","10000");
        fruitsList.add(banana1);
        Fruits dragon1 = new Fruits(R.drawable.dragon_fruits4,"Dragon","10000");
        fruitsList.add(dragon1);

    }

    private void setUpList()
    {
        listView = (RecyclerView) findViewById(R.id.shapesListView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), fruitsList);
        listView.setLayoutManager(llm);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener()
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



    private void filterList(String status)
    {
        status = status.toLowerCase();
        selectedFilter = status;
        Log.d("Filter", "-----" + status);
        ArrayList<Fruits> filteredShapes = new ArrayList<Fruits>();

        for(Fruits fruits: fruitsList)
        {
            Log.d("Name", "-----" + fruits.getName());
            if(fruits.getName().toLowerCase().contains(status))
            {
                if(currentSearchText.equals(""))
                {
                    filteredShapes.add(fruits);
                }
                else
                {
                    if(fruits.getName().toLowerCase().contains(currentSearchText.toLowerCase()))
                    {
                        filteredShapes.add(fruits);
                    }
                }
            }
        }

        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), filteredShapes);
        listView.setAdapter(adapter);
    }


    public void allFilterTapped(View view)
    {
        selectedFilter = "all";
        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), fruitsList);
        listView.setAdapter(adapter);
    }

    public void triangleFilterTapped(View view)
    {
        filterList("Apple");
    }

    public void squareFilterTapped(View view)
    {
        filterList("Dragon");
    }

    public void octagonFilterTapped(View view)
    {
        filterList("Bananas");
    }

    public void rectangleFilterTapped(View view)
    {
        filterList("Guava");
    }

    public void circleFilterTapped(View view)
    {
        filterList("Grapes");
    }
}