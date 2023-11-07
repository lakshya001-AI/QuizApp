package com.example.hrsr04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class quizScreen extends AppCompatActivity {

    private TextView tvQuestion, tvScore, textView4;
   private RadioGroup radioGroup;
   private RadioButton rb1, rb2, rb3, rb4;
   private Button btnNext, btnQuit;

   int totalQuestion;
   int qCounter = 0;
   int score = 0;
   int correctanswer = 0;
   private QuestionModel currentQuestion;

   ColorStateList dfRbColor;
   boolean answered;


    private List<QuestionModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        questionList = new ArrayList<>();
        tvQuestion = findViewById(R.id.questiontextview);
        tvScore = findViewById(R.id.scoretextview);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 =findViewById(R.id.option1RadioButton);
        rb2 =findViewById(R.id.option2RadioButton);
        rb3 =findViewById(R.id.option3RadioButton);
        rb4 =findViewById(R.id.option4RadioButton);
        textView4 = findViewById(R.id.textView4);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        textView4.setText("Hello " + str );

        btnNext = findViewById(R.id.nextQuestionbutton);
        btnQuit = findViewById(R.id.quitbutton);

        dfRbColor = rb1.getTextColors();

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(quizScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addQuestions();
        totalQuestion = questionList.size();
        showNextQuestion();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answered == false){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();

                    }
                    else {
                        Toast.makeText(quizScreen.this, "Please Select an Option", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });


    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            correctanswer++;
            tvScore.setText("Your Score: " + score);
            Toast.makeText(this, "Awesome! Right Answer", Toast.LENGTH_SHORT).show();
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;

            case 2:
                rb2.setTextColor(Color.GREEN);
                break;

            case 3:
                rb3.setTextColor(Color.GREEN);
                break;

            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestion){
            btnNext.setText("Next");
        }
        else {
            btnNext.setText("Finish");
        }


    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);


        if (qCounter < totalQuestion){
            currentQuestion = questionList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;
            btnNext.setText("Submit");
            answered = false;
        }
        else {
            Intent intent = new Intent(quizScreen.this , resultPage.class);
            String score1 = Integer.toString(score);
            String totalQuestion1 = Integer.toString(totalQuestion);
            String correctanswer1 = Integer.toString(correctanswer);

            intent.putExtra("score", score1);
            intent.putExtra("questions", totalQuestion1);
            intent.putExtra("correctanswer", correctanswer1);
            startActivity(intent);
            finish();
        }
    }

    private void addQuestions() {
        questionList.add(new QuestionModel(
                "Which method can be defined only once in a program ?", "finalize method","main method", "static method","private method", 2));
        questionList.add(new QuestionModel(
                "Which keyword is used by method to refer to the current object that invoked it ?", "import","this", "catch","abstract", 2));
        questionList.add(new QuestionModel(
                "Which of these access specifiers can be used for an interface ? ", "public","protected", "private","All of the above", 1));
        questionList.add(new QuestionModel(
                "Which of the following is correct way of importing an entire package<pkg> ", "Import pkg.","import pkg.*", "Import pkg.*","import pkg.", 2));
        questionList.add(new QuestionModel(
                "What is the return type of Constructors", "int","float", "void","None of the above", 4));
    }


}