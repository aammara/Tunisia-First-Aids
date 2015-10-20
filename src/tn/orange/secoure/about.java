package tn.orange.secoure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class about extends Activity{
	ImageView about;
	ImageView home;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        //******** HIDE ABOUT IMAGE START ********//
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
        //******** HOME IMAGE END ********//
}
}
