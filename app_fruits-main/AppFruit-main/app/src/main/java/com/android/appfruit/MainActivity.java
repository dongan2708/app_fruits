package com.android.appfruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.android.appfruit.activity.LoginActivity;
import com.android.appfruit.fragment.CategoryFragment;
import com.android.appfruit.fragment.LogoutFragment;
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
    private SearchView searchView;
    //private Storage
    //Add fragment
    public static MyHomeFragment myHomeFragment;
    public static ProductFragment productFragment;
    public static CategoryFragment categoryFragment;
    public static ShoppingCartFragment shoppingCartFragment;
    public static MyProfileFragment myProfileFragment;
    public static LogoutFragment logoutFragment;
    public static String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sh = getSharedPreferences("PRIVATE_INFOR", MODE_PRIVATE);
        accessToken = sh.getString("token", "");
        Log.d("AccessToken - Load", accessToken);
//        final Menu menu = navigationView.getMenu();
//        if(accessToken != null && accessToken.length() > 0){
//            menu.add(" Runtime item"+ i);
//        }else{
//
//        }
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
        logoutFragment = new LogoutFragment();
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
            case R.id.nav_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
        return false;
    }
    public void Welcome(View view) {
        startActivity(new Intent(MainActivity.this, LogoutFragment.class));
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null)
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setMaxWidth(Integer.MAX_VALUE);
    return true;
}
}
