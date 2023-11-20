package com.example.infinite_stairs;

import android.app.Application;

public class MyApplication extends Application {
    private MusicManager musicManager;

    @Override
    public void onCreate() {
        super.onCreate();
        musicManager = MusicManager.getInstance(getApplicationContext());
    }

    public MusicManager getMusicManager() {
        return musicManager;
    }
}
