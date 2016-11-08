package com.example.jarek.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SingInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
    }

    public void sInBtnClick(View view) {
        Toast toast = Toast.makeText(this, "Zalogowano!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, TownListActivity.class);
        startActivity(intent);
    }
}
