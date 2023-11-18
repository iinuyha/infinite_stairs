package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import team.Block;

public class GameActivity extends AppCompatActivity {
    private final int ROWS = 19;
    private final int COLS = 41;

    private ProgressBar progressBar;
    private int progressValue = 100; // 초기 값 설정
    private ImageView backgroundImageView;
    private Handler handler;


    //////////////////////////////////////////////////////////////////////
    DrawView drawView;
    GameState gameState;
    RelativeLayout gameButtons;
    Button left;
    Button right;
    Button rotateAc;
    FrameLayout game;
    Button pause;
    TextView score;
    Button difficultyToggle;
    Runnable loop;
    int delayFactor;
    int delay;
    int delayLowerLimit;


    public GameActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progressBar = findViewById(R.id.progressBar);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        ///////////////////////////////////////////////////////////

        gameState = new GameState(ROWS, COLS);
        gameState.initBlock();  //초기 블록 설정
        drawView = new DrawView(this, gameState);//gameState가 State여야 그림을 그림
        drawView.invalidate();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ConstraintLayout frameLayout = findViewById(R.id.my_constraint_layout);
        frameLayout.addView(drawView);





        //createBlocks();

        // 1초마다 ProgressBar를 업데이트하는 Handler 설정
        this.handler = new Handler(Looper.getMainLooper());
        this.handler.postDelayed(new Runnable() {
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
                    // goToResultActivity();
                }
            }
        }, 500); // 초기 0.5초 후에 시작

        ImageButton stopButton = findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일시정지 버튼 누르면 팝업창 뜨도록 여기 기능 구현하기
            }
        });

        ImageButton changeButton = findViewById(R.id.ChangeButton);
        changeButton.setOnClickListener(new View.OnClickListener() { // 수정: changeButton에 대한 클릭 리스너 추가
            @Override
            public void onClick(View view) {
                gameState.updateBlock(0);
                drawView.invalidate();
                // 왼쪽 아래 방향 바꾸는 버튼 누르면 실행되는 기능
                moveBackgroundDown(); // 버튼을 누를 때마다 배경이 내려감
                restartProgress();  // 버튼을 누를 때마다 프로그레스바가 100으로 꽉 참
            }
        });

        ImageButton upButton = findViewById(R.id.UpButton);
        upButton.setOnClickListener(new View.OnClickListener() { // 수정: upButton에 대한 클릭 리스너 추가
            @Override
            public void onClick(View view) {
                gameState.updateBlock(1);
                drawView.invalidate();
                // 오른쪽 아래 위로 올라가는 버튼 누르면 실행되는 기능
                moveBackgroundDown(); // 버튼을 누를 때마다 배경이 내려감
                restartProgress();  // 버튼을 누를 때마다 프로그레스바가 100으로 꽉 참
            }
        });
    }



    ///////////////////////////////////////////////////////////






    // 프로그레스바의 값을 100으로 초기화하는 메서드
    private void restartProgress() {
        progressValue = 100;
    }


    // ResultActivity로 이동하는 메서드
    private void goToResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    // 배경을 아래로 이동시키는 메서드
    private void moveBackgroundDown() {
        final int moveDistance = 100; // 이동 거리 (임의의 값)
        final int moveDuration = 1000; // 이동 시간 (1초)

        // 배경을 이동하기 위한 Handler 설정
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // ImageView의 위치를 변경하여 화면을 이동하는 효과를 낼 수 있음
                backgroundImageView.setY(backgroundImageView.getY() + moveDistance);
            }
        }, moveDuration);
    }

}