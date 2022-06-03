package com.android.appfruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.appfruit.activity.LoginActivity;
import com.android.appfruit.fragment.CategoryFragment;
import com.android.appfruit.fragment.MyHomeFragment;
import com.android.appfruit.fragment.MyProfileFragment;
import com.android.appfruit.fragment.ProductFragment;
import com.android.appfruit.fragment.ShoppingCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements
        NavigationBarView.OnItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FrameLayout frameLayout; // chứa nội dung chính
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    // Firebase
    private DatabaseReference myRef;
    //private Storage
    //Add fragment
    public static MyHomeFragment myHomeFragment;
    public static ProductFragment productFragment;
    public static CategoryFragment categoryFragment;
    public static ShoppingCartFragment shoppingCartFragment;
    public static MyProfileFragment myProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        myRef = FirebaseDatabase.getInstance().getReference();
    }
    private void initView() {
        // Cấu hình drawer layout.
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_menu,
                        R.string.close_menu);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);
        // khởi tạo các fragment liên quan.
        myHomeFragment = new MyHomeFragment();
        productFragment = new ProductFragment();
        categoryFragment = new CategoryFragment();
        shoppingCartFragment= new ShoppingCartFragment();
        myProfileFragment = new MyProfileFragment();
        // chạy fragment default
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, myHomeFragment, MyHomeFragment.class.getName())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.home_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, myHomeFragment, MyHomeFragment.class.getName())
                        .commit();
                break;
            case R.id.nav_category:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, categoryFragment, CategoryFragment.class.getName())
                        .commit();
                break;
            case R.id.nav_product:
                System.out.println("Reset");
                productFragment = new ProductFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, productFragment, ProductFragment.class.getName())
                        .commit();
                break;
            case R.id.nav_shopping_cart:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, shoppingCartFragment, ShoppingCartFragment.class.getName())
                        .commit();
                break;
            case R.id.nav_my_profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, myProfileFragment, MyProfileFragment.class.getName())
                        .commit();
                break;

        }
        return false;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null)
//        {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
//    }

}
