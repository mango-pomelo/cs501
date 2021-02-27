package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Hashtable<String, String[]> word;
    Random rand = new Random();
    int upperbound;
    int next_int;
    String cur_word;
    String cur_hint1;
    String cur_hint2;
    Button btnNewGame;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word = new Hashtable<String, String[]>();

        String[] curhints1 = {getResources().getString(R.string.hint1_1), getResources().getString(R.string.hint1_2)};
        word.put(getResources().getString(R.string.word1), curhints1);
        String[] curhints2 = {getResources().getString(R.string.hint2_1), getResources().getString(R.string.hint2_2)};
        word.put(getResources().getString(R.string.word2), curhints2);
        String[] curhints3 = {getResources().getString(R.string.hint3_1), getResources().getString(R.string.hint3_2)};
        word.put(getResources().getString(R.string.word3), curhints3);
        String[] curhints4 = {getResources().getString(R.string.hint4_1), getResources().getString(R.string.hint4_2)};
        word.put(getResources().getString(R.string.word4), curhints4);
        String[] curhints5 = {getResources().getString(R.string.hint5_1), getResources().getString(R.string.hint5_2)};
        word.put(getResources().getString(R.string.word5), curhints5);


        String[] keys = word.keySet().toArray(new String[word.size()]);
        String key = keys[new Random().nextInt(keys.length)];
        cur_hint1 = word.get(key)[0];
        cur_hint2 = word.get(key)[1];

        btnNewGame = findViewById(R.id.btnNewGame);
        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.hangman_0);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.hangman_0);
            }
        });
    }
}