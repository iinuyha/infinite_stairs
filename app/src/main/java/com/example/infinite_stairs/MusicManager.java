package com.example.infinite_stairs;

import android.app.Application;
import android.media.MediaPlayer;

public class MusicManager extends Application {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private boolean musicOn;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        musicOn = true; // 기본값은 음악이 켜져있는 상태
        initMediaPlayer();
    }

    public static MusicManager getInstance() {
        return instance;
    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.your_music_file); // 여기서 'your_music_file'은 재생할 음악 파일의 이름입니다.
        mediaPlayer.setLooping(true); // 음악을 반복 재생하도록 설정
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
