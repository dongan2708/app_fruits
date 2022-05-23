package com.android.appfruit.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.appfruit.MainActivity;
import com.android.appfruit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText text_name,text_mail, text_password;
    TextView Already_registered, signIn,register, myFruits;
    ImageView img1;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.button);
        text_mail = findViewById(R.id.text3);
        text_name = findViewById(R.id.text1);
        text_password = findViewById(R.id.editText);
        Already_registered = findViewById(R.id.txtView3);
        signIn = findViewById(R.id.txtView4);
        register = findViewById(R.id.tView2);
        myFruits = findViewById(R.id.tView1);
        img1 = findViewById(R.id.imgView);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        signIn.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

    }

    private void createUser() {
        String email = text_mail.getText().toString();
        String password = text_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            text_mail.setError("Please Enter Email");
            text_mail.requestFocus();
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