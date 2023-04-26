package com.example.biggernumbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int score = 0;

    private Button button;

    private Button button2;

    private Button cheatButton;

    private TextView scoreDisplay;

    private TextView cheatText;

    private int number1;

    private int number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        cheatButton = findViewById(R.id.cheat_button);
        scoreDisplay = findViewById(R.id.score_display);
        cheatText = findViewById(R.id.cheat_view);
        cheatText.setVisibility(View.INVISIBLE);

        generateNumbers();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number1 >= number2) {
                    score++;
                    correctAnswer();
                } else {
                    score--;
                    incorrectAnswer();
                }
                if(score == 10) {
                    showWinDialog();
                }
                scoreDisplay.setText("Score: " + score);
                generateNumbers();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number2 >= number1) {
                    score++;
                    correctAnswer();
                } else {
                    score--;
                    incorrectAnswer();
                }
                if(score == 10) {
                    showWinDialog();
                }
                scoreDisplay.setText("Score: " + score);
                generateNumbers();
            }
        });

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                cheatText = findViewById(R.id.cheat_view);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        if(getIntent().getBooleanExtra("buttonClicked", false)) {
            cheatText.setVisibility(View.VISIBLE);
        }
    }

    private void generateNumbers() {
        number1 = getRandomNumber();
        number2 = getRandomNumber();
        button.setText(String.valueOf(number1));
        button2.setText(String.valueOf(number2));
        if (number1 > number2) {
            cheatText.setText("It is: " + number1);
        } else if (number1 < number2) {
            cheatText.setText("It is: " + number2);
        } else {
            cheatText.setText("It is: " + number1);
        }
    }

    private int getRandomNumber() {
        return (int) (Math.random() * 10);
    }

    private void showWinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You win!").setCancelable(false).setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // reset score and generate new numbers
                score = 0;
                scoreDisplay.setText("Score: " + score);
                generateNumbers();
            }
        })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void correctAnswer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Correct!").setCancelable(true).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void incorrectAnswer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Incorrect!").setCancelable(true).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}