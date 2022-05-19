package com.example.EasyLearn.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EasyLearn.MainActivity;
import com.example.EasyLearn.R;
import com.example.EasyLearn.Words.WordEdit;
import com.example.EasyLearn.Words.WordInfo;
import com.example.EasyLearn.containers.ShortTable;
import com.example.EasyLearn.db.DbManager;

import java.util.ArrayList;
import java.util.List;

public class WordRecycleAdapter extends RecyclerView.Adapter<WordRecycleAdapter.ViewHolder>   {

    Activity activity;
    List<ShortTable> arrayList;
    TextView tvEmpty;
    WordViewModel wordViewModel;
    boolean isEnable = false;
    List<ShortTable> selectList = new ArrayList<>();
    ActionMode actionMode;
    DbManager db = MainActivity.dbManager;
    MenuItem editmenu;


    public WordRecycleAdapter(Activity activity, List<ShortTable> arrayList, TextView tvEmpty) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.tvEmpty = tvEmpty;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);
        wordViewModel = ViewModelProviders.of((FragmentActivity) activity)
                .get(WordViewModel.class);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getText());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!isEnable)
                {
                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater menuInflater = mode.getMenuInflater();
                            menuInflater.inflate(R.menu.menu_recycle_select,menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                            isEnable = true;
                            editmenu = menu.getItem(1);
                            ClickItem(holder);
                            wordViewModel.getText().observe((LifecycleOwner) activity
                                    , new Observer<String>() {
                                        @Override
                                        public void onChanged(String s) {
                                            mode.setTitle(String.format("%s Selected",s));
                                        }
                                    });



                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                            int id = item.getItemId();
                            switch (id)
                            {
                                case R.id.menu_delete:

                                    db.openDb();
                                    for (ShortTable s : selectList){
                                        arrayList.remove(s);
                                        db.RemoveWord(s.getId());
                                    }
                                    db.closeDb();
                                    if (arrayList.size() == 0){
                                        tvEmpty.setVisibility(View.VISIBLE);
                                    }
                                    mode.finish();

                                    break;

                                case  R.id.menu_edit:

                                    String word_id = selectList.get(0).getId();
                                    Intent intent = new Intent(activity, WordEdit.class);
                                    intent.putExtra("word_id",word_id);
                                    activity.startActivity(intent);
                                    actionMode.finish();

                            }

                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            isEnable = false;
                            selectList.clear();
                            notifyDataSetChanged();


                        }
                    };
                    actionMode = ((AppCompatActivity) view.getContext() ).startActionMode(callback);
                }
                else {
                    ClickItem(holder);
                }
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEnable){
                    ClickItem(holder);
                }
                else {
                    String word_id = arrayList.get(holder.getAdapterPosition()).getId();
                    Intent intent = new Intent(activity, WordInfo.class);
                    intent.putExtra("word_id",word_id);
                    activity.startActivity(intent);
                }
            }
        });


        holder.ivCheckBox.setVisibility(View.GONE);
        holder.itemView.setBackgroundColor(Color.TRANSPARENT);



    }

    private void ClickItem(ViewHolder holder) {

        ShortTable s = arrayList.get(holder.getAdapterPosition());
        //if item was not selected
        if (holder.ivCheckBox.getVisibility()  == View.GONE){

            selectList.add(s);
            if(selectList.size()!=1){
                editmenu.setVisible(false);
            }
            holder.setSelection(true);

        }
        //if item was selected
        else{
            selectList.remove(s);
            if(selectList.size()==0){
                actionMode.finish();
                return;
            }
            if(selectList.size()==1){
                editmenu.setVisible(true);
            }
            holder.setSelection(false);
        }

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView ivCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            ivCheckBox = itemView.findViewById(R.id.iv_check_box);

        }

        public void setSelection(boolean isSelection){
            if(isSelection){
                ivCheckBox.setVisibility(View.VISIBLE);
                itemView.setBackgroundColor(Color.LTGRAY);
            }
            else{
                ivCheckBox.setVisibility(View.GONE);
                itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
}
