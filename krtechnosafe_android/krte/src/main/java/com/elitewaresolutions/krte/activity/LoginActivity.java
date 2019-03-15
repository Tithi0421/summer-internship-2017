package com.elitewaresolutions.krte.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.database.DatabaseHelper;
import com.elitewaresolutions.krte.database.DbQueries;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    long value;
    private Button signin;
    private TextView textView;
    private EditText usertext,passwordtext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
    private FirebaseAuth fbAuth;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    public String currentUserUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       SharedPreferenceUtils sharedPreferenceUtils=SharedPreferenceUtils.getInstance();
        String id=  sharedPreferenceUtils.getString(this,"key");

        if ( id != null ){
            Intent i1= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i1);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usertext=(EditText)findViewById(R.id.username);
        passwordtext=(EditText)findViewById(R.id.password);
        fbAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LOG_Login", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LOG_Login", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        addListenerOnButton();
        addListenerOnTextView();

    }
    public void addListenerOnButton() {
        signin = (Button) findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               DbQueries dbQueries=new DbQueries(LoginActivity.this);
                String name=(usertext.getText().toString());
                final String password=(passwordtext.getText().toString());
                fbAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("LOG_Login", "signInWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()){
                            Log.w("LOG_Login", "signInWithEmail", task.getException());
                            Snackbar.make(signin, "Emailid or Password is Invalid", Snackbar.LENGTH_LONG).show();
                        }
                        user = FirebaseAuth.getInstance().getCurrentUser() ;
                        String currentUserUid =  user.getUid().toString();

                        SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
                        sharedPreferenceUtils.setString(LoginActivity.this,"key",currentUserUid);
                        Log.d(currentUserUid," ");
                        Intent i1 = new Intent(LoginActivity.this, MainActivity.class);

                        startActivity(i1);
                        usertext.setText("");
                        passwordtext.setText("");
                    }
                });


            }
        });
    }
    public void addListenerOnTextView() {
        textView = (TextView) findViewById(R.id.sign);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i2= new Intent(LoginActivity.this,Main3Activity.class);
                startActivity(i2);
            }
        });
    }

}
