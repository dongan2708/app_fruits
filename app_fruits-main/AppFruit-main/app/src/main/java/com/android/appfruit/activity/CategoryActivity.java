package com.android.appfruit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.android.appfruit.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        super.onCreate(savedInstanceState);
        Log.i("Hello", "onCreate: ");
        setContentView(R.layout.fragment_category);

//        bindEventToButton();
    }
//    private void bindEventToButton() {
//        Button btn1 = findViewById(R.id.button1);
//        Button btn2 = findViewById(R.id.button2);
//        Button btn3 = findViewById(R.id.button3);
//        Button btn4 = findViewById(R.id.button4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, SpringFragment.class);
//                startActivity(intent);
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, SummerFragment.class);
//                startActivity(intent);
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, AutumnFragment.class);
//                startActivity(intent);
//            }
//        });
//
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CategoryActivity.this, WinterFragment.class);
//                startActivity(intent);
//            }
//        });
//    }
}