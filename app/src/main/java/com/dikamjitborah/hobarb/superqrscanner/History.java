package com.dikamjitborah.hobarb.superqrscanner;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class History extends AppCompatActivity {

   public ArrayList<String> urls = new ArrayList<>();
    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listView_history);
        textView = findViewById(R.id.textView_history);
        urls = new ArrayList<>();
        urls.add("yo");


        readPreferences();

       if(urls == null)
        {
            urls = new ArrayList<>();
            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
       else
       {
           textView.setVisibility(View.INVISIBLE);
           listView.setVisibility(View.VISIBLE);
           ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item_1, urls);
           listView.setAdapter(arrayAdapter);


           //Toast.makeText(this, "" + urls.toString(), Toast.LENGTH_SHORT).show();
       }
    }
    private void readPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("HISTORY", MODE_PRIVATE);
        String theUrls = sharedPreferences.getString("HISTORY_LIST","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        urls = gson.fromJson(theUrls, type);
    }

}