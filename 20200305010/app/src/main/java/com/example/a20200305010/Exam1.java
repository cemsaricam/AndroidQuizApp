package com.example.a20200305010;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exam1 extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView currentQuestionTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button nextBtn;
    Button backBtn;

    int score = 0;
    int totalQuestion = Exam1Answers.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        totalQuestionsTextView = findViewById(R.id.total_question);
        currentQuestionTextView = findViewById(R.id.current_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        nextBtn = findViewById(R.id.next_btn);
        backBtn = findViewById(R.id.back_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions: " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        if (view.getId() == R.id.back_btn) {
            goBackToPreviousQuestion();
        } else if (view.getId() == R.id.next_btn) {
            if (selectedAnswer.equals(Exam1Answers.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            selectedAnswer = ((Button) view).getText().toString();
            view.setBackgroundColor(Color.BLUE);
        }
    }

    void goBackToPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadNewQuestion();
        } else {
            goToMainActivity();
        }
    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        currentQuestionTextView.setText("Question " + (currentQuestionIndex + 1) + " of " + totalQuestion);
        questionTextView.setText(Exam1Answers.question[currentQuestionIndex]);
        ansA.setText(Exam1Answers.choices[currentQuestionIndex][0]);
        ansB.setText(Exam1Answers.choices[currentQuestionIndex][1]);
        ansC.setText(Exam1Answers.choices[currentQuestionIndex][2]);
        ansD.setText(Exam1Answers.choices[currentQuestionIndex][3]);
    }

    void finishQuiz() {
        String passStatus = (score > totalQuestion * 0.60) ? "Passed" : "Failed";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setNegativeButton("Close", (dialogInterface, i) -> goToMainActivity())
                .setCancelable(false)
                .show();
    }

    void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}
