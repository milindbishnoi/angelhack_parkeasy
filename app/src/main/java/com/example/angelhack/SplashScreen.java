package com.example.angelhack;

import android.content.Intent;
import android.os.Build;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelhack.UI.MainPageActivity;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {
    TextView tvEnter;
    private TextToSpeech myTTs;
    private SpeechRecognizer myspeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        tvEnter = findViewById(R.id.tvEnter);
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreen.this, MainPageActivity.class);
                startActivity(intent);
            }
        });
        initializeTextToSpeech();


    }

    private void initializeTextToSpeech() { myTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if (myTTs.getEngines().size() == 0) {
                Toast.makeText(SplashScreen.this, "There is no TTS engine on your device", Toast.LENGTH_LONG).show();
                finish();
            } else {
                myTTs.setLanguage(Locale.ENGLISH);
                speak("Hello! welcome to parkeasy app ");
            }
        }
    });
    }

    private void speak(String message) { if (Build.VERSION.SDK_INT >= 21) {
        myTTs.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
    } else {
        myTTs.speak(message, TextToSpeech.QUEUE_FLUSH, null);

    }
    }


    @Override
    protected void onPause () {
        super.onPause();
        myTTs.shutdown();
    }


}
