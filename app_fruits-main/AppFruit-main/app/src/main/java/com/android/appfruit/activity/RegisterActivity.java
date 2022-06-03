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
import com.android.appfruit.entity.Account;
import com.android.appfruit.service.AccountService;
import com.android.appfruit.util.RetrofitGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText text_username,text_name,text_phone, text_password;
    TextView Already_registered, signIn,register, myFruits;
    ImageView img1;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
//
//        btnRegister.setOnClickListener(view ->{
//            createUser();
//        });
//
//        signIn.setOnClickListener(view ->{
//            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//        });

    }
    private void initData() {
        btnRegister = (Button) findViewById(R.id.button);
        text_username = (EditText) findViewById(R.id.txtUserName);
        text_name = (EditText) findViewById(R.id.txtName);
        text_password = (EditText) findViewById(R.id.txtPassword);
        text_phone = (EditText) findViewById(R.id.txtPhone);
        Already_registered = (TextView) findViewById(R.id.txtView3);
        signIn = (TextView) findViewById(R.id.txtView4);
        register = (TextView) findViewById(R.id.tView2);
        myFruits = (TextView) findViewById(R.id.tView1);
        img1 = (ImageView) findViewById(R.id.imgRegister);
    }

    private void initListener(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = text_username.getText().toString();
                String password = text_password.getText().toString();
                String name = text_name.getText().toString();
                String phone = text_phone.getText().toString();

                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                account.setPhone(phone);
                account.setName(name);

                AccountService accountService = RetrofitGenerator.createService(AccountService.class);
                Log.d("ERROR", new Gson().toJson(account));
                Response<Account> accountCall = null;
                try {
                    accountCall = accountService.register(account).execute();
                    if(accountCall.isSuccessful()){
                        CharSequence charSequence = "Tạo thành công";
                        Toast toast = Toast.makeText(getApplicationContext(), charSequence, Toast.LENGTH_LONG);
                        toast.show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    CharSequence charSequence = e.getMessage();
                    Toast toast = Toast.makeText(getApplicationContext(), charSequence, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
    private void createUser() {
        String email = text_username.getText().toString();
        String password = text_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            text_username.setError("Please Enter Email");
            text_username.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            text_password.setError("Please Enter Password");
            text_password.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registration Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void Login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

}