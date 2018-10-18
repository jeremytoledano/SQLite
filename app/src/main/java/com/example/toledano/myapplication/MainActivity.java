package com.example.toledano.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper myDB ;
    EditText name;
    ListView listView;
    Button add, retrieve;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBHelper(this,"ORT",null,1);
        name = findViewById(R.id.name);
        add = findViewById(R.id.add);
        retrieve = findViewById(R.id.sql);
        listView = findViewById(R.id.list);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.insertItem(name.getText().toString(),"");
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ArrayList<String>> arr = myDB.getAllNames();
                adapter =
                        new ArrayAdapter(getApplicationContext(),R.layout.db_item,arr);
                listView.setAdapter(adapter);
            }
        });
    }
}
