package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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