package com.example.infinite_stairs;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    public boolean musicOn;
    private Context context;  // 추가

    private MusicManager(Context context) {  // 생성자에 Context 추가
        this.context = context;
        musicOn = false; // 기본값은 음악이 켜져있는 상태
        initMediaPlayer();
    }

    public static synchronized MusicManager getInstance(Context context) {
        if (instance == null) {
            instance = new MusicManager(context.getApplicationContext());
        }
        return instance;
    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(context, R.raw.background_music);
        mediaPlayer.setLooping(true);
    }

    public void startMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            musicOn = true;
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            musicOn = false;
        }
    }

    public boolean isMusicOn() {
        return musicOn;
    }
}
