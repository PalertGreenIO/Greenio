package com.dcoders.greenio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private Button btnback, signUpbtn;
    private TextInputEditText fullname, Emailadd, Phone, Password;
    private TextView loginbtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpIds();

        fAuth =FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        //btnCall

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name1 = fullname.getText().toString().trim();
                String user_email1 = Emailadd.getText().toString().trim();
                String user_phone1 = Phone.getText().toString().trim();
                String user_pass1 = Password.getText().toString().trim();

                if(TextUtils.isEmpty(user_email1)){
                    Emailadd.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(user_pass1)){
                    Password.setError("Password is required");
                    return;
                }
                if(user_pass1.length()<7){
                    Password.setError("Password must be 7 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //////////
                fAuth.createUserWithEmailAndPassword(user_email1,user_pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Error!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }

    //ID's
    private void setUpIds() {
        btnback = findViewById(R.id.btnback2);
        signUpbtn = findViewById(R.id.SignUpbtn);
        fullname = findViewById(R.id.user_fullname);
        Emailadd = findViewById(R.id.email_address);
        Phone = findViewById(R.id.user_phone);
        Password = findViewById(R.id.user_password);
        progressBar = findViewById(R.id.progressB);
        loginbtn = findViewById(R.id.go_loginbtn);



    }

    }










