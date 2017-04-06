package com.acubed.android.singlescreenapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Class - Pop
 * Function - Opens a popup window that displays the menu. The window can be
 * closed by clicking anywhere outside the window
 */
public class Pop extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_menu);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = (int)(displayMetrics.widthPixels * .8); // sets width to 80% of window width
        int height = (int)(displayMetrics.heightPixels * .8); // sets height to 80% of window height

        getWindow().setLayout(width, height);
    }
}
