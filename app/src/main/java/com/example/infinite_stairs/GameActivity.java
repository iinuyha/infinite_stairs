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
                } else {
                    // progressValue가 0 이하일 때, ResultActivity로 이동
                    goToResultActivity();
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
        changeButton.setOnClickListener(new View.OnClickListener() { // 수정: changeButton에 대한 클릭 리스너 추가
            @Override
            public void onClick(View view) {
                // 왼쪽 아래 방향 바꾸는 버튼 누르면 실행되는 기능
            }
        });

        ImageButton upButton = findViewById(R.id.UpButton);
        upButton.setOnClickListener(new View.OnClickListener() { // 수정: upButton에 대한 클릭 리스너 추가
            @Override
            public void onClick(View view) {
                // 오른쪽 아래 위로 올라가는 버튼 누르면 실행되는 기능
            }
        });
    }

    // 수정: ResultActivity로 이동하는 메소드 추가
    private void goToResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
        // 이후 필요에 따라 finish()를 호출하여 현재 Activity를 종료할 수 있습니다.
        // finish();
    }
}
