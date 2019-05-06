package com.example.mobilalk;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[4];
    public int score=0;
    public int[] store = new int[100];
    public int[] input = new int[100];
    public int inputN=0;
    private TextView playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerScore = findViewById(R.id.score);

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);

        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
        buttons[3].setOnClickListener(this);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent restartIntent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
            }
        });

        Random rand = new Random();
        int n = rand.nextInt(4) + 1;
        store[0] = n;
        Handler handler = new Handler();
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.bell_1);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.bell_2);
        final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.bell_3);
        final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.bell_4);

        switch (store[0]) {
            case 1: {
                    buttons[0].setBackgroundColor(getResources().getColor(R.color.green2));
                    mp1.start();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            buttons[0].setBackgroundColor(getResources().getColor(R.color.green1));
                            mp1.stop();
                        }
                    }, 1000);
                    break;
                }
            case 2: {
                    buttons[1].setBackgroundColor(getResources().getColor(R.color.blue2));
                    mp2.start();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            buttons[1].setBackgroundColor(getResources().getColor(R.color.blue1));
                            mp2.stop();
                        }
                    }, 1000);
                    break;
                }
            case 3: {
                    buttons[2].setBackgroundColor(getResources().getColor(R.color.red2));
                    mp3.start();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            buttons[2].setBackgroundColor(getResources().getColor(R.color.red1));
                            mp3.stop();
                        }
                    }, 1000);
                    break;
                }
            case 4: {
                    buttons[3].setBackgroundColor(getResources().getColor(R.color.orange2));
                    mp4.start();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            buttons[3].setBackgroundColor(getResources().getColor(R.color.orange1));
                            mp4.stop();
                        }
                    }, 1000);
                    break;
                }
            }

    }

    public void nextInput(){
        if(store[inputN]!=input[inputN]){
            playerScore.setTextColor(getResources().getColor(R.color.red1));
            playerScore.setTextSize(50);
            playerScore.setText("Game over! Final score:" + score);
            buttons[0].setBackgroundColor(getResources().getColor(R.color.red1));
            buttons[1].setBackgroundColor(getResources().getColor(R.color.red1));
            buttons[2].setBackgroundColor(getResources().getColor(R.color.red1));
            buttons[3].setBackgroundColor(getResources().getColor(R.color.red1));
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.bell_end);
            mp.start();
            Handler handlerOff = new Handler();
            handlerOff.postDelayed(new Runnable() {
                public void run() {
                    mp.stop();
                }
            }, 4000);
            return;
        }

        if(inputN==score){
            score++;
            playerScore.setText("Score: " + score);
            Random rand = new Random();
            int n = rand.nextInt(4) + 1;
            store[score] = n;
            final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.bell_1);
            final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.bell_2);
            final MediaPlayer mp3 = MediaPlayer.create(this, R.raw.bell_3);
            final MediaPlayer mp4 = MediaPlayer.create(this, R.raw.bell_4);

            for (int i = 0; i <= score; i++) {
                Handler handler2 = new Handler();
                switch (store[i]) {
                    case 1: {
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                buttons[0].setBackgroundColor(getResources().getColor(R.color.green2));
                                mp1.start();
                                Handler handler1 = new Handler();
                                handler1.postDelayed(new Runnable() {
                                    public void run() {
                                        buttons[0].setBackgroundColor(getResources().getColor(R.color.green1));
                                        mp1.pause();
                                        mp1.seekTo(0);
                                    }
                                }, 1000);
                            }
                        }, 1100*i);
                        break;
                    }

                    case 2: {
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                buttons[1].setBackgroundColor(getResources().getColor(R.color.blue2));
                                mp2.start();
                                Handler handler1 = new Handler();
                                handler1.postDelayed(new Runnable() {
                                    public void run() {
                                        mp2.pause();
                                        mp2.seekTo(0);
                                        buttons[1].setBackgroundColor(getResources().getColor(R.color.blue1));
                                    }
                                }, 1000);
                            }
                        }, 1100*i);
                        break;
                    }

                    case 3: {
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                buttons[2].setBackgroundColor(getResources().getColor(R.color.red2));
                                mp3.start();
                                Handler handler1 = new Handler();
                                handler1.postDelayed(new Runnable() {
                                    public void run() {
                                        mp3.pause();
                                        mp3.seekTo(0);
                                        buttons[2].setBackgroundColor(getResources().getColor(R.color.red1));
                                    }
                                }, 1000);
                            }
                        }, 1100*i);
                        break;
                    }

                    case 4: {
                        handler2.postDelayed(new Runnable() {
                            public void run() {
                                buttons[3].setBackgroundColor(getResources().getColor(R.color.orange2));
                                mp4.start();
                                Handler handler1 = new Handler();
                                handler1.postDelayed(new Runnable() {
                                    public void run() {
                                        mp4.pause();
                                        mp4.seekTo(0);
                                        buttons[3].setBackgroundColor(getResources().getColor(R.color.orange1));
                                    }
                                }, 1000);
                            }
                        }, 1100*i);
                        break;
                    }
                }
            }
            Arrays.fill(input, 0);
            inputN = 0;
        }else{
            inputN++;
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button1:{
                buttons[0].setBackgroundColor(getResources().getColor(R.color.green2));
                new CountDownTimer(500, 1000) {
                    public void onFinish() {
                        buttons[0].setBackgroundColor(getResources().getColor(R.color.green1));
                        input[inputN] = 1;
                        nextInput();
                    }
                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();
            }
            break;

            case R.id.button2:{
                buttons[1].setBackgroundColor(getResources().getColor(R.color.blue2));

                new CountDownTimer(500, 1000) {
                    public void onFinish() {
                        buttons[1].setBackgroundColor(getResources().getColor(R.color.blue1));
                        input[inputN] = 2;
                        nextInput();
                    }
                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();}
            break;

            case R.id.button3:{
                buttons[2].setBackgroundColor(getResources().getColor(R.color.red2));

                new CountDownTimer(500, 1000) {
                    public void onFinish() {
                        buttons[2].setBackgroundColor(getResources().getColor(R.color.red1));
                        input[inputN] = 3;
                        nextInput();
                    }
                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();}
            break;
            case R.id.button4:{
                buttons[3].setBackgroundColor(getResources().getColor(R.color.orange2));

                new CountDownTimer(500, 1000) {
                    public void onFinish() {
                        buttons[3].setBackgroundColor(getResources().getColor(R.color.orange1));
                        input[inputN] = 4;
                        nextInput();
                    }
                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();}
            break;
        }

    }
}