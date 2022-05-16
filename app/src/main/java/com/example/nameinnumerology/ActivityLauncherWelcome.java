package com.example.nameinnumerology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//This activity is the welcome screen.
public class ActivityLauncherWelcome extends AppCompatActivity {
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_welcome);

        buttonStart = findViewById(R.id.welcomeButtonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Simply redirect to the next activity (ActivityWelcomeInfo)
                Intent intent = new Intent(getApplicationContext(), ActivityWelcomeInfo.class);
                startActivity(intent);
                finish();
            }
        });
    }
}