package com.example.jarek.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SingUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
    }

    public void registerBtnClick(View view) {

        Toast toast = Toast.makeText(this, "Zarejestrowano!",Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, SingInActivity.class);
        startActivity(intent);

    }

    public void clearAll(View view) {
        EditText imie = (EditText) findViewById(R.id.nameEditText);
        EditText nazwisko = (EditText) findViewById(R.id.sureNameEditText);
        EditText email = (EditText) findViewById(R.id.emailEditText);
        EditText haslo = (EditText) findViewById(R.id.passEditText);
        EditText rehaslo = (EditText) findViewById(R.id.rePassEditText);
        imie.setText("");
        nazwisko.setText("");
        email.setText("");
        haslo.setText("");
        rehaslo.setText("");
    }
}
