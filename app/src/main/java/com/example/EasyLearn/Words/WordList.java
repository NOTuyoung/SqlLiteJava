package com.example.EasyLearn.Words;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.EasyLearn.Adapter.WordRecycleAdapter;
import com.example.EasyLearn.R;
import com.example.EasyLearn.containers.ShortTable;
import com.example.EasyLearn.db.DbManager;
import java.util.ArrayList;
import java.util.List;

public class WordList extends AppCompatActivity {


    public static boolean isWordAppend = false;
    RecyclerView recyclerView;
    TextView tvEmpty;
    List<ShortTable> words = new ArrayList<>();
    WordRecycleAdapter adapter;
    DbManager dbManager = new DbManager(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        recyclerView = findViewById(R.id.recycle_view);
        tvEmpty = findViewById(R.id.tv_empty);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WordRecycleAdapter(this, words, tvEmpty);
        recyclerView.setAdapter(adapter);

        LoadData();
    }


    @Override
    protected void onResume() {

        if (isWordAppend){
            words.clear();
            adapter.notifyDataSetChanged();
            LoadData();
            isWordAppend = false;
        }
        super.onResume();

    }

    private void LoadData()
    {
        dbManager.openDb();

        for (ShortTable word: dbManager.getShortWords()) {
            words.add(word);
            adapter.notifyItemInserted(words.size() - 1);
        }
        dbManager.closeDb();

    }





    public void onClickAdd(View view) {

        Intent intent = new Intent(this, WordAppend.class);
        startActivity(intent);
    }






}
