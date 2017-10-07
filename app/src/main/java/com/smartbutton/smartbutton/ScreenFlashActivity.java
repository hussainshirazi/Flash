package com.smartbutton.smartbutton;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

public class ScreenFlashActivity extends Activity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat gestureDetector;
    private SeekBar BrightnessSeekBar;

    private Boolean check = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //////////to go fullscreen/////////
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.screen_flash_activity);

        /////////////////To adjust brightness using SeekBar///////////
        BrightnessSeekBar = (SeekBar) findViewById(R.id.BrightnessSeekBar);
        BrightnessSeekBar.setMax(255);

        float curBrightnessValue = 0;
        try {
            curBrightnessValue = android.provider.Settings.System.getInt(
                    getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        int screen_brightness = (int) curBrightnessValue;
        BrightnessSeekBar.setProgress(screen_brightness);
        BrightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                                         int progress = 0;

                                                         @Override
                                                         public void onProgressChanged(SeekBar BrightnessSeekBar, int progressValue, boolean fromUser) {
                                                             progress = progressValue;
                                                         }

                                                         @Override
                                                         public void onStartTrackingTouch(SeekBar BrightnessSeekBar) {
                                                         }

                                                         @Override
                                                         public void onStopTrackingTouch(SeekBar BrightnessSeekBar) {
                                                             android.provider.Settings.System.putInt(getContentResolver(),
                                                                     android.provider.Settings.System.SCREEN_BRIGHTNESS,
                                                                     progress);
                                                         }
                                                     }
        );
        ///////////////////////////////////////////

        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (check == Boolean.FALSE) {
            check = Boolean.TRUE;
            setActivityBackgroundColor(Color.WHITE);
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
        } else {
            check = Boolean.FALSE;
            setActivityBackgroundColor(Color.BLACK);
            Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // setActivityBackgroundColor(Color.rgb(250,250,250));
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //  Toast.makeText(getApplicationContext(), "onDown", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //Toast.makeText(getApplicationContext(), "onLongPress", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
