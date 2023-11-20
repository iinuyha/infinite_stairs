package com.example.infinite_stairs;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private boolean musicOn;
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String MUSIC_PREFERENCE_KEY = "music_preference";
    private static final String MUSIC_STATE_KEY = "music_state";

    private MusicManager(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(MUSIC_PREFERENCE_KEY, Context.MODE_PRIVATE);
        musicOn = isMusicOn();  // 초기화 시 저장된 상태를 가져와서 설정
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
        if (musicOn) {
            startMusic();  // 초기화 시 저장된 상태에 따라 음악 시작 또는 정지
        }
    }

    public void startMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying() && !musicOn) {
            mediaPlayer.start();
            musicOn = true;
            saveMusicState(true);
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying() && musicOn) {
            mediaPlayer.pause();
            musicOn = false;
            saveMusicState(false);
        }
    }

    public boolean isMusicOn() {
        // musicOn 변수에 값을 할당하고 반환
        musicOn = sharedPreferences.getBoolean(MUSIC_STATE_KEY, false);
        return musicOn;
    }

    public void saveMusicState(boolean isMusicOn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MUSIC_STATE_KEY, isMusicOn);
        editor.apply();
    }
}
