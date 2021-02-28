package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView txtDisplay;
    char[] wordDisplayCharArray;
    String wordToGuess;

    String revealLetter(char letter, String wordToGuess){
        // reveal all the letter in the current_word_to_guess
        int idx = wordToGuess.indexOf(letter);
        wordDisplayCharArray = wordToGuess.toCharArray();
        while(idx>=0){
            wordDisplayCharArray[idx] = wordToGuess.charAt(idx);
            idx = wordToGuess.indexOf(letter, idx+1);
        }
        return String.valueOf(wordDisplayCharArray);
    }


    void initializeGame(){
        // randomly get a word to guess
        String[] keys = word.keySet().toArray(new String[word.size()]);
        String key = keys[new Random().nextInt(keys.length)]; // current word for guess
        wordToGuess = key;
        cur_hint1 = word.get(key)[0]; // current hint 1
        cur_hint2 = word.get(key)[1]; // current hint 2

        // remove current word from the hashtable so it won't appear again when we reset the game
        word.remove(key);

        // init the displayed word with all underscores
        wordDisplayCharArray = key.toCharArray();
        for(int i=0; i<wordDisplayCharArray.length; i++){
            wordDisplayCharArray[i]='_';
        }
        // then turn the chararray to string
        String worddisplayed  = String.valueOf(wordDisplayCharArray);
        // make this visible on the txtDisplay
        txtDisplay.setText(worddisplayed);
        // init the matchstick man pictures
        btnNewGame = findViewById(R.id.btnNewGame);
        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.hangman_0);

    }
    void nextDrawable(){
        int theID = (Integer)img.getTag();
        switch(theID){
            case R.drawable.hangman_0:
                img.setImageResource(R.drawable.hangman_1);
                break;
            case R.drawable.hangman_1:
                img.setImageResource(R.drawable.hangman_2);
                break;
            case R.drawable.hangman_2:
                img.setImageResource(R.drawable.hangman_3);
                break;
            case R.drawable.hangman_3:
                img.setImageResource(R.drawable.hangman_4);
                break;
            case R.drawable.hangman_4:
                img.setImageResource(R.drawable.hangman_5);
                break;
            case R.drawable.hangman_5:
                img.setImageResource(R.drawable.hangman_6);
                // should return lose message and end this game here
                break;
        }
    }

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

        txtDisplay = (TextView) findViewById(R.id.txtDisplay);

        initializeGame();

        Button btn_a = (Button) findViewById(R.id.btn_a);
        Button btn_b = (Button) findViewById(R.id.btn_b);
        Button btn_c = (Button) findViewById(R.id.btn_c);
        Button btn_d = (Button) findViewById(R.id.btn_d);
        Button btn_e = (Button) findViewById(R.id.btn_e);
        Button btn_f = (Button) findViewById(R.id.btn_f);
        Button btn_g = (Button) findViewById(R.id.btn_g);
        Button btn_h = (Button) findViewById(R.id.btn_h);
        Button btn_i = (Button) findViewById(R.id.btn_i);
        Button btn_j = (Button) findViewById(R.id.btn_j);
        Button btn_k = (Button) findViewById(R.id.btn_k);
        Button btn_l = (Button) findViewById(R.id.btn_l);
        Button btn_m = (Button) findViewById(R.id.btn_m);
        Button btn_n = (Button) findViewById(R.id.btn_n);
        Button btn_o = (Button) findViewById(R.id.btn_o);
        Button btn_p = (Button) findViewById(R.id.btn_p);
        Button btn_q = (Button) findViewById(R.id.btn_q);
        Button btn_r = (Button) findViewById(R.id.btn_r);
        Button btn_s = (Button) findViewById(R.id.btn_s);
        Button btn_t = (Button) findViewById(R.id.btn_t);
        Button btn_u = (Button) findViewById(R.id.btn_u);
        Button btn_v = (Button) findViewById(R.id.btn_v);
        Button btn_w = (Button) findViewById(R.id.btn_w);
        Button btn_x = (Button) findViewById(R.id.btn_x);
        Button btn_y = (Button) findViewById(R.id.btn_y);
        Button btn_z = (Button) findViewById(R.id.btn_z);

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordToGuess.indexOf('a')>=0){
                    String newstr = revealLetter('a',wordToGuess);
                    txtDisplay.setText(newstr);
                } else{
                    nextDrawable();
                }
            }
        });









        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeGame();
            }
        });
    }
}