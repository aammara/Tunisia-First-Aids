package tn.orange.secoure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class seformer extends Activity {
	ImageView about;
	ImageView home;
	ProgressDialog dialog;
    int increment;
    DbHelper db;
    Cursor c;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seformer);
        db = new DbHelper (getApplicationContext());
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

        ImageButton btn1 = (ImageButton) findViewById(R.id.imageButtonEtape1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.imageButtonEtape2);
        ImageButton btn3 = (ImageButton) findViewById(R.id.imageButtonEtape3);
        ImageButton btn4 = (ImageButton) findViewById(R.id.imageButtonEtape4);
        
        btn1.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent myIntent = new Intent (seformer.this, SampleTitlesStyledMethods.class);
				Bundle b = new Bundle();
				b.putInt("key", 1); //Your id
				myIntent.putExtras(b); //Put your id to your next Intent
				startActivity(myIntent);
			}
        });
		
   
        
    btn2.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent myIntent = new Intent (seformer.this, SampleTitlesStyledMethods.class);
				Bundle b = new Bundle();
				b.putInt("key", 2); //Your id
				myIntent.putExtras(b); //Put your id to your next Intent
				startActivity(myIntent);
			}
        });    
        
    btn3.setOnClickListener(new OnClickListener() {
    	
		public void onClick(View v) {
			Intent myIntent = new Intent (seformer.this, SampleTitlesStyledMethods.class);
			Bundle b = new Bundle();
			b.putInt("key", 3); //Your id
			myIntent.putExtras(b); //Put your id to your next Intent
			startActivity(myIntent);
		}
    });   
    

 btn4.setOnClickListener(new OnClickListener() {
    	
		public void onClick(View v) {
			Intent myIntent = new Intent (seformer.this, SampleTitlesStyledMethods.class);
			Bundle b = new Bundle();
			b.putInt("key", 4); //Your id
			myIntent.putExtras(b); //Put your id to your next Intent
			startActivity(myIntent);
		}
    });
}
}
