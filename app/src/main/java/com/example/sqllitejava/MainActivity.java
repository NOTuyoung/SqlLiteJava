package com.example.sqllitejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqllitejava.db.DbManager;
import com.example.sqllitejava.db.MyDbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClickWordsAppend(View view)
    {
        Intent intent = new Intent(this,WordsAppend.class);
        startActivity(intent);
    }
    public void onClickWordsList(View view)
    {
        Intent intent = new Intent(this,WordsList.class);
        startActivity(intent);
    }




}