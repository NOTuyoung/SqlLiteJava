package com.example.sqllitejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllitejava.db.DbManager;
import com.google.android.material.snackbar.Snackbar;

public class WordsAppend extends AppCompatActivity {
    EditText editText1 , editText2;
    DbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_append);
        dbManager = new DbManager(this);
        editText1 = (EditText) findViewById(R.id.editTextFirstWord);
        editText2 = (EditText) findViewById(R.id.editTextSecondWord);

    }


    public void onClickAppend(View view)
    {
        if (!Cheaker()) return;
        dbManager.openDb();
        dbManager.InsertToDb(editText1.getText().toString(),editText2.getText().toString(),null,null,null);
        dbManager.closeDb();
        PrintMessage("Слово "+ editText1.getText().toString() +" успешно добавлено");
        Clear();
    }

    private boolean Cheaker()
    {
        String text1 = editText1.getText().toString();
        String text2 = editText2.getText().toString();

        boolean flag = false;
        if(text1.equals("")) {
            editText1.setHintTextColor(Color.RED);
            flag = true;
        }
        if(text2.equals("")) {
            editText2.setHintTextColor(Color.RED);
            flag = true;
        }

        if(flag)
        {
            PrintMessage(getString(R.string.Error_emptyEditText));
            return false;
        }

        return  true;
    }

    private void Clear()
    {
        editText1.setText("");
        editText2.setText("");
        editText1.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));
        editText2.setHintTextColor(getResources().getColor(R.color.hint_foreground_material_light));

    }

    private void PrintMessage(String text)
    {
        View view = this.findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(0XFF555555);
        snackbar.setActionTextColor(0XFF0277BD);
        snackbar.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}