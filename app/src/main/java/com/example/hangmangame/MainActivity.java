package com.example.hangmangame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Hashtable<String, String[]> words;
    private LinearLayout llMain;
    private LinearLayout.LayoutParams llParams;

    Button btnNewGame;
    ImageView img;
    TextView[] txtWord;

    String word;
    String hint1;
    String hint2;
    boolean[] chosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewGame = findViewById(R.id.btnNewGame);
        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.hangman_0);
        llMain = findViewById(R.id.llMain);

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

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGame();
            }
        });
    }


    public void selectLetter(View view) {
        Button buttonSelected = (Button) findViewById(view.getId());
        String letter = buttonSelected.getText().toString();

        for (int i = 0; i < chosen.length; i++) {
            if (word.substring(i, i + 1).equals(letter)) {
                chosen[i] = true;
                txtWord[i].setText(word.substring(i, i+1));
            }
        }

        buttonSelected.setClickable(false);
        buttonSelected.setTextColor(Color.RED);
    }

    private void enableButtons() {
        TableLayout buttonTable = findViewById(R.id.tableLayout);
        ArrayList<View> layoutButtons = buttonTable.getTouchables();

        for (View v : layoutButtons) {
            if (v instanceof Button) {
                System.out.println(((Button) v).getText().toString());
                ((Button) v).setClickable(true);
                ((Button) v).setTextColor(Color.BLACK);
            }
        }
    }

    protected void createNewGame() {
        enableButtons();
        llMain.removeAllViewsInLayout();

        img.setImageResource(R.drawable.hangman_0);

        String[] keys = words.keySet().toArray(new String[words.size()]);
        word = keys[new Random().nextInt(keys.length)];
        hint1 = words.get(word)[0];
        hint2 = words.get(word)[1];

        chosen = new boolean[word.length()];
        for (int i = 0; i < chosen.length; i++) {
            chosen[i] = false;
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