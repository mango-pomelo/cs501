package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView[] txtWord;

    ArrayList<View> alphabetButtons;

    String word;
    String hint1;
    String hint2;

    boolean[] letterChosen;

    int score;
    int numHints;

@Override
public void onSaveInstanceState(Bundle savedInstanceState){
    super.onSaveInstanceState(savedInstanceState);

    savedInstanceState.putInt("score", score);
    savedInstanceState.putString("word",word);
    savedInstanceState.putString("hint1", hint1);
    savedInstanceState.putString("hint2", hint2);
    savedInstanceState.putBooleanArray("letterChosen", letterChosen);
    savedInstanceState.putInt("img", (Integer) img.getTag());
    Log.i("waner", img.getTag().toString());
    savedInstanceState.putInt("numHints", numHints);
    int numchar = txtWord.length;
    savedInstanceState.putInt("numchar", numchar);
    String intxt = "";
    for (int i =0; i<numchar; i++){
        intxt += txtWord[i].toString();
    }
    savedInstanceState.putString("intxt", intxt);
}

@Override
public void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    int score = savedInstanceState.getInt("score");
    String word = savedInstanceState.getString("word");
    String hint1 = savedInstanceState.getString("hint1");
    String hint2 = savedInstanceState.getString("hint2");
    boolean [] letterChosen = savedInstanceState.getBooleanArray("letterChosen");
    img.setTag(savedInstanceState.getInt("img"));
    Log.i("waner", img.getTag().toString());
    numHints = savedInstanceState.getInt("numHints");
    int numchar = savedInstanceState.getInt("numchar");
    String intxt = savedInstanceState.getString("intxt");
    for (int i = 0; i<numchar; i++){
        txtWord[i].setText(intxt.substring(i,i+1));
    }
}


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
        // init the matchstick man; reset the hint btn

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame();
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
        numHints = 0;
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

        // llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        txtWord = new TextView[word.length()];

        for (int i = 0; i < txtWord.length; i++) {
            txtWord[i] = new TextView(this);
            txtWord[i].setTextSize(32);
            txtWord[i].setText("_");
            llParams = new LinearLayout.LayoutParams(100+i, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtWord[i].setLayoutParams(llParams);
            llMain.addView(txtWord[i]);
        }

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            btnHint = new Button(this);
            btnHint.setText("Hint");
            btnHint.setTag("btnHint");
            // btnHint.setLayoutParams(llParams);
            llMain.addView(btnHint);

            btnHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numHints += 1;

                    switch (numHints) {
                        case 1:
                            Toast.makeText(MainActivity.this, "hint1: " + hint1, Toast.LENGTH_LONG).show();
                            nextDrawable();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, "hint2: " + hint2, Toast.LENGTH_LONG).show();
                            nextDrawable();
                            break;
                        case 3:
                            showOneLetter();
                            btnHint.setEnabled(false);
                            nextDrawable();
                    }
                }
            });
        }
    }
}
