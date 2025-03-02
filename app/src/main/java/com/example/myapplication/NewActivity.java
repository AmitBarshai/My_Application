package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewActivity extends AppCompatActivity {

    private ImageView timerImage; // משתנה גלובלי לתמונה

    private final int[] images = {
            R.drawable.number_5,
            R.drawable.number_4,
            R.drawable.number_3,
            R.drawable.number_2,
            R.drawable.number_1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // אתחול של timerImage בתוך onCreate
        timerImage = findViewById(R.id.timer_image);

        // הפעלת הטיימר בתוך onCreate
        new CountDownTimer(5000, 1000) {
            int index = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                if (index < images.length) {
                    timerImage.setImageResource(images[index]);
                    index++;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(NewActivity.this, NextActivity.class);
                startActivity(intent);
                finish(); // סוגר את הפעילות הנוכחית כדי שלא תחזור אחורה
            }
        }.start();
    }
}