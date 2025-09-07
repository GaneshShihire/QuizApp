package com.example.quizzo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class c_language extends AppCompatActivity {

    private RadioGroup[] answerGroups;
    private int[] correctAnswers = {2, 3, 3, 2, 3, 4, 0, 0, 0, 0}; // Correct answers indices (1-based for first 6 questions, others set to 0 as they are not provided)
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clanguage);

        answerGroups = new RadioGroup[6];
        answerGroups[0] = findViewById(R.id.answers1);
        answerGroups[1] = findViewById(R.id.answers2);
        answerGroups[2] = findViewById(R.id.answers3);
        answerGroups[3] = findViewById(R.id.answers4);
        answerGroups[4] = findViewById(R.id.answers5);
        answerGroups[5] = findViewById(R.id.answers6);

        scoreTextView = findViewById(R.id.submit_button);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = calculateScore();
                String scoreMessage = "Your score: " + score + " out of 10\n";
                for (int i = 0; i < answerGroups.length; i++) {
                    int checkedId = answerGroups[i].getCheckedRadioButtonId();
                    if (checkedId != -1) { // Check if an option is selected
                        RadioButton selectedAnswer = findViewById(checkedId);
                        int answerIndex = answerGroups[i].indexOfChild(selectedAnswer) + 1; // Get 1-based index of selected answer
                        scoreMessage += "Question " + (i + 1) + ": " + (answerIndex == correctAnswers[i] ? "Correct" : "Incorrect") + "\n";
                    } else {
                        scoreMessage += "Question " + (i + 1) + ": Not answered\n";
                    }
                }
                scoreTextView.setText(scoreMessage);
                Toast.makeText(c_language.this, "Your score: " + score + " out of 10", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < answerGroups.length; i++) {
            int checkedId = answerGroups[i].getCheckedRadioButtonId();
            if (checkedId != -1) { // Check if an option is selected
                RadioButton selectedAnswer = findViewById(checkedId);
                int answerIndex = answerGroups[i].indexOfChild(selectedAnswer) + 1; // Get 1-based index of selected answer
                if (answerIndex == correctAnswers[i]) {
                    score++;
                }
            }
        }
        return score;
    }
}
