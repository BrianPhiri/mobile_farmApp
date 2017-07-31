package com.example.brianphiri.farm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brianphiri.farm.adapter.FarmerOrderListAdapter;
import com.example.brianphiri.farm.customer.CustomerMainActivity;
import com.example.brianphiri.farm.farmer.FarmerMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView reg;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.loginEmailInput);
        password = (EditText) findViewById(R.id.loginPasswordInput);
        reg = (TextView) findViewById(R.id.registrationTxt);
        login = (Button) findViewById(R.id.signInUserBtn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String u = username.getText().toString(), p = password.getText().toString();
//                signIn(u,p );
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString() == "farmer@test.com"){
                    Intent in = new Intent(LoginActivity.this, FarmerMainActivity.class);
                    startActivity(in);
                }else{
                    Intent in = new Intent(LoginActivity.this, FarmerMainActivity.class);
                    startActivity(in);
                }
            }
        });
    }

    private void signIn(String email, String password) {


//        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), (CharSequence) user,Toast.LENGTH_LONG);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            ;
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "ot good",Toast.LENGTH_LONG);
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        Intent i = new Intent(LoginActivity.this, LoginActivity.class);
    }
}
