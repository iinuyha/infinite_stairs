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
        handler.postDelayed(progressBarRunnable, 500); // 초기 0.5초 후에 시작

        ImageButton stopButton = findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일시정지 버튼 누르면 팝업창 뜨도록 여기 기능 구현하기
                showCustomDialog();
            }
        });

        ImageButton changeButton = findViewById(R.id.ChangeButton);
        changeButton.setOnClickListener(new View.OnClickListener() { // 수정: changeButton에 대한 클릭 리스너 추가
            @Override
            public void onClick(View view) {
                gameState.updateBlock(0);
                drawView.invalidate();
                // 왼쪽 아래 방향 바꾸는 버튼 누르면 실행되는 기능
                changeBackground();
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
                changeBackground();
                restartProgress();  // 버튼을 누를 때마다 프로그레스바가 100으로 꽉 참
            }
        });
    }



    ///////////////////////////////////////////////////////////

    // 프로그레스바의 초기 값을 100으로 지정
    private void restartProgress() {
        progressValue = 100;
    }

    private Runnable progressBarRunnable = new Runnable() {
        @Override
        public void run() {
            // ProgressBar 갱신
            progressBar.setProgress(progressValue);
            progressValue -= 10;        // 프로그레스바가 1초에 10씩 줄어듦

            // progressValue가 0 미만으로 가지 않도록 체크
            if (progressValue >= 0) {
                // 다음 갱신을 위해 Handler에게 postDelayed 호출
                handler.postDelayed(this, 1000); // 1초마다 갱신
            } else {
                // progressValue가 0 미만일 때, ResultActivity로 이동
                goToResultActivity();
            }
        }
    };

    //일시정지 버튼 누르면 CustomDialog 클래스에서 지정한 다이얼로그 띄움
    private void showCustomDialog() {
        // 다이얼로그가 뜨면, 프로그레스바는 중지
        handler.removeCallbacksAndMessages(null);
        // 커스텀다이얼로그 불러옴
        CustomDialog.showDefaultDialog(this, new CustomDialog.DialogCallback() {
            @Override
            public void onHomeButtonClicked() {
                // gameHomeBtn을 눌렀을 때 MainActivity로 이동
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onPlayButtonClicked() {
                // gamePlayBtn을 눌렀을 때 다이얼로그 닫고, 프로그레스바는 다시 이어서 실행
                handler.postDelayed(progressBarRunnable, 1000); // 1초마다 갱신
            }
        });
    }

    // ResultActivity로 이동하는 메서드
    private void goToResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    // 배경을 아래로 이동시키는 메서드
    private int currentBackgroundIndex = 0;

    //버튼을 1~3번째 클릭하는 경우에 배경 이미지가 하니씩 INVISIBLE하게 되도록
    private void changeBackground() {
        int[] backgroundIds = {
                R.id.backgroundImageView,
                R.id.backgroundImageView2,
                R.id.backgroundImageView3,
        };

        if (currentBackgroundIndex < 3){
            // 현재 처리할 배경 ImageView
            ImageView currentBackground = findViewById(backgroundIds[currentBackgroundIndex]);

            //배경을 INVISIBLE로 설정
            currentBackground.setVisibility(View.INVISIBLE);

            currentBackgroundIndex++;
        }
    }
}