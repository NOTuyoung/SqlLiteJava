package com.example.sqllitejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sqllitejava.db.DbManager;

import java.util.List;

public class WordsList extends AppCompatActivity {

    List<String> words;
    DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        TextView selection = findViewById(R.id.selection);
        ListView listView = (ListView)findViewById(R.id.countriesList);

        dbManager = new DbManager(this);
        dbManager.openDb();
        words = dbManager.getTranslations1();

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, words);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

                String selectedItem = words.get(position);
                Word word = dbManager.getWordByTranslation1(selectedItem);
                selection.setText(word.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.openDb();
    }

    @Override
    protected void onDestroy() {
        dbManager.closeDb();
        super.onDestroy();
    }



}