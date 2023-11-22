package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton soundButton;
    private MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MusicManager를 onCreate에서 초기화
        MyApplication myApp = (MyApplication) getApplication();
        musicManager = myApp.getMusicManager();

        ImageButton startButton = findViewById(R.id.StartButton);
        ImageButton themeButton = findViewById(R.id.ThemeButton);
        soundButton = findViewById(R.id.SoundButton);
        ImageButton mailButton = findViewById(R.id.MailButton);
        mailButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("plain/text");
                String[] address = {"InfiniteStiars@gmail.com"};
                mail.putExtra(Intent.EXTRA_EMAIL, address);
                mail.putExtra(Intent.EXTRA_TEXT, "Infinite Stairs 게임에 대한 평가를 전송해주세요! \n :");
                startActivity(mail);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toGameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(toGameIntent);
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toThemeIntent = new Intent(MainActivity.this, ThemeActivity.class);
                startActivity(toThemeIntent);
            }
        });

        toggleMusic();  // 앱이 실행될 때 현재 상태에 맞게 이미지 설정

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMusic();
            }
        });
    }

    private void updateMusicButtonImage() {
        if (musicManager.isMusicOn()) {
            soundButton.setImageResource(R.drawable.music_off_btn);
        } else {
            soundButton.setImageResource(R.drawable.music_on_btn);
        }
    }

    private void toggleMusic() {
        if (musicManager.isMusicOn()) {
            musicManager.stopMusic();
        } else {
            musicManager.startMusic();
        }
        // 이미지 업데이트 이후에 상태를 저장
        updateMusicButtonImage();
        musicManager.saveMusicState(!musicManager.isMusicOn());
    }
}

