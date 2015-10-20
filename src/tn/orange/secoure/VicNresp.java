package tn.orange.secoure;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VicNresp extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vicnresp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vicnresp, menu);
        return true;
    }

    
}
