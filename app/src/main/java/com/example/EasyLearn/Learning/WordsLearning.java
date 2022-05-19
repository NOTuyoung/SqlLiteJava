package com.example.EasyLearn.Learning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.EasyLearn.MainActivity;
import com.example.EasyLearn.R;
import com.example.EasyLearn.containers.Word;

import java.util.ArrayList;
import java.util.Random;

public class WordsLearning extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyApp";

    private ProgressBar progressBar;
    private TextView questionTextView;
    private Button answer1, answer2, answer3, answer4, answer5, answer6;

    private Random rnd;
    private AnswersGenerator answersGenerator;

    private ArrayList<Word> listOfWords;
    private Word question;
    private ArrayList<Word> answers;
    private int numberOfWords;
    private final int numberOfCounts = 3;
    private final int numberOfAnswers = 6;
    private int indexOfRight;
    private int progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        //Bundle arguments = getIntent().getExtras();

        // Test sample
        listOfWords = new ArrayList<>();
        listOfWords.add(new Word(0, "Dog", "Собака", null, null, null));
        listOfWords.add(new Word(1, "Cat", "Кошка", null, null, null));
        listOfWords.add(new Word(2, "Parrot", "Попугай", null, null, null));
        listOfWords.add(new Word(3, "Apple", "Яблоко", null, null, null));
        listOfWords.add(new Word(4, "Tomato", "Помидор", null, null, null));
        listOfWords.add(new Word(5, "Pain", "Боль", null, null, null));
        listOfWords.add(new Word(6, "Door", "Дверь", null, null, null));
        listOfWords.add(new Word(7, "Hat", "Шляпа", null, null, null));
        listOfWords.add(new Word(8, "Water", "Вода", null, null, null));
        listOfWords.add(new Word(9, "Book", "Книга", null, null, null));
        /*
        listOfWords.add(new Word(10, "Tree", "Дерево", null, null, null));
        listOfWords.add(new Word(11, "Chair", "Стул", null, null, null));
        listOfWords.add(new Word(12, "Table", "Стол", null, null, null));
        listOfWords.add(new Word(13, "Fish", "Рыба", null, null, null));
        listOfWords.add(new Word(14, "Mother", "Мама", null, null, null));
        listOfWords.add(new Word(15, "Family", "Семья", null, null, null));
        listOfWords.add(new Word(16, "Time", "Время", null, null, null));
        listOfWords.add(new Word(17, "Room", "Комната", null, null, null));
        listOfWords.add(new Word(18, "Window", "Окно", null, null, null));
        listOfWords.add(new Word(19, "Glass", "Стекло", null, null, null));
        */
        numberOfWords = listOfWords.size();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(numberOfWords * numberOfCounts);
        progress = 0;
        questionTextView = findViewById(R.id.questionTextView);
        answer1 = findViewById(R.id.answer1);
        answer2 =  findViewById(R.id.answer2);
        answer3 =  findViewById(R.id.answer3);
        answer4 =  findViewById(R.id.answer4);
        answer5 =  findViewById(R.id.answer5);
        answer6 =  findViewById(R.id.answer6);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        answer5.setOnClickListener(this);
        answer6.setOnClickListener(this);

        rnd = new Random();
        answersGenerator = new AnswersGenerator(listOfWords, numberOfAnswers);

        showNewWord();
    }

    private void showNewWord() {
        if (!listOfWords.isEmpty()) {
            cleanButtons();

            int n = rnd.nextInt(numberOfWords);
            question = listOfWords.get(n);
            answers = answersGenerator.generate(question);

            questionTextView.setText(question.getTranslation1());
            answer1.setText(answers.get(0).getTranslation2());
            answer2.setText(answers.get(1).getTranslation2());
            answer3.setText(answers.get(2).getTranslation2());
            answer4.setText(answers.get(3).getTranslation2());
            answer5.setText(answers.get(4).getTranslation2());
            answer6.setText(answers.get(5).getTranslation2());

            indexOfRight = rnd.nextInt(numberOfAnswers);
            switch (indexOfRight) {
                case 0:
                    answer1.setText(question.getTranslation2());
                    break;
                case 1:
                    answer2.setText(question.getTranslation2());
                    break;
                case 2:
                    answer3.setText(question.getTranslation2());
                    break;
                case 3:
                    answer4.setText(question.getTranslation2());
                    break;
                case 4:
                    answer5.setText(question.getTranslation2());
                    break;
                case 5:
                    answer6.setText(question.getTranslation2());
                    break;
                default:
                    break;
            }
        }
        else {
            setContentView(R.layout.activity_learning_end);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.answer1)
            checkAnswer(answer1, answer1.getText().toString());
        else if (id == R.id.answer2)
            checkAnswer(answer2, answer2.getText().toString());
        else if (id == R.id.answer3)
            checkAnswer(answer3, answer3.getText().toString());
        else if (id == R.id.answer4)
            checkAnswer(answer4, answer4.getText().toString());
        else if (id == R.id.answer5)
            checkAnswer(answer5, answer5.getText().toString());
        else if (id == R.id.answer6)
            checkAnswer(answer6, answer6.getText().toString());
    }

    private void checkAnswer(Button btn, String answer) {
        if (answer.equals(question.getTranslation2())) {
            progress++;
            progressBar.setProgress(progress);
            question.increaseCnt();

            btn.setBackgroundColor(Color.parseColor("#32CD32"));

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNewWord();
                }
            }, 1000);

            if (question.getCntOfRight() == 3) {
                listOfWords.remove(question);
                numberOfWords--;
            }
        }
        else {
            progress -= question.getCntOfRight();
            progressBar.setProgress(progress);
            question.resetCnt();

            btn.setBackgroundColor(Color.parseColor("#FF0000"));
            switch(indexOfRight) {
                case 0:
                    answer1.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
                case 1:
                    answer2.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
                case 2:
                    answer3.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
                case 3:
                    answer4.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
                case 4:
                    answer5.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
                case 5:
                    answer6.setBackgroundColor(Color.parseColor("#32CD32"));
                    break;
            }

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNewWord();
                }
            }, 1000);
        }
    }

    public void onReturnBtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void cleanButtons() {
        answer1.setBackgroundColor(Color.parseColor("#C0C0C0"));
        answer2.setBackgroundColor(Color.parseColor("#C0C0C0"));
        answer3.setBackgroundColor(Color.parseColor("#C0C0C0"));
        answer4.setBackgroundColor(Color.parseColor("#C0C0C0"));
        answer5.setBackgroundColor(Color.parseColor("#C0C0C0"));
        answer6.setBackgroundColor(Color.parseColor("#C0C0C0"));
    }
}
