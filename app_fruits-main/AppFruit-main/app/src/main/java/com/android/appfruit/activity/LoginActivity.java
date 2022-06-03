package com.android.appfruit.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.appfruit.MainActivity;
import com.android.appfruit.R;
import com.android.appfruit.dto.CredentialDto;
import com.android.appfruit.dto.LoginDto;
import com.android.appfruit.dto.LoginToken;
import com.android.appfruit.service.AccountService;
import com.android.appfruit.util.RetrofitGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnSignIn;
    EditText etPassword, etUserName;
    TextView tvMyFruits, tvSignIn, tvDntHvAccount, tvRegister;
    ImageView imgView2;
    AccountService accountService;

//    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }
        initData();
        initListener();
//        mAuth = FirebaseAuth.getInstance();

//        btnSignIn.setOnClickListener(view ->{
//            loginUser();
//        });
//
//        tvRegister.setOnClickListener(view ->{
//            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//        });
    }
    private void initData() {
        btnSignIn = findViewById(R.id.button);
        imgView2 = findViewById(R.id.imgSignIn);
        tvMyFruits = findViewById(R.id.txtView1);
        tvSignIn = findViewById(R.id.txtView2);
        tvDntHvAccount = findViewById(R.id.txtView3);
        tvRegister = findViewById(R.id.txtView4);
        etPassword = findViewById(R.id.txtPassword);
        etUserName = findViewById(R.id.txtUserName);
        accountService = RetrofitGenerator.createService(AccountService.class);

    }
    private void initListener() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Token","123");
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                LoginDto loginDto = new LoginDto();
                loginDto.setUsername(userName);
                loginDto.setPassword(password);
                try {
                    Response<LoginToken> tokenResponse =  accountService.login(loginDto).execute();
                    Log.d("Token","1234");

                    if(tokenResponse.isSuccessful()){
                        Log.d("Token","12345");

//                        LoginToken loginToken = tokenResponse.body();
//                        Log.d("Token", loginToken.getAccess_token());
                        Toast.makeText(LoginActivity.this, "Login success",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Log.d("Token",new Gson().toJson(tokenResponse.message()));
                        Log.d("Token",new Gson().toJson(tokenResponse.body()));
                        Log.d("Token",new Gson().toJson(tokenResponse.errorBody()));
                        Log.d("Token",new Gson().toJson(tokenResponse.code()));

                        Toast.makeText(LoginActivity.this,"Login failed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
//    private void loginUser() {
//        String name = etUserName.getText().toString();
//        String password = etPassword.getText().toString();
//
//        if(TextUtils.isEmpty(name)){
//            etUserName.setError("Please Enter UserName");
//            etUserName.requestFocus();
//        }
//        else if(TextUtils.isEmpty(password)){
//            etPassword.setError("Please Enter Password");
//            etPassword.requestFocus();
//        }
////        else{
////            mAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
////                @Override
////                public void onComplete(@NonNull Task<AuthResult> task) {
////                    if(task.isSuccessful()){
////                        Toast.makeText(LoginActivity.this,"User Logged in Successfully", Toast.LENGTH_SHORT).show();
////                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
////                    }else{
////                        Toast.makeText(LoginActivity.this,"Log in Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
////        }
//    }

    public void Register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}