package com.example.biggernumbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheatActivity extends AppCompatActivity {

    private Button acceptCheatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        acceptCheatingButton = findViewById(R.id.accept_cheating_button);

        acceptCheatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheatActivity.this, MainActivity.class);
                intent.putExtra("buttonClicked", true);
                startActivity(intent);
            }
        });
    }
}