package com.example.dialogue;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class Brulure extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brulure1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.brulure1, menu);
        return true;
    }

    
}
