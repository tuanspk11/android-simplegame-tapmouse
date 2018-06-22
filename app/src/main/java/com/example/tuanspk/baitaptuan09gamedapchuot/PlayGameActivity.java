package com.example.tuanspk.baitaptuan09gamedapchuot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class PlayGameActivity extends Activity {
    public static int iScore;

    TextView textViewScore;
    TextView textViewPlayer;
    TextView textViewMang;

    View viewMouse;

    Random random = new Random();

    int iLevel = 1;
    int iSize = 200;
    int iResetTime = 4000;
    int iMaxRestTime = 3000;
    int iRestTime;
    int iMang = 3;
    int iX;
    int iY;
    int iOldX;
    int iOldY;

    int maxWidth = MainActivity.iWidth - iSize;
    int maxHeight = MainActivity.iHeight - iSize - 170;

    long lSystemTime;
    long lStartTime;

    String s = new String("111");

    boolean isRedPoint;

    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game_layout);

        textViewScore = (TextView) findViewById(R.id.textview_score);
        textViewPlayer = (TextView) findViewById(R.id.textview_player);
        textViewMang = (TextView) findViewById(R.id.textview_mang);

        viewMouse = (View) findViewById(R.id.view_mouse);
    }

    @Override
    protected void onStart() {
        super.onStart();

        iScore = 0;

        textViewPlayer.setText(String.valueOf(MainActivity.sPlayerName));

        viewMouse.setBackgroundResource(R.drawable.blue_point);
        isRedPoint = false;

        viewMouse.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (isRedPoint == true) {
                    iMang--;
                    textViewMang.setText(String.valueOf(iMang));
                    if (iMang == 0) onDestroy();
                }

                iScore += 1;
                if (iLevel < 11) {
                    if (iScore == iLevel * 10) levelUp();
                } else if (iLevel == 11) {
                    iSize = 100;
                    iResetTime = 500;
                    iRestTime = 500;
                }

                if (iLevel > 5) showRedPoint();

                showMouse();
                countDown();
//                restTime();

                textViewScore.setText(String.valueOf(iScore));

//                if (iScore == 500 || iScore == 1000) {
//                    onDestroy();
//                }
            }
        });

        startApp();

//        CountDownTimer Timer = new CountDownTimer(30000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                mTextField.setText("done!");
//            }
//        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(PlayGameActivity.this, TheRankingsActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NewApi")
    public void showMouse() {
        while (iX == iOldX && iY == iOldY) {
            iX = random.nextInt(maxWidth);
            if (iX < 0) iX += iSize;
            iY = random.nextInt(maxHeight);
            if (iY < 0) iY += iSize + 100;
        }

        iX = random.nextInt(maxWidth);
        iY = random.nextInt(maxHeight);

        iOldX = iX;
        iOldY = iY;

//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        Canvas canvas = new Canvas();
//        canvas.drawCircle(iOldX, iOldY, iSize, paint);
//        viewMouse.draw(canvas);

//        viewMouse.setBackgroundResource(R.drawable.blue_point);
        viewMouse.layout(iOldX, iOldY, iOldX + iSize, iOldY + iSize);
    }

    @SuppressLint("NewApi")
    public void restTime() {
        //viewMouse.setBackground(null);

        iRestTime = 1000 + random.nextInt(iMaxRestTime - 1000);

        CountDownTimer countDownTimer = new CountDownTimer(iRestTime, iRestTime) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                showMouse();
                countDown();
            }
        }.start();
    }

    public void countDown() {
//        if (iMang == 4) {
//            showMouse();
//            iResetTime = 4000;
//        }
        lStartTime = SystemClock.uptimeMillis();
//        lStartTime -= lStartTime % 10;
//        lSystemTime = SystemClock.uptimeMillis();
//        lSystemTime -= lSystemTime % 10;

        handler.removeCallbacks(runnable, 0);
        handler.postDelayed(runnable, 0);
    }

//    public void countDown2(){
//        CountDownTimer countDownTimer = new CountDownTimer(iResetTime, iResetTime) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                iMang--;
//                if (iMang==0)
//                    onDestroy();
//
//                if (iLevel > 5) showRedPoint();
//
//                showMouse();
//                countDown2();
//            }
//        }.start();
//    }

    public void levelUp() {
        iLevel++;
        iSize -= 10;
        iResetTime -= 300;
        iMaxRestTime -= 200;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            lSystemTime = SystemClock.uptimeMillis();
            if (lSystemTime == lStartTime + iResetTime) {
                if (isRedPoint == false) {
                    iMang--;
                    textViewMang.setText(String.valueOf(iMang));
                    if (iMang == 0) onDestroy();
                }

                if (iLevel > 5) showRedPoint();

                showMouse();
                countDown();
            }

            handler = new Handler();
            handler.postDelayed(this, 0);
        }
    };

    public void showRedPoint() {

        int x = random.nextInt(2);
        if (x == 1 && s.length() == 3) {
            s = s.substring(s.length() - 2);
            s += String.valueOf(x);
        }

        if (x == 0) {
            if (s.indexOf(String.valueOf(x)) == -1 && String.valueOf(x) != s.substring(2)) {
                viewMouse.setBackgroundResource(R.drawable.red_point);
                isRedPoint = true;

                if (s.length() == 3) {
                    s = s.substring(s.length() - 2);
                    s += String.valueOf(x);
                }
            } else {
                viewMouse.setBackgroundResource(R.drawable.blue_point);
                isRedPoint = false;
            }
        } else {
            viewMouse.setBackgroundResource(R.drawable.blue_point);
            isRedPoint = false;
        }
    }

    public void startApp() {
        CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                showMouse();
                countDown();
            }
        }.start();
    }
}
