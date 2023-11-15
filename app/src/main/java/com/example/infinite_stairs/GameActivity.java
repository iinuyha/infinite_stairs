package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class GameActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressValue = 100; // 초기 값 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progressBar = findViewById(R.id.progressBar);

        // 1초마다 ProgressBar를 업데이트하는 Handler 설정
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // ProgressBar 갱신
                progressBar.setProgress(progressValue);

                // progressValue 갱신
                progressValue -= 10;

                // progressValue가 0 미만으로 가지 않도록 체크
                if (progressValue >= 0) {
                    // 다음 갱신을 위해 Handler에게 postDelayed 호출
                    handler.postDelayed(this, 1000); // 1초마다 갱신
                }
            }
        }, 500); // 초기 0.5초 후에 시작




        ImageButton startButton = findViewById(R.id.StopButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일시정지 버튼 누르면 팝업창 뜨도록 여기 기능 구현하기
            }
        });

        ImageButton changeButton = findViewById(R.id.ChangeButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 왼쪽 아래 방향 바꾸는 버튼 누르면 실행되는 기능
            }
        });

        ImageButton upButton = findViewById(R.id.UpButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 오른쪽 아래 위로 올라가는 버튼 누르면 실행되는 기능
            }
        });


    }
}