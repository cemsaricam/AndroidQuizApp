package com.example.a20200305010;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exam2 extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView currentQuestionTextView;
    TextView questionTextView;
    Button ansTrue, ansFalse;
    Button backBtn;
    Button nextBtn;

    int score = 0;
    int totalQuestion = Exam2Answers.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        totalQuestionsTextView = findViewById(R.id.total_question);
        currentQuestionTextView = findViewById(R.id.current_question);
        questionTextView = findViewById(R.id.question);
        ansTrue = findViewById(R.id.ans_True);
        ansFalse = findViewById(R.id.ans_False);
        nextBtn = findViewById(R.id.next_btn);
        backBtn = findViewById(R.id.back_btn);

        ansTrue.setOnClickListener(this);
        ansFalse.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions: " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansTrue.setBackgroundColor(Color.WHITE);
        ansFalse.setBackgroundColor(Color.WHITE);

        if (view.getId() == R.id.back_btn) {
            goBackToPreviousQuestion();
        } else if (view.getId() == R.id.next_btn) {
            if (selectedAnswer.equals(Exam2Answers.correctAnswers[currentQuestionIndex])) {
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
        questionTextView.setText(Exam2Answers.question[currentQuestionIndex]);
        ansTrue.setText(Exam2Answers.choices[currentQuestionIndex][0]);
        ansFalse.setText(Exam2Answers.choices[currentQuestionIndex][1]);
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
