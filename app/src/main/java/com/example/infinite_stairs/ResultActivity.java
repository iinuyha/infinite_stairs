package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent gameIntent = getIntent();
        int score = gameIntent.getIntExtra("score", 0);

        // TextView 찾기
        TextView currentScoreTextView = findViewById(R.id.CurrentScore);

        // TextView에 score 값 설정
        currentScoreTextView.setText(String.valueOf(score));

        // ImageButton 찾기
        ImageButton homeButton = findViewById(R.id.homeButton);

        // 클릭 이벤트 리스너 설정
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity로 이동하는 Intent 생성
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
