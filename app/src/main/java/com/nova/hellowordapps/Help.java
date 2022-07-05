package com.nova.hellowordapps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
public class help extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }
    public void backtoMenu(View view){
        finish();
    }
}























