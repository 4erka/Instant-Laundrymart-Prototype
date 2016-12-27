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

public class PetugasMain extends AppCompatActivity {

    private DatabaseHelper helperku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_main);
        Button login = (Button)findViewById(R.id.btLogin);
        login.setOnClickListener(clickHandler);
        Button regis = (Button)findViewById(R.id.btRegister);
        regis.setOnClickListener(clickHandler);
        helperku = new DatabaseHelper(this,"dbku.db",null,1);
    }
    View.OnClickListener clickHandler = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btLogin:
                    login_process();
                    break;
                case R.id.btRegister:
                    register_process();
                    break;
            }
        }
    };
    public void register_process(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.petugas_regis,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);
        final EditText username = (EditText) inputnya.findViewById(R.id.etUsername);
        final EditText password= (EditText) inputnya.findViewById(R.id.etPassword);
        final EditText nama = (EditText) inputnya.findViewById(R.id.etNama);
        final EditText nomorhp = (EditText) inputnya.findViewById(R.id.etNomorhp);
        final EditText alamat = (EditText) inputnya.findViewById(R.id.etAlamat);
        final EditText rekening = (EditText) inputnya.findViewById(R.id.etRekening);
        dialognya
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isNotExist = helperku.cekRegisPetugas(username.getText().toString());
                        if(isNotExist){
                            helperku.addPetugas(username.getText().toString(),password.getText().toString(),nama.getText().toString(),nomorhp.getText().toString(),alamat.getText().toString(),rekening.getText().toString());
                            Toast.makeText(getBaseContext(),"Registrasi Sukses!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else Toast.makeText(getBaseContext(),"Username Sudah Terpakai!",Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialognya.show();
    }
    public void login_process(){
        EditText username = (EditText) findViewById(R.id.etUsername);
        EditText password= (EditText) findViewById(R.id.etPassword);
        boolean isRight = helperku.cekLoginPetugas(username.getText().toString(),password.getText().toString());
        if(isRight){
            Toast.makeText(getBaseContext(),"Login Sukses!",Toast.LENGTH_SHORT).show();
            Intent intenku = new Intent(getBaseContext(),PetugasHome.class);
            intenku.putExtra("username",username.getText().toString());
            startActivityForResult(intenku,0);
        }
        else{
            Toast.makeText(getBaseContext(),"Username atau Password Salah!",Toast.LENGTH_SHORT).show();
        }
    }

}
