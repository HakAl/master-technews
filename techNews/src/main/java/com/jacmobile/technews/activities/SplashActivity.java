package com.jacmobile.technews.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andrewgiang.textspritzer.lib.Spritzer;
import com.andrewgiang.textspritzer.lib.SpritzerTextView;
import com.jacmobile.technews.R;

/**
 * Created by acorll on 6/8/2014.
 */
public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runAnim();
    }

    private void runAnim() {
        final SpritzerTextView tvSplash = (SpritzerTextView) findViewById(R.id.tv_splash);
        tvSplash.setWpm(300);
        tvSplash.setSpritzText(getString(R.string.splash_spritz));
        tvSplash.setOnCompletionListener(new Spritzer.OnCompletionListener()
        {
            @Override
            public void onComplete() {
                transitionToHome();
            }
        });
        tvSplash.play();
    }

    private void transitionToHome() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
