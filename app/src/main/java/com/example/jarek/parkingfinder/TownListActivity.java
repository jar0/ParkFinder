package com.example.jarek.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TownListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String object = "";
    private String [] town_list;
    private ListView listView;
    private EditText editText;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_list);

       // listView = (ListView) findViewById(R.id.townListView);
        editText = (EditText) findViewById(R.id.etTownName);
        town_list = getResources().getStringArray(R.array.miasta);
        initList();
//
//        town_list = getResources().getStringArray(R.array.miasta);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,town_list);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new ItemList());

        editText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });

    }

    public void searchItem(String textToSearch){
        for(String item:town_list){
            if(!item.contains(textToSearch)){
                itemList.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        listView = (ListView) findViewById(R.id.townListView);

        itemList = new ArrayList<>(Arrays.asList(town_list));


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemList());

    }

    public void searchTownBtnClick(View view) {
        Intent goMap = new Intent(TownListActivity.this, MapsActivity.class);
        editText = (EditText) findViewById(R.id.etTownName);
        String enterTownName = editText.getText().toString();
        goMap.putExtra("miasto",enterTownName);
        startActivity(goMap);
    }

    private class ItemList implements AdapterView.OnItemClickListener{
        Intent goMap = new Intent(TownListActivity.this, MapsActivity.class);
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            object = listView.getItemAtPosition(position).toString();
            goMap.putExtra("miasto", object);
            startActivity(goMap);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_town_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int id = item.getItemId();
//        switch (id) {
//            case R.id.action_help:
//                return true;
//            case R.id.action_about:
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
