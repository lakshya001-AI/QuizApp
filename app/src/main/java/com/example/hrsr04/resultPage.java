package com.example.hrsr04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class resultPage extends AppCompatActivity {
    private TextView score, totalquestion, correct_answer, wrong_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        score = findViewById(R.id.scoretextview);
        totalquestion = findViewById(R.id.totalquestiontextview);
        correct_answer = findViewById(R.id.correctanswertextview);
        wrong_answer = findViewById(R.id.wrongquestiontextview);

        Intent intent = getIntent();

        String score1 = intent.getStringExtra("score");
        String totalQuestion1 = intent.getStringExtra("questions");
        String correctanswer1 = intent.getStringExtra("correctanswer");
        int total = Integer.parseInt(totalQuestion1);
        int correct = Integer.parseInt(correctanswer1);
        int wrong = total - correct;
        String wrongAnswer2 = Integer.toString(wrong);


        score.setText("Your Score: " + score1);
        totalquestion.setText("Total Question: " + totalQuestion1);
        correct_answer.setText("Correct Answers: " + correctanswer1);
        wrong_answer.setText("Wrong Answers: " + wrongAnswer2);





    }
}