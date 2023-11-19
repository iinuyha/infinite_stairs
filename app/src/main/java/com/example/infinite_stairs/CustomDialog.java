package com.example.infinite_stairs;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class CustomDialog {

    public interface DialogCallback {
        void onHomeButtonClicked();
        void onPlayButtonClicked();
    }

    public static void showDefaultDialog(Context context, final DialogCallback callback) {
        // 커스텀 다이얼로그의 레이아웃을 inflate
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_confirm, null);

        // AlertDialog.Builder를 사용하여 다이얼로그 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        // 커스텀 다이얼로그 안의 버튼에 대한 이벤트 처리
        ImageButton gameHomeBtn = dialogView.findViewById(R.id.gameHomeBtn);
        ImageButton gamePlayBtn = dialogView.findViewById(R.id.gamePlayBtn);

        final AlertDialog dialog = builder.create();

        // 다이얼로그 배경을 투명하게 설정
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 다이얼로그의 제목을 제거
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        gameHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onHomeButtonClicked();
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        gamePlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onPlayButtonClicked();
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        // 다이얼로그 표시
        dialog.show();
    }
}