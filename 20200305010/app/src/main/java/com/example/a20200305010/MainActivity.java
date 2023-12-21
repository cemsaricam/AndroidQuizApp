package com.example.a20200305010;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btn_study;
    public Button btn_exam1;

    public Button btn_exam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_study=findViewById(R.id.btn_study);
        btn_exam1=findViewById(R.id.btn_exam1);
        btn_exam2=findViewById(R.id.btn_exam2);

        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Study.class);
                startActivity(intent);
            }
        });

        btn_exam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Exam1.class);
                startActivity(intent);

            }
        });

        btn_exam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Exam2.class);
                startActivity(intent);

            }
        });
    }
}