package com.example.hrsr04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.GpioCallback;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    
    private EditText nameEditText;
    private Button StartButton;
    private Button AboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        
        nameEditText = findViewById(R.id.nameedittext);
        StartButton = findViewById(R.id.startbutton);
        AboutButton = findViewById(R.id.Aboutbutton);
        

        
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = nameEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, quizScreen.class);
                intent.putExtra("message_key", str);
                startActivity(intent);

            }
        });

        AboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this , AboutPage.class);
                startActivity(intent);
            }
        });



    }

}
