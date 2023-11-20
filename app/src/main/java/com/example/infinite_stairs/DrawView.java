//// 비트맵에서 특정 영역 자르기
//Rect sourceRect = new Rect(startX, startY, endX, endY); // 시작과 끝 좌표 설정
//Bitmap croppedBitmap = Bitmap.createBitmap(originalBitmap, sourceRect.left, sourceRect.top, sourceRect.width(), sourceRect.height());
//
//// 자른 비트맵을 캔버스에 그리기
//Canvas canvas = new Canvas();
//canvas.drawBitmap(croppedBitmap, 0, 0, paint);
//일단 구현부터 하고 되면 이거 넣어서 열 넓힌 다음 부분만 표시하도록 하기

package com.example.infinite_stairs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
//clear 꼭 있어야하나? 한번 없애보기

public class DrawView extends View {
    private final int ROWS = 19;
    private final int COLS = 41;
    int yOffset; //이거 수정해야할듯
    GameState gameState;
    Bitmap BlockBitmap;

    public DrawView(Context context, final GameState gameState) {
        super(context);
        yOffset = 200;  //테트리스 시작 위치 정하기 위해 사용됨
        this.gameState = gameState;
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), ThemeManager.getInstance().getBlockImageResource());
        BlockBitmap = Bitmap.createScaledBitmap(originalBitmap, 200, 100, true);  // 크기를 200x200으로 조정

    } //초기 그리기 설정


    //비어진 블록 아니면 그냥 그리기 => 이걸로 그냥 블록까지 싹 다 그려버리기?
    // => ON_EMPTY 이용해서 블록 있는 상태를 ON_BLOCK으로 만들면서 그리기
    private void DrawMatrix(BasicBlock[][] matrix, Canvas canvas) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j].state != BasicBlockState.ON_BLOCK)
                    continue; //현재 상태 ON_EMPTY면 그냥 넘어감(블록 있을 때만 그림)

                int adjustedX =  -3600 + j * BlockBitmap.getWidth();   //Clear left 값
                int adjustedY = 250 + i * BlockBitmap.getHeight() + 2;
                canvas.drawBitmap(BlockBitmap, adjustedX, adjustedY, null);
            } //이걸 계속 불러와서 ON_EMPTY로 사각형이 비어있지 않으면 계속 그리나봄
        }
    }  //게임판 정보 받아와서 게임판 그리기

    private void Clear(BasicBlock[][] matrix, Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.TRANSPARENT);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                canvas.drawRect(42 + j * 50, yOffset + i * 50 + 2, 88 + j * 50, yOffset + (i + 1) * 50 - 2, p); //right랑 bottom은 bitmap 블록 크기에 따라 +-하면 됨
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // paint.setStrokeWidth(5f);
        // grid(canvas);
        if (gameState.status) { // 게임 상태면
            Clear(gameState.board, canvas); //다 지우고
            DrawMatrix(gameState.board, canvas); //보드 그리고
        } else { //게임 오버 그리기 => 결과 페이지로 넘어가기

        }

    }

}
