package tn.orange.secoure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DonnerActivity extends Activity {
	 /** Called when the activity is first created. */
	ImageView about;
	ImageView home;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation);
        
        about = (ImageView) findViewById(R.id.about);
        about.setImageDrawable(null);
       //******** HIDE ABOUT IMAGE END ********//
        
      //******** HOME IMAGE START ********//
        home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
		public void onClick(View arg0) {
		// TODO Auto-generated method stub
			        finish();
				}
     });
    }


}
