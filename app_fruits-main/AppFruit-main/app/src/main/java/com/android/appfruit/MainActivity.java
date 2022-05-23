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
import com.android.appfruit.fragment.AutumnFragment;
import com.android.appfruit.fragment.CategoryFragment;
import com.android.appfruit.fragment.ProductFragment;
import com.android.appfruit.fragment.SpringFragment;
import com.android.appfruit.fragment.SummerFragment;
import com.android.appfruit.fragment.WinterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FrameLayout frameLayout; // chứa nội dung chính
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;

    //Add fragment
   public static ProductFragment productFragment;
   public static CategoryFragment categoryFragment;
   public static SpringFragment springFragment;
   public static AutumnFragment autumnFragment;
   public static SummerFragment summerFragment;
   public static WinterFragment winterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

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
        // add fragment.
        springFragment = new SpringFragment();
        productFragment = new ProductFragment();
        categoryFragment = new CategoryFragment();
        autumnFragment = new AutumnFragment();
        springFragment = new SpringFragment();
        summerFragment = new SummerFragment();
        winterFragment = new WinterFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, categoryFragment, CategoryFragment.class.getName())
                .commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, categoryFragment, CategoryFragment.class.getName())
                        .commit();
                break;
            case R.id.nav_product:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, productFragment, ProductFragment.class.getName())
                        .commit();
                break;

        }
        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null)
//        {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
    }

}
