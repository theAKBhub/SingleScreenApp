package com.acubed.android.singlescreenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Class - PopWindow
 * Function - Opens a popup window that displays the menu. The window can be
 * closed by clicking anywhere outside the window
 */
public class PopWindow extends AppCompatActivity {
    private static final int SCREEN_WIDTH_PIXELS = 1080;
    private static final double SIZE_PERCENTAGE = 0.9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_menu);

        setPopWindowSize();
    }

    /**
     * This method sets width and height of pop-up window that opens
     * If screen width (px) is more than 1080 then calculate menu width based on 1080
     * Else get screen width and calculate menu width
     */
    public void setPopWindowSize() {
        int width = 0;
        int height = 0;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        if (displayMetrics.widthPixels > SCREEN_WIDTH_PIXELS) {
            width = (int)(SCREEN_WIDTH_PIXELS * SIZE_PERCENTAGE);
        } else {
            width = (int) (displayMetrics.widthPixels * SIZE_PERCENTAGE); // sets width to 80% of window width
        }
        height = (int)(displayMetrics.heightPixels * SIZE_PERCENTAGE); // sets height to 80% of window height
        getWindow().setLayout(width, height);
    }
}

