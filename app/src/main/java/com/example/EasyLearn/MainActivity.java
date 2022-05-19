package com.example.EasyLearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.EasyLearn.Learning.StartOfLearning;
import com.example.EasyLearn.Modules.ModulesList;
import com.example.EasyLearn.Words.WordList;
import com.example.EasyLearn.db.DbManager;

public class MainActivity extends AppCompatActivity {

    public static DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DbManager(this);
    }


    public void onClickWordsList(View view) {
        Intent intent = new Intent(this, WordList.class);
        startActivity(intent);
    }

    public void onClickModulesList(View view) {
        Intent intent = new Intent(this, ModulesList.class);
        startActivity(intent);
    }


    public void onClickWordsLearning(View view) {
        Intent intent = new Intent(this, StartOfLearning.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                //open setting
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}