package com.example.whiteknight.laundrysystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PemilikMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_main);
        Button login = (Button)findViewById(R.id.btLogin);
        login.setOnClickListener(clickHandler);
    }
    View.OnClickListener clickHandler = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btLogin:
                    login_process();
                    break;
            }
        }
    };

    public void login_process(){
        EditText username = (EditText) findViewById(R.id.etUsername);
        EditText password= (EditText) findViewById(R.id.etPassword);
        if(username.getText().toString().equals("admin")&&password.getText().toString().equals("admin")){
            Toast.makeText(getBaseContext(),"Login Sukses!",Toast.LENGTH_SHORT).show();
            Intent intenku = new Intent(getBaseContext(),PemilikHome.class);
            startActivityForResult(intenku,0);
        }
        else{
            Toast.makeText(getBaseContext(),"Username atau Password Salah!",Toast.LENGTH_SHORT).show();
        }
    }

}
