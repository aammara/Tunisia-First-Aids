package com.example.boutonand;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import tn.orange.secoure.*;

public class Test extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    
}
