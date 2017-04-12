package com.acubed.android.singlescreenapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // All UI components
    private ImageButton mImageButtonPhone;
    private ImageButton mImageButtonEmail;
    private ImageButton mImageButtonTwitter;
    private ImageButton mImageButtonFacebook;
    private Button mButton;

    // Various Identifiers
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all UI components
        mImageButtonPhone = (ImageButton) findViewById(R.id.button_phone);
        mImageButtonEmail = (ImageButton) findViewById(R.id.button_email);
        mImageButtonTwitter = (ImageButton) findViewById(R.id.button_twitter);
        mImageButtonFacebook = (ImageButton) findViewById(R.id.button_facebook);
        mButton = (Button) findViewById(R.id.button_menu);

        mImageButtonPhone.setOnClickListener(this);
        mImageButtonEmail.setOnClickListener(this);
        mImageButtonTwitter.setOnClickListener(this);
        mImageButtonFacebook.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    /**
     * Invokes methods for individual call to action buttons
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_phone:
                openPhone();
                break;
            case R.id.button_email:
                openEmail();
                break;
            case R.id.button_facebook:
                mIntent = openFacebook(MainActivity.this);
                startActivity(mIntent);
                break;
            case R.id.button_twitter:
                mIntent = openTwitter(MainActivity.this);
                startActivity(mIntent);
                break;
            case R.id.button_menu:
                startActivity(new Intent(MainActivity.this, PopWindow.class));
                break;
        }
    }

    /**
     * This method opens up phone app
     */
    public void openPhone() {
        mIntent = new Intent(Intent.ACTION_DIAL);
        mIntent.setData(Uri.parse(getString(R.string.action_call)));
        startActivity(mIntent);
    }

    /**
     * This method opens up email client
     */
    public void openEmail() {
        mIntent = new Intent(Intent.ACTION_SENDTO);
        mIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        mIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.action_email)});
        if (mIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mIntent);
        }
    }

    /**
     * This method opens Facebook app
     * @param context
     * @return Intent
     */
    public static Intent openFacebook(Context context) {
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://root"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/"));
        }
    }

    /**
     * This method opens Twitter app
     * @param context
     * @return Intent
     */
    public static Intent openTwitter(Context context) {
        Intent intent;
        try {
            // get the Twitter app if possible
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://root"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
            return intent;
        }
    }
}