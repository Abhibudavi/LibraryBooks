package com.atixiansoftwares.librarybooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;

public class SplashScreen extends AppCompatActivity {

    ImageView ivSplashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.lightgreen));
        }

        findViews();

    }

    private void findViews() {
        getSupportActionBar().hide();
        ivSplashLogo = findViewById(R.id.ic_logo_svg);
        Animation fade1 = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom_in);

        ivSplashLogo.startAnimation(fade1);
        fade1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {


                Boolean registred = PreferenceHelper.getInstance().readBoolean("REGISTERED");

                Log.v("MY","login boool "+ registred);

                if (registred) {
                    performLogin();
                } else if (!registred) {
                    performLoginAgain();
                }
            }

            public void onAnimationRepeat(Animation animation) {
                Log.v("MY", "inside on repete");


            }

            public void onAnimationStart(Animation animation) {
                Log.v("MY", "inside onstart");

            }


        });

    }

    private void performLogin() {
        Intent mainIntent = new Intent(SplashScreen.this, DashboardActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void performLoginAgain() {

        /* Intent that will start the Login-Activity. */
        Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
