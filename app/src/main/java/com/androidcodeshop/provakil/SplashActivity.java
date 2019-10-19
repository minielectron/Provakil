package com.androidcodeshop.provakil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final String DISPLAY = "PROVAKIL";
    @BindView(R.id.textView)
    TextView textView;

    private int i = 0;
    private Timer timer;
    private Handler handler;
    private TimerTask taskEverySplitSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setText(DISPLAY);
    }

    @SuppressLint("HandlerLeak")
    public void setText(final String s) {
        final int length = s.length();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c = s.charAt(i);
                Log.d("Strange", "" + c);
                if (i > 2) {
                    Spannable colorLetter = new SpannableString(String.valueOf(c));
                    colorLetter.setSpan(new ForegroundColorSpan(Color.RED), 0, colorLetter.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.append(colorLetter);
                } else
                    textView.append(String.valueOf(c));
                i++;
            }
        };

        timer = new Timer();
        taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i == length - 1) {
                    timer.cancel();
                    try {
                        Thread.sleep(1000);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer != null && handler != null) {
            timer.cancel();
            handler.removeCallbacksAndMessages(null);
            finish();
        }
    }
}
