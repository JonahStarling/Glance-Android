package com.appspot.glancesocial.glance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Jonah on 8/7/15.
 */
public class SplashScreenActivity extends Activity {
    // Use LOG_TAG when logging anything
    private static final String LOG_TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_page);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "t7aZ9iBlFtU4JOXtvm2F3Hb5A0zqOXh2uysEeZ3X", "mUp7lGpKhfhHOuJ0DZCjvJOzDt8wQouo1wWsRHM9");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int DELAY = 500;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String seen_intro = sharedPref.getString(getString(R.string.seen_intro), "false");
        if (seen_intro == null || seen_intro.equals("false")) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.seen_intro), "true");
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), IntroScreenActivity.class);
            startActivity(intent);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(intent);
                }
            }, DELAY);
        }
    }
}