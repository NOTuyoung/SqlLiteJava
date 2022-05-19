package com.example.EasyLearn.Learning;

import android.util.Log;

import com.example.EasyLearn.containers.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AnswersGenerator {
    private static final String TAG = "MyApp";
    private final Random rnd;
    private final ArrayList<Word> list;
    private final int quantity;
    private final int numberOfAnswers;

    public AnswersGenerator(ArrayList<Word> list, int numberOfAnswers) {
        rnd = new Random();
        this.list = new ArrayList<>(list);
        this.quantity = list.size();
        this.numberOfAnswers = numberOfAnswers;
    }

    public ArrayList<Word> generate(Word question) {
        ArrayList<Word> answers = new ArrayList<>();
        int n;

        ArrayList<Integer> randList = new ArrayList<Integer>(quantity);
        for (int i = 0; i < quantity; i++)
            randList.add(i);
        Collections.shuffle(randList);

        byte size = 0;
        for (int i = 0; size < numberOfAnswers; i++) {
            n = randList.get(i);
            Word answer = list.get(n);
            if (!answer.equals(question)) {
                Log.i(TAG, answer.getTranslation1() + " " + answer.getTranslation2());
                answers.add(answer);
                size++;
            }
            else {
                Log.i(TAG, "Question == answer");
            }
        }

        return answers;
    }
}
