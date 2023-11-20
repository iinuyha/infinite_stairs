package com.example.infinite_stairs;


import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager {
    private static final String PREF_NAME = "HighScorePrefs";
    private static final String HIGH_SCORE_KEY = "HighScore";

    private static HighScoreManager instance;
    private SharedPreferences sharedPreferences;

    private HighScoreManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized HighScoreManager getInstance(Context context) {
        if (instance == null) {
            instance = new HighScoreManager(context.getApplicationContext());
        }
        return instance;
    }

    public int getHighScore() {
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
    }

    public void setHighScore(int newScore) {
        int currentHighScore = getHighScore();

        // 만약 새로운 점수가 현재 최고 기록보다 크다면 업데이트
        if (newScore > currentHighScore) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(HIGH_SCORE_KEY, newScore);
            editor.apply();
        }
    }
}

