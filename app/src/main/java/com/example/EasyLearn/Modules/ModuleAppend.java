package com.example.EasyLearn.Modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.EasyLearn.R;
import com.example.EasyLearn.db.DbManager;

public class ModuleAppend extends AppCompatActivity {

    DbManager dbManager = new DbManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_append);


    }



}