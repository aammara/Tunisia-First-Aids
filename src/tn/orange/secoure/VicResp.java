package tn.orange.secoure;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class VicResp extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vicresp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vicresp, menu);
        return true;
    }

    
}
