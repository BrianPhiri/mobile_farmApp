package com.example.brianphiri.farm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianphiri.farm.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    EditText fullname, username,type, password, confPassword;
    Button clear, register;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final FirebaseDatabase database = com.google.firebase.database.FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullname = (EditText) findViewById(R.id.userFullNameInput);
        username = (EditText) findViewById(R.id.userNameInput);
        type = (EditText) findViewById(R.id.userTypeInput);
        password = (EditText) findViewById(R.id.passwordInput);
        confPassword = (EditText) findViewById(R.id.confPassword);
        clear = (Button) findViewById(R.id.clearRegistrationBtn);
        register = (Button) findViewById(R.id.addNewUserBtn);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname.setText("");
                username.setText("");
                type.setText("");
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public static final String TAG = "";

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(username.getText().toString(), password.getText().toString());
            }
        });


    }

    public void createAccount(String semail, String ppassword)
    {
        mAuth.createUserWithEmailAndPassword(semail, ppassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete" + task.isSuccessful());

                        if (task.isSuccessful()){
                            Users user = new Users(fullname.getText().toString(), username.getText().toString(), type.getText().toString());
                            myRef.push().setValue(user);
                            fullname.setText("");
                            username.setText("");
                            type.setText("");
                            Toast.makeText(RegistrationActivity.this, "Created user.", Toast.LENGTH_SHORT).show();
                        }


                        if(!task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
