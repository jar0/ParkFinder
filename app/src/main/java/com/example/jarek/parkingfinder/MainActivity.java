package com.example.jarek.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void continueBtnClick(View view) {
        Intent intent = new Intent(this, TownListActivity.class);
        startActivity(intent);
    }

    public void singInBtnClick(View view) {
        Intent intent = new Intent(this, SingInActivity.class);
        startActivity(intent);
    }

    public void singUpBtnClick(View view) {
        Intent intent = new Intent(this, SingUpActivity.class);
        startActivity(intent);
    }
}
