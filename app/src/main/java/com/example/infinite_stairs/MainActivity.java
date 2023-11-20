package com.example.infinite_stairs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton ;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toGameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(toGameIntent);
            }
        });

        ImageButton ThemeButton = findViewById(R.id.ThemeButton);
        ThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toThemeIntent = new Intent(MainActivity.this, ThemeActivity.class);
                startActivity(toThemeIntent);
            }
        });
    }
}
