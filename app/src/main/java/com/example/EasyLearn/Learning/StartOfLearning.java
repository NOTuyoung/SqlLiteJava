package com.example.EasyLearn.Learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.EasyLearn.R;

public class StartOfLearning extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_start);

        startBtn = (Button) findViewById(R.id.startBtn);
    }

    public void onStartBtn(View view) {
        Intent intent = new Intent(this, WordsLearning.class);
        startActivity(intent);
    }
}
