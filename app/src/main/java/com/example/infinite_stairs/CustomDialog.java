package com.example.infinite_stairs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {

    private static CustomDialog customDialog;

    private CustomDialog(@NonNull Context context) {
        super(context);
    }

    public static CustomDialog getInstance(Context context) {
        customDialog = new CustomDialog(context);

        return customDialog;
    }

    //다이얼로그 생성하기
    public void showDefaultDialog() {
        //참조할 다이얼로그 화면을 연결한다.
        customDialog.setContentView(R.layout.dialog_confirm);

        ImageButton gameHomeButton = customDialog.findViewById(R.id.gameHomeBtn);
        ImageButton gamePlayButton = customDialog.findViewById(R.id.gamePlayBtn);

        // gameHome버튼을 누르면 어떤 동작 할 지 정의해주기
        gameHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 홈화면으로 가도록
            }
        });

        // gamePlay버튼을 누르면 어떤 동작 할 지 정의해주기
        gamePlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그를 종료하고 원래 화면으로 돌아가도록
            }
        });
    }

}