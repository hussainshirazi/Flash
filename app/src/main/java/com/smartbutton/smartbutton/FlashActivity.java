package com.smartbutton.smartbutton;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class FlashActivity extends Activity {
    private Boolean check = Boolean.FALSE;
    private Camera mCamera = Camera.open();
    private Camera.Parameters params;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* //////////To go Full screen////////////// */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flash);

        /* ////////////To set the background color to Black////////////////// */
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.BLACK);

        /* /////////////Click listener for ImageButton////////////////////// */
        ImageButton offButton = (ImageButton) findViewById(R.id.offButton);
        offButton.setImageResource(R.drawable.off);
        offButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if (check == Boolean.FALSE) {
                            check = Boolean.TRUE;
                            turnOn(v);
                            torchOn();
                        } else {
                            check = Boolean.FALSE;
                            turnOff(v);
                            torchOff();
                        }
                    }
                }
        );

    }

    public void torchOn() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

            params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(params);
            mCamera.startPreview();
        } else
            Toast.makeText(getApplicationContext(), "You have no flashlight!", Toast.LENGTH_SHORT).show();
    }

    public void torchOff() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

            params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(params);
            mCamera.startPreview();
            mCamera.stopPreview();
        }
    }

    public void turnOn(View view) {
        ImageButton offButton = (ImageButton) view;
        offButton.setImageResource(R.drawable.on);
    }

    public void turnOff(View view) {
        ImageButton offButton = (ImageButton) view;
        offButton.setImageResource(R.drawable.off);
    }

}
