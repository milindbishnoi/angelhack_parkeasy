package com.example.angelhack;

import android.content.Intent;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tvEnter;
    private TextToSpeech myTTs;
    private SpeechRecognizer myspeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEnter = findViewById(R.id.tvEnter);
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.angelhack.Page1.class);
                startActivity(intent);
            }
        });
        initializeTextToSpeech();


    }

    private void initializeTextToSpeech() { myTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if (myTTs.getEngines().size() == 0) {
                Toast.makeText(MainActivity.this, "There is no TTS engine on your device", Toast.LENGTH_LONG).show();
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
