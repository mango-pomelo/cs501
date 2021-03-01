package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
<<<<<<< HEAD
import android.widget.TextView;
=======
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>> e6110d031afc1d9ac264dd57dcb8cd06af396c9e

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Hashtable<String, String[]> words;
    private LinearLayout llMain;
    private LinearLayout.LayoutParams llParams;

    Button btnHint;
    Button btnNewGame;
    ImageView img;
<<<<<<< HEAD
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
=======
    TextView[] txtWord;

    ArrayList<View> alphabetButtons;

    String word;
    String hint1;
    String hint2;

    boolean[] letterChosen;

    int score;
    int numHints;
>>>>>>> e6110d031afc1d9ac264dd57dcb8cd06af396c9e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewGame = findViewById(R.id.btnNewGame);
        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.hangman_0);
        llMain = findViewById(R.id.llMain);

        alphabetButtons = (findViewById(R.id.tableLayout)).getTouchables();

        score = 0;
        numHints = 0;

<<<<<<< HEAD
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








=======
        words = new Hashtable<String, String[]>();
        String[] hints1 = {getResources().getString(R.string.hint1_1), getResources().getString(R.string.hint1_2)};
        words.put(getResources().getString(R.string.word1), hints1);
        String[] hints2 = {getResources().getString(R.string.hint2_1), getResources().getString(R.string.hint2_2)};
        words.put(getResources().getString(R.string.word2), hints2);
        String[] hints3 = {getResources().getString(R.string.hint3_1), getResources().getString(R.string.hint3_2)};
        words.put(getResources().getString(R.string.word3), hints3);
        String[] hints4 = {getResources().getString(R.string.hint4_1), getResources().getString(R.string.hint4_2)};
        words.put(getResources().getString(R.string.word4), hints4);
        String[] hints5 = {getResources().getString(R.string.hint5_1), getResources().getString(R.string.hint5_2)};
        words.put(getResources().getString(R.string.word5), hints5);

        createNewGame();

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            btnHint = new Button(this);
            btnHint.setText("Hint");
            btnHint.setTag("btnHint");
            btnHint.setLayoutParams(llParams);
            llMain.addView(btnHint);

            btnHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numHints += 1;

                    switch (numHints) {
                        case 1:
                            // show hint 1
                            break;
                        case 2:
                            // show hint 2
                            break;
                        case 3:
                            showOneLetter();
                            btnHint.setEnabled(false);
                    }
                }
            });
        }
>>>>>>> e6110d031afc1d9ac264dd57dcb8cd06af396c9e

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                initializeGame();
=======
                createNewGame();
>>>>>>> e6110d031afc1d9ac264dd57dcb8cd06af396c9e
            }
        });
    }


    private Button findButton(String letter) {
        for (View v : alphabetButtons) {
            if (v instanceof Button && ((Button) v).getText().toString().equals(letter)) {
                return (Button) v;
            }
        }
        return (Button) alphabetButtons.get(0);
    }

    private void showOneLetter() {
        for (int i = 0; i < word.length(); i++) {
            if (!letterChosen[i]) {
                selectLetter(findButton(word.substring(i, i+1)));
                break;
            }
        }
    }

    void calculateScore() {
        for (int i = 0; i < word.length(); i++) {
            if (letterChosen[i]) {
                String letter = word.substring(i, i+1);
                if (letter.equals("a") || letter.equals("e") || letter.equals("i") || letter.equals("o") || letter.equals("u")) {
                    score += 10;
                } else{
                    score += 5;
                }
            }
        }
    }

    public void selectLetter(View view) {
        Button buttonSelected = (Button) findViewById(view.getId());
        String letter = buttonSelected.getText().toString();

        buttonSelected.setClickable(false);
        buttonSelected.setTextColor(Color.GRAY);

        boolean correctLetter = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals(letter)) {
                txtWord[i].setText(word.substring(i, i+1));
                correctLetter = true;
                letterChosen[i] = true;
            }
        }

        if (!correctLetter) {
            nextDrawable();
        } else if (hasWon()) {
            endGame("win");
        }
    }

    boolean hasWon() {
        for (int i = 0; i < letterChosen.length; i++) {
            if (!letterChosen[i]) {
                return false;
            }
        }
        return true;
    }

    void nextDrawable() {
        int theID = (Integer) img.getTag();
        switch(theID){
            case R.drawable.hangman_0:
                img.setTag(R.drawable.hangman_1);
                img.setImageResource(R.drawable.hangman_1);
                break;
            case R.drawable.hangman_1:
                img.setTag(R.drawable.hangman_2);
                img.setImageResource(R.drawable.hangman_2);
                break;
            case R.drawable.hangman_2:
                img.setTag(R.drawable.hangman_3);
                img.setImageResource(R.drawable.hangman_3);
                break;
            case R.drawable.hangman_3:
                img.setTag(R.drawable.hangman_4);
                img.setImageResource(R.drawable.hangman_4);
                break;
            case R.drawable.hangman_4:
                img.setTag(R.drawable.hangman_5);
                img.setImageResource(R.drawable.hangman_5);
                break;
            case R.drawable.hangman_5:
                img.setTag(R.drawable.hangman_6);
                img.setImageResource(R.drawable.hangman_6);

                endGame("loss");
                break;
        }
    }

    void endGame(String outcome) {
        disableButtons();
        calculateScore();

        if (outcome.equals("win")) {
            Toast.makeText(this, "You win! Score: " + score, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You lose! Score: " + score, Toast.LENGTH_LONG).show();
        }
    }

    private void enableButtons() {
        for (View v : alphabetButtons) {
            if (v instanceof Button) {
                ((Button) v).setClickable(true);
                ((Button) v).setTextColor(Color.BLACK);
            }
        }
    }

    private void disableButtons() {
        for (View v : alphabetButtons) {
            if (v instanceof Button) {
                ((Button) v).setClickable(false);
                ((Button) v).setTextColor(Color.GRAY);
            }
        }
    }

    protected void createNewGame() {
        enableButtons();

        llMain.removeAllViewsInLayout();

        img.setTag(R.drawable.hangman_0);
        img.setImageResource(R.drawable.hangman_0);

        String[] keys = words.keySet().toArray(new String[words.size()]);
        word = keys[new Random().nextInt(keys.length)];
        hint1 = words.get(word)[0];
        hint2 = words.get(word)[1];

        letterChosen = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letterChosen[i] = false;
        }

        llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        txtWord = new TextView[word.length()];

        for (int i = 0; i < txtWord.length; i++) {
            txtWord[i] = new TextView(this);
            txtWord[i].setText("-");
            txtWord[i].setLayoutParams(llParams);
            llMain.addView(txtWord[i]);
        }
    }
}
