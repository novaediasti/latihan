package com.nova.hellowordapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.Animation.AnimationListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
public class splash extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startAnimating();
    }
    private void startAnimating() {
// Animasi untuk top title
        TextView logo1 = (TextView)
                findViewById(R.id.TextViewTopTitle);
        Animation fade1 = AnimationUtils.loadAnimation(this,
                R.anim.fade_in1);
        logo1.startAnimation(fade1);
// Animasi untuk bottom title.
        TextView logo2 = (TextView)
                findViewById(R.id.TextViewBottomTitle);
        Animation fade2 = AnimationUtils.loadAnimation(this,
                R.anim.fade_in2);
        logo2.startAnimation(fade2);
// Beralih ke main menu jika animasi bottom title berakhir
        fade2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
// Animasi berakhir, untuk berpindah ke main menu
                screen
                startActivity(new Intent(splash.this,
                        awal.class));
                splash.this.finish();
            }
            public void onAnimationRepeat(Animation animation) {
            }
            public void onAnimationStart(Animation animation) {
            }
        });
// Animasi untuk objek dalam TableLayout
        Animation spinin = AnimationUtils.loadAnimation(this,
                R.anim.custom_anim);
        LayoutAnimationController controller = new
                LayoutAnimationController(spinin);
        TableLayout table = (TableLayout)
                findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.setLayoutAnimation(controller);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
//Animasi berhenti
        TextView logo1 = (TextView)
                findViewById(R.id.TextViewTopTitle);
        logo1.clearAnimation();
        TextView logo2 = (TextView)
                findViewById(R.id.TextViewBottomTitle);
        logo2.clearAnimation();
        TableLayout table = (TableLayout)
                findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        startAnimating();
    }
}