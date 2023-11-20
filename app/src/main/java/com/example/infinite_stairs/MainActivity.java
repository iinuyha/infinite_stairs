package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton ;

public class MainActivity extends AppCompatActivity {
    private ImageButton soundButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton startButton = findViewById(R.id.StartButton);
        ImageButton themeButton = findViewById(R.id.ThemeButton);
        soundButton = findViewById(R.id.SoundButton);
        ImageButton mailButton = findViewById(R.id.MailButton);



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

        updateMusicButtonImage();  // 앱이 실행될 때 현재 상태에 맞게 이미지 설정

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMusic();
                updateMusicButtonImage();
            }
        });
    }
    private void toggleMusic() {
        MusicManager myApplication = MusicManager.getInstance();
        if (myApplication.isMusicOn()) {
            myApplication.stopMusic();
        } else {
            myApplication.startMusic();
        }
    }

    private void updateMusicButtonImage() {
        if (MusicManager.getInstance().isMusicOn()) {
            soundButton.setImageResource(R.drawable.music_on_btn);
        } else {
            soundButton.setImageResource(R.drawable.music_off_btn);
        }
    }
}
