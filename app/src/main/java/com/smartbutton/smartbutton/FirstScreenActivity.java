package com.smartbutton.smartbutton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class FirstScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);
        setContentView(R.layout.activity_first_screen);

        Button TorchButton = (Button) findViewById(R.id.TorchButton);
        TorchButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        TorchButtonClicked(v);
                    }
                }
        );
        Button ScreenFlashButton = (Button) findViewById(R.id.ScreenFlashButton);
        ScreenFlashButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        ScreenFlashButtonClicked(v);
                    }
                }
        );
    }

    public void TorchButtonClicked(View view) {
        Intent i = new Intent(getApplicationContext(), FlashActivity.class);
        startActivity(i);
    }

    public void ScreenFlashButtonClicked(View view) {
        Intent i = new Intent(getApplicationContext(), ScreenFlashActivity.class);
        startActivity(i);
    }
}

