package com.example.keepinmind;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean gameActive;
    int easyScore = 0, normalScore = 0, proScore = 0, easyHighScore = 0, normalHighScore = 0, proHighScore = 0, easyLvl = 1, normalLvl = 0, proLvl = 0, gameSpeed;
    short nrBlocksEasy, nrBlocksNormal, nrBlocksPro;

    List <Integer> sequences = new ArrayList<Integer>();
    int j, arrayPos = 0;
    
    ConstraintLayout easyConstraint, normalConstraint, proConstraint, settingLayout;
    LinearLayout playChangeLayout;
    GridLayout easyGrid, normalGrid, proGrid;
    TextView easyShowScore, easyShowHighScore, normalShowScore, normalShowHighScore, proShowScore, proShowHighScore, hint, easyLvlSetting, normalLvlSetting, proLvlSetting, slowSpeedSetting, normalSpeedSetting, fastSpeedSetting;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadHighScores();
        loadSettings();
    }

    public void createSequence(){
        Random r = new Random();
        if (easyLvl == 1) {
            nrBlocksEasy = (short) r.nextInt(4);
            sequences.add(Integer.valueOf(nrBlocksEasy));
        } else if (normalLvl == 1) {
            nrBlocksNormal = (short) r.nextInt(9);
            sequences.add(Integer.valueOf(nrBlocksNormal));
        } else if (proLvl == 1) {
            nrBlocksPro = (short) r.nextInt(16);
            sequences.add(Integer.valueOf(nrBlocksPro));
        }
        showSequence();
    }

    public void showSequence(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < sequences.size(); i++){
                    j = i;

                    if (easyLvl == 1) {
                        Button sequenceBtn = (Button) easyGrid.getChildAt(sequences.get(i));
                        int color = getResources().obtainTypedArray(R.array.easyLvlColor).getColor(Integer.parseInt(sequenceBtn.getTag().toString()),0);
                        if (i != 0) {
                            int finalI = i;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                                        }, finalI * gameSpeed);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {sequenceBtn.setBackgroundColor(color);}
                                        }, (finalI + 1) * gameSpeed);
                                }
                            },i * gameSpeed);
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                            }, i * gameSpeed);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {sequenceBtn.setBackgroundColor(color);}
                            }, (i+1) * gameSpeed);}

                    } else if (normalLvl == 1) {
                        Button sequenceBtn = (Button) normalGrid.getChildAt(sequences.get(i));
                        int color = getResources().obtainTypedArray(R.array.normalLvlColor).getColor(Integer.parseInt(sequenceBtn.getTag().toString()),0);
                        if (i != 0) {
                            int finalI = i;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                                    }, finalI * gameSpeed);

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {sequenceBtn.setBackgroundColor(color);}
                                    }, (finalI +1) * gameSpeed);
                                }
                            },i * gameSpeed);
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                            }, i * gameSpeed);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {sequenceBtn.setBackgroundColor(color);}
                            }, (i+1) * gameSpeed);}

                    } else if (proLvl == 1) {
                        Button sequenceBtn = (Button) proGrid.getChildAt(sequences.get(i));
                        int color = getResources().obtainTypedArray(R.array.proLvlColor).getColor(Integer.parseInt(sequenceBtn.getTag().toString()),0);
                        if (i != 0) {
                            int finalI = i;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                                    }, finalI * gameSpeed);

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {sequenceBtn.setBackgroundColor(color);}
                                    }, (finalI +1) * gameSpeed);
                                }
                            },i * gameSpeed);
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    sequenceBtn.setBackgroundColor(getResources().getColor(R.color.black));}
                            }, i * gameSpeed);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {sequenceBtn.setBackgroundColor(color);}
                            }, (i+1) * gameSpeed);}}
                }
                if (j != 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() { gameActive = true;
                        hint.setText("");}
                        }, ((2*j) + 1) * gameSpeed);
                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() { gameActive = true;
                            hint.setText("");}
                    }, (j + 1) * gameSpeed);
                }
            }
            },gameSpeed + 150);
    }

    public void checkSequences(View view){
        int color;
        Button currentBtn = (Button) view;
        Integer currentBtnTag = Integer.parseInt(currentBtn.getTag().toString());

        if (gameActive) {
            if (easyLvl == 1){
                color = getResources().obtainTypedArray(R.array.easyLvlColor).getColor(currentBtnTag,0);
                currentBtn.setBackgroundColor(getResources().getColor(R.color.white));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentBtn.setBackgroundColor(color);
                    }
                },200);
            }
            Log.i(String.valueOf(sequences),"");
            if (currentBtnTag.equals(sequences.get(arrayPos))) {
                arrayPos++;
                if (sequences.size() == arrayPos) {
                    createSequence();
                    if (easyLvl == 1) {
                        easyScore++;
                        easyShowScore.setText("Score: " + easyScore);
                    } else if (normalLvl == 1) {
                        normalScore++;
                        normalShowScore.setText("Score: " + normalScore);
                    } else if (proLvl == 1) {
                        proScore++;
                        proShowScore.setText("Score: " + proScore);
                    }
                    arrayPos = 0;
                    gameActive = false;
                }
            } else {
                gameActive = false;
                playAgain.setVisibility(View.VISIBLE);
                saveHighScores();
                loadHighScores();
            }
        } else if (playAgain.getVisibility() == View.INVISIBLE){
            hint.setText("Please wait till the end!");
        }
    }

    public void saveHighScores(){
        SharedPreferences saveHighScores = getSharedPreferences("HighScores", 0);
        SharedPreferences.Editor editor = saveHighScores.edit();
        if (easyScore > easyHighScore){ editor.putInt("easyHighScore", easyScore);
        hint.setText("New highscore!");}
        if (normalScore > normalHighScore){ editor.putInt("normalHighScore", normalScore);
        hint.setText("New highscore!");}
        if (proScore > proHighScore){ editor.putInt("proHighScore", proScore);
        hint.setText("New highscore!");}
        editor.commit();
    }

    public void loadHighScores(){
        SharedPreferences loadHighScores = getSharedPreferences("HighScores", 0);
        easyHighScore = loadHighScores.getInt("easyHighScore", 0);
        easyShowHighScore.setText("Highscore: " + easyHighScore);
        normalHighScore = loadHighScores.getInt("normalHighScore", 0);
        normalShowHighScore.setText("Highscore: " + normalHighScore);
        proHighScore = loadHighScores.getInt("proHighScores", 0);
        proShowHighScore.setText("Highscore: " + proHighScore);
    }

    public void saveSettings(){
        SharedPreferences saveSettings = getSharedPreferences("setting",0);
        SharedPreferences.Editor editor = saveSettings.edit();
        editor.putInt("easyLvl", easyLvl);
        editor.putInt("normalLvl", normalLvl);
        editor.putInt("proLvl", proLvl);
        editor.putInt("speed", gameSpeed);
        editor.commit();
    }

    public void loadSettings(){
        SharedPreferences loadSettings = getSharedPreferences("setting", 0);
        easyLvl = loadSettings.getInt("easyLvl", 0);
        normalLvl = loadSettings.getInt("normalLvl", 0);
        proLvl = loadSettings.getInt("proLvl", 0);
        gameSpeed = loadSettings.getInt("speed",0);
    }

    public void initViews(){
        easyConstraint = findViewById(R.id.easyLayout);
        normalConstraint = findViewById(R.id.normalLayout);
        proConstraint = findViewById(R.id.proLayout);
        playChangeLayout = findViewById(R.id.playChangeLayout);
        settingLayout = findViewById(R.id.settingLayout);

        easyGrid = findViewById(R.id.easy);
        normalGrid = findViewById(R.id.normal);
        proGrid = findViewById(R.id.pro);

        easyShowScore = findViewById(R.id.easyScore);
        easyShowScore.setText("Score: " + easyScore);
        easyShowHighScore = findViewById(R.id.easyHighScore);
        normalShowScore = findViewById(R.id.normalScore);
        normalShowScore.setText("Score: " + normalScore);
        normalShowHighScore = findViewById(R.id.normalHighScore);
        proShowScore = findViewById(R.id.proScore);
        proShowScore.setText("Score: " + proScore);
        proShowHighScore = findViewById(R.id.proHighScore);
        hint = findViewById(R.id.hintTxt);

        playAgain = findViewById(R.id.playAgain);

        easyLvlSetting = findViewById(R.id.easyLvlSetting);
        normalLvlSetting = findViewById(R.id.normalLvlSetting);
        proLvlSetting = findViewById(R.id.proLvlSetting);
        slowSpeedSetting = findViewById(R.id.slowSpeedSetting);
        normalSpeedSetting = findViewById(R.id.normalSpeedSetting);
        fastSpeedSetting = findViewById(R.id.fastSpeedSetting);
    }

    public void playBtn(View view){
        loadHighScores();
        loadSettings();
        openLevel();
        createSequence();
        playChangeLayout.setVisibility(View.INVISIBLE);
        settingLayout.setVisibility(View.INVISIBLE);
    }

    public void playAgainBtn(View view){
        playAgain.setVisibility(View.INVISIBLE);
        arrayPos = 0;
        easyScore = 0;
        easyShowScore.setText("Score: " + easyScore);
        normalScore = 0;
        normalShowScore.setText("Score: " + normalScore);
        proScore = 0;
        proShowScore.setText("Score: " + proScore);
        sequences.clear();
        createSequence();
    }

    public void openLevel(){
        if(easyLvl == 1){ easyConstraint.setVisibility(View.VISIBLE);
        } else{ easyConstraint.setVisibility(View.INVISIBLE); }
        if(normalLvl == 1){ normalConstraint.setVisibility(View.VISIBLE);
        } else{ normalConstraint.setVisibility(View.INVISIBLE); }
        if(proLvl == 1){ proConstraint.setVisibility(View.VISIBLE);
        } else{ proConstraint.setVisibility(View.INVISIBLE); }
    }

    public void openSettings(View view){
        settingLayout.setVisibility(View.VISIBLE);
        playChangeLayout.setVisibility(View.INVISIBLE);
    }

    public void chooseLvl(View view){
        TextView currentLvl = (TextView) view;
        int currentBtnTag = Integer.parseInt(currentLvl.getTag().toString());
        if(currentBtnTag == 0){
            easyLvlSetting.setTextColor(getResources().getColor(R.color.green));
            normalLvlSetting.setTextColor(getResources().getColor(R.color.red));
            proLvlSetting.setTextColor(getResources().getColor(R.color.red));
            easyLvl = 1;
            normalLvl = 0;
            proLvl = 0;
            saveSettings();
        }else if(currentBtnTag == 1){
            easyLvlSetting.setTextColor(getResources().getColor(R.color.red));
            normalLvlSetting.setTextColor(getResources().getColor(R.color.green));
            proLvlSetting.setTextColor(getResources().getColor(R.color.red));
            easyLvl = 0;
            normalLvl = 1;
            proLvl = 0;
            saveSettings();
        }else if(currentBtnTag == 2){
            easyLvlSetting.setTextColor(getResources().getColor(R.color.red));
            normalLvlSetting.setTextColor(getResources().getColor(R.color.red));
            proLvlSetting.setTextColor(getResources().getColor(R.color.green));
            easyLvl = 0;
            normalLvl = 0;
            proLvl = 1;
            saveSettings();
        }
    }

    public void chooseSpeed(View view){
        TextView currentSpeed = (TextView) view;
        int currentBtnTag = Integer.parseInt(currentSpeed.getTag().toString());
        if(currentBtnTag == 0){
            slowSpeedSetting.setTextColor(getResources().getColor(R.color.green));
            normalSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            fastSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            gameSpeed = 500;
            saveSettings();
        }
        if(currentBtnTag == 1){
            slowSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            normalSpeedSetting.setTextColor(getResources().getColor(R.color.green));
            fastSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            gameSpeed = 350;
            saveSettings();
        }
        if(currentBtnTag == 2){
            slowSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            normalSpeedSetting.setTextColor(getResources().getColor(R.color.red));
            fastSpeedSetting.setTextColor(getResources().getColor(R.color.green));
            gameSpeed = 200;
            saveSettings();
        }
    }
}