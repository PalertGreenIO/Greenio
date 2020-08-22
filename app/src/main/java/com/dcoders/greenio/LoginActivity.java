package com.dcoders.greenio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity  {


    //variables
    ProgressBar progressBar;
    Button back;
    Button loginbt;
    FirebaseAuth fAuth;
    TextInputEditText email1,passwword1;
    TextView signbtn,forgotbtn;
    SignInButton googlebtn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        fAuth =FirebaseAuth.getInstance();

     //id's
        back = findViewById(R.id.btnback1);
        signbtn = findViewById(R.id.signupbtn);
        forgotbtn = findViewById(R.id.forgotbtn);
        googlebtn = findViewById(R.id.googlebtn);
        email1 = findViewById(R.id.login_emailadd1);
        passwword1 =findViewById(R.id.login_password1);
        progressBar =findViewById(R.id.progressBar3);
        loginbt = findViewById(R.id.loginbtn_user);


        /////////----//////

       //google signin
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);



        ///button call

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email2 = email1.getText().toString().trim();
                String user_pass2 = passwword1.getText().toString().trim();

                if(TextUtils.isEmpty(user_email2)){
                    email1.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(user_pass2)){
                    passwword1.setError("Password is required");
                    return;
                }
                if(user_pass2.length()<7){
                    passwword1.setError("Password must be less than 7");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //////

                fAuth.signInWithEmailAndPassword(user_email2,user_pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(getApplicationContext(),Sample.class));
                              finish();
                          }
                          else {
                              Toast.makeText(LoginActivity.this,"Error!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.GONE);
                          }
                    }
                });
            }
        });

        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });


        findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
            }
        });


        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(LoginActivity.this,"Not Available",Toast.LENGTH_SHORT).show();
            }
        });



        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,OnboardActivity.class);
                startActivity(i);
            }
        });


    }

    //Adapters
    private void signIn() {
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try{
            GoogleSignInAccount account = task.getResult(ApiException.class);
            startActivity(new Intent(LoginActivity.this,ShopActivity.class));
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(LoginActivity.this,"Loading",Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            //e.printStackTrace();
            Log.w("Google Sign In Error","signInResult:failed code="+e.getStatusCode());
            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            startActivity(new Intent(LoginActivity.this,ShopActivity.class));
        }
        super.onStart();
    }


}



