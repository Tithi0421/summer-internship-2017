package com.elitewaresolutions.krte.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.database.DbQueries;
import com.elitewaresolutions.krte.model.UserAccount;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;

public class Main3Activity extends AppCompatActivity {
    private long id;
    private Button join;
    private EditText password;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private  EditText confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        password= (EditText)findViewById(R.id.pass);
        fname= (EditText)findViewById(R.id.fname);
        lname= (EditText)findViewById(R.id.lname);
        email= (EditText)findViewById(R.id.eid);
        confirm= (EditText)findViewById(R.id.conf);
        join = (Button)findViewById(R.id.join);
        checkPassword();
    }
    public void checkPassword() {
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidations();
            }
        });

    }

    private void checkValidations() {

        /*if(!TextUtils.isEmpty(fname.getText().toString())
                && !TextUtils.isEmpty(lname.getText().toString())
                && !TextUtils.isEmpty(email.getText().toString())
                && !TextUtils.isEmpty(password.getText().toString())
                && !TextUtils.isEmpty(confirm.getText().toString())){

        }*/

        if( fname.getText().toString().length() == 0 ) {
            fname.setError("This field is required!");
            fname.requestFocus();
            return;
        }
        if( lname.getText().toString().length() == 0 ) {
            lname.setError("This field is required!");
            lname.requestFocus();
            return;
        }
        if( email.getText().toString().length() == 0 ) {
            email.setError("This field is required!");
            email.requestFocus();
            return;
        }
            else{
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Emaid Id is not valid");
                    email.requestFocus();
                    return;
                }
            }

        if (password.getText().toString().length() == 0){
            password.setError("This field is required!");
            password.requestFocus();
            return;
        }
        else {
            if (password.getText().length() < 6 ) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(Main3Activity.this)
                                    .setTitle("Alert")
                                    .setMessage("Password must be greater than 6 letters")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    }
                });
                return;
            }

        }
        if( confirm.getText().toString().length() == 0 ) {
            confirm.setError("This field is required!");
            confirm.requestFocus();
            return;
        }
        else  {
            if (!password.getText().toString().equals(confirm.getText().toString())) {
                password.setError("Password does not match");
                password.requestFocus();
                return;
            }
            else {
                insertInDb(fname.getText().toString().trim(),lname.getText().toString().trim(),email.getText().toString().trim(),password.getText().toString().trim());
                fname.setText("");
                lname.setText("");
                email.setText("");
                password.setText("");
                confirm.setText("");


            }

        }

    }

    private void insertInDb(String fname, String lname, String email, String password) {
        UserAccount userAccount=new UserAccount();
        userAccount.setFname(fname);
        userAccount.setLname(lname);
        userAccount.setEmail(email);
        userAccount.setPass(password);

        DbQueries dbqueries=new DbQueries(this);
          id = dbqueries.addUserAccout(userAccount);

        SharedPreferenceUtils sharedPreferenceUtils=SharedPreferenceUtils.getInstance();
        sharedPreferenceUtils.setLong(this,"key",id);

        if(id>0) {
           Intent i1=new Intent(Main3Activity.this,MainActivity.class);
            startActivity(i1);

        }


    }

}

