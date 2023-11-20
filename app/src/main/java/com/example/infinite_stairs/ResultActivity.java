package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView bestScoreTextView;
    private HighScoreManager highScoreManager;
    private TextView currentScoreTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent fromGameIntent = getIntent();
        int score = fromGameIntent.getIntExtra("score", 0);

        // TextView 찾기
        currentScoreTextView = findViewById(R.id.CurrentScore);

        // TextView에 score 값 설정
        currentScoreTextView.setText(String.valueOf(score));

        // ImageButton 찾기
        ImageButton homeButton = findViewById(R.id.homeButton);

        highScoreManager = HighScoreManager.getInstance(getApplicationContext());

        bestScoreTextView = findViewById(R.id.BestScore);


        int currentHighScore = highScoreManager.getHighScore();

        bestScoreTextView.setText(String.valueOf(currentHighScore));

        ImageView crownImage = findViewById(R.id.crownImage);
        ImageView newImage = findViewById(R.id.newImage);


        if (score==currentHighScore){
            crownImage.setVisibility(View.VISIBLE);
            newImage.setVisibility(View.VISIBLE);
        }

        // 클릭 이벤트 리스너 설정
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity로 이동하는 Intent 생성
                Intent toMainIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(toMainIntent);
            }
        });
    }
}
