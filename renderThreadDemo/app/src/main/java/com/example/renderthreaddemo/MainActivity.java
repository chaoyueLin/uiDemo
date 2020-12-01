package com.example.renderthreaddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View view = findViewById(R.id.button);
        final ViewPropertyAnimator animator = view.animate();
        AsyncAnimHelper.onStartBefore(animator, view);
// 必须在 start 之前
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        animator.scaleY(2).setDuration(1000).start();

                    }
                }).start();
            }
        });

    }
}