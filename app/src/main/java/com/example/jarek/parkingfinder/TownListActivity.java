package com.example.jarek.parkingfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TownListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_list);

        ListView listView = (ListView) findViewById(R.id.townListView);

        String [] town_list;
        town_list = getResources().getStringArray(R.array.miasta);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,town_list);
        listView.setAdapter(adapter);

//        Spinner spinner = (Spinner) findViewById(R.id.townSpinner);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.miasta,
//                android.R.layout.simple_selectable_list_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

//        getResources().getStringArray(R.array.miasta);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_town_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_help:
                return true;
            case R.id.action_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void townBtnClick(View view) {

    }
}
