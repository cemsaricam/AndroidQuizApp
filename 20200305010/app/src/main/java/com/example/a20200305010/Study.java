package com.example.a20200305010;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Study extends AppCompatActivity {

    private TextView titleTextView;
    private TextView infoTextView;
    private ImageView imageView5;
    private Button nextButton;
    private Button backButton;

    private String[] titles;
    private String[] infoTexts;
    private int currentIndex = 0;

    private int[] geographyImages = {
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3,
            R.drawable.foto4,
            R.drawable.foto5,
            R.drawable.foto6,
            R.drawable.foto7,
            R.drawable.foto8,
            R.drawable.foto9,
            R.drawable.foto10,
            R.drawable.foto11,
            R.drawable.foto12,
            R.drawable.foto13,
            R.drawable.foto14,
            R.drawable.foto15,
            R.drawable.foto16,
            R.drawable.foto17,
            R.drawable.foto18,
            R.drawable.foto19,
            R.drawable.foto20,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        titleTextView = findViewById(R.id.titleTextView);
        infoTextView = findViewById(R.id.infoTextView);
        imageView5 = findViewById(R.id.imageView5);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        titles = getResources().getStringArray(R.array.study_titles);
        infoTexts = getResources().getStringArray(R.array.study_info_texts);

        updateContent();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex == titles.length - 1) {
                    // Eğer son sayfadaysa MainActivity'e git
                    Intent intent = new Intent(Study.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Eğer Study Activity'yi kapatmak istiyorsanız
                } else {
                    currentIndex = (currentIndex + 1) % titles.length;
                    updateContent();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex == 0) {
                    // Eğer ilk sayfadaysa MainActivity'e git
                    Intent intent = new Intent(Study.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Eğer Study Activity'yi kapatmak istiyorsanız
                } else {
                    currentIndex = (currentIndex - 1 + titles.length) % titles.length;
                    updateContent();
                }
            }
        });
    }

    private void updateContent() {
        titleTextView.setText(titles[currentIndex]);
        infoTextView.setText(infoTexts[currentIndex]);
        imageView5.setImageResource(geographyImages[currentIndex]);
    }
}
