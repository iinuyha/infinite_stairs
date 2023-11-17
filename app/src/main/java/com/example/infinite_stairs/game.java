package com.example.infinite_stairs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class game extends AppCompatActivity {

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
    Handler handler;
    Runnable loop;
    int delayFactor;
    int delay;
    int delayLowerLimit;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        gameState = new GameState(15, 7);
        drawView = new DrawView(this, gameState);//gameState가 State여야 그림을 그림
        //초기 화면 설정
        gameState.initBlock();  //초기 블록 설정
        drawView.invalidate();


    }

    //초기화면 설정(열, 행 넣어서 계단 그릴 베이스 설정) gameState = newGamestate(행, 열); 이용
    //백그라운드 그리기(게임 상태 전달 => 초기 게임 상태 그리기)  => 위에서 gameState 행, 열 넣으면 game State초기라는 정보 가져올 수 있어야함
    //
    //게임 실행 버튼 눌리면 =>  gameState.moveFallingTetraminoLeft();처럼 GameState에서 방향바꾸기, 위로 올라가기 함수 호출

    //정지 버튼 눌리면 => 나중에 구현




}
