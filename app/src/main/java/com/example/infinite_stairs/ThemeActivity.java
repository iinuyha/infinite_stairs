package com.example.infinite_stairs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThemeActivity extends AppCompatActivity {

    private ImageButton christmasThemeButton;
    private ImageButton basicThemeButton;
    private ImageButton lockedThemeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        christmasThemeButton = findViewById(R.id.christmasThemeButton);
        basicThemeButton = findViewById(R.id.basicThemeButton);
        lockedThemeButton = findViewById(R.id.lockedThemeButton);

        basicThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.getInstance().setBasicTheme();
                finish();
            }
        });
        christmasThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.getInstance().setChristmasTheme();
                finish();
            }
        });

        lockedThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThemeActivity.this, R.style.CustomAlertDialog);
                builder.setTitle("This theme is locked. \nPlease wait for the next update...");

                // OK 버튼 추가
                builder.setPositiveButton("OK", null); // null로 설정하여 기본 동작을 사용하지 않도록 함

                // AlertDialog 객체 생성
                AlertDialog alertDialog = builder.create();

                // OK 버튼의 스타일을 직접 설정
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

                        // 색상을 검정색으로 변경
                        positiveButton.setTextColor(Color.BLACK);

                        // OK 버튼 클릭 시의 동작 설정
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 여기에 "OK" 버튼을 눌렀을 때의 동작 추가
                                alertDialog.dismiss(); // 다이얼로그 닫기
                            }
                        });
                    }
                });

// 다이얼로그 보이기
                alertDialog.show();

            }
        });
    }

}
