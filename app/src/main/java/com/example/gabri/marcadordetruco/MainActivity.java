package com.example.gabri.marcadordetruco;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Stack aStack = new Stack();
    Stack bStack = new Stack();

    private TextView scoreATextView;
    private int scoreA, scoreA_Minus, scoreB_Minus;
    private TextView scoreBTextView;
    private int scoreB;
    private Button a_1;
    private Button a_3;
    private Button a_6;
    private Button a_9;
    private Button a_12;
    private Button backA;

    private Button b_1;
    private Button b_3;
    private Button b_6;
    private Button b_9;
    private Button b_12;
    private Button backB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreA = 0;
        scoreATextView = findViewById(R.id.we_score);

        scoreB = 0;
        scoreBTextView = findViewById(R.id.they_score);

        a_1 = findViewById(R.id.increaseByOneAButton);
        a_3 = findViewById(R.id.increaseByThreeAButton);
        a_6 = findViewById(R.id.increaseBySixAButton);
        a_9 = findViewById(R.id.increaseByNineAButton);
        a_12 = findViewById(R.id.increaseByTwelveAButton);
        backA = findViewById(R.id.back_pA);

        b_1 = findViewById(R.id.increaseByOneBButton);
        b_3 = findViewById(R.id.increaseByThreeBButton);
        b_6 = findViewById(R.id.increaseBySixBButton);
        b_9 = findViewById(R.id.increaseByNineBButton);
        b_12 = findViewById(R.id.increaseByTwelveBButton);
        backB = findViewById(R.id.back_pB);

    }

    public void increaseByOneA(View view) {
        addScore("A", 1);
        checkWin();
    }

    public void increaseByThreeA(View view) {
        addScore("A", 3);
        checkWin();
    }

    public void increaseBySixA(View view) {
        addScore("A", 6);
        checkWin();
    }

    public void increaseByNineA(View view) {
        addScore("A", 9);
        checkWin();
    }

    public void increaseByTwelveA(View view) {
        addScore("A", 12);
        checkWin();
    }

    public void increaseByOneB(View view) {
        addScore("B", 1);
        checkWin();
    }

    public void increaseByThreeB(View view) {
        addScore("B", 3);
        checkWin();
    }

    public void increaseBySixB(View view) {
        addScore("B", 6);
        checkWin();
    }

    public void increaseByNineB(View view) {
        addScore("B", 9);
        checkWin();
    }

    public void increaseByTwelveB(View view) {
        addScore("B", 12);
        checkWin();

    }

    public void setEnabledTrue() {
        a_1.setEnabled(true);
        a_3.setEnabled(true);
        a_6.setEnabled(true);
        a_9.setEnabled(true);
        a_12.setEnabled(true);

        b_1.setEnabled(true);
        b_3.setEnabled(true);
        b_6.setEnabled(true);
        b_9.setEnabled(true);
        b_12.setEnabled(true);
    }

    public void SetEnabledFalse() {
        a_1.setEnabled(false);
        a_3.setEnabled(false);
        a_6.setEnabled(false);
        a_9.setEnabled(false);
        a_12.setEnabled(false);

        b_1.setEnabled(false);
        b_3.setEnabled(false);
        b_6.setEnabled(false);
        b_9.setEnabled(false);
        b_12.setEnabled(false);
    }

    public void reset(View v) {
        scoreA = 0;
        scoreB = 0;
        scoreATextView.setText(String.valueOf(scoreA));
        scoreBTextView.setText(String.valueOf(scoreB));
        setEnabledTrue();
        backA.setEnabled(true);
        backB.setEnabled(true);

        while (!aStack.empty())
        {
            aStack.pop();
        }

        while (!bStack.empty())
        {
            bStack.pop();
        }
    }

    public void checkWin() {

        if (scoreA >= 12) {
            scoreA = 12;
            scoreATextView.setText(String.valueOf(scoreA));
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Fim de jogo!");
            alertDialog.setMessage(R.string.we_won);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(false);
            alertDialog.show();
            SetEnabledFalse();
            backA.setEnabled(false);
            backB.setEnabled(false);

        }

        if (scoreB >= 12) {
            scoreB = 12;
            scoreBTextView.setText(String.valueOf(scoreB));
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Fim de jogo!");
            alertDialog.setMessage(R.string.they_won);
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(false);
            alertDialog.show();
            SetEnabledFalse();
            backA.setEnabled(false);
            backB.setEnabled(false);

        }
    }

    public void checkElevenHandA()
    {
        if (scoreA == 11)
        {
            a_3.setEnabled(false);
            a_6.setEnabled(false);
            a_9.setEnabled(false);
            a_12.setEnabled(false);
            Toast.makeText(getApplicationContext(), R.string.eleven_hand, Toast.LENGTH_SHORT).show();
        }
    }

    public void checkElevenHandB()
    {
        if (scoreB == 11)
        {
            b_3.setEnabled(false);
            b_6.setEnabled(false);
            b_9.setEnabled(false);
            b_12.setEnabled(false);
            Toast.makeText(getApplicationContext(), R.string.eleven_hand_b, Toast.LENGTH_SHORT).show();
        }
    }

    public void minusScoreA() {
            if (!aStack.empty()) {
                scoreA_Minus = Integer.parseInt(String.valueOf(aStack.lastElement()));
                scoreA = scoreA - scoreA_Minus;
                aStack.pop();
                scoreATextView.setText(String.valueOf(scoreA));
            }
            setEnabledTrue();
        }

    public void minusScoreB() {
        if (!bStack.empty()) {
            scoreB_Minus = Integer.parseInt(String.valueOf(bStack.lastElement()));
            scoreB = scoreB - scoreB_Minus;
            bStack.pop();
            scoreBTextView.setText(String.valueOf(scoreB));
        }
        setEnabledTrue();
    }


    public void addScore(String team, int score) {
        if (team.equals("A")) {
            aStack.push(score);
            scoreA += score;
            scoreATextView.setText(String.valueOf(scoreA));
            checkElevenHandA();
        } else {
            bStack.push(score);
            scoreB += score;
            scoreBTextView.setText(String.valueOf(scoreB));
            checkElevenHandB();
        }

    }

    public void backA(View view) {
        minusScoreA();
    }

    public void backB(View view) {
        minusScoreB();
    }
}
