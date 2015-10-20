package tn.orange.secoure;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class alert extends Activity implements OnClickListener{
	ImageView about;
	ImageView home;
	ProgressDialog dialog;
    int increment;
    DbHelper db;
    Cursor c;
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert);
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

        ImageButton btn1 = (ImageButton) findViewById(R.id.imageButtonPolice);
        ImageButton btn2 = (ImageButton) findViewById(R.id.imageButtonSamu);
        ImageButton btn3 = (ImageButton) findViewById(R.id.imageButtonPompier);
        
        btn1.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:197"));
				startActivity(callIntent);
			}
        });
		
   
        
    btn2.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:190"));
				startActivity(callIntent);
			}
        });    
        
    btn3.setOnClickListener(new OnClickListener() {
    	
		public void onClick(View v) {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:198"));
			startActivity(callIntent);
		}
    });   
    ImageButton startbtn = (ImageButton) findViewById(R.id.imageButtonProches);
    startbtn.setOnClickListener(this);
}

public void onClick(View view) { 

    // get the increment value from the text box
    //EditText et = (EditText) findViewById(R.id.increment);
    // convert the text value to a integer
    increment = 5;

    dialog = new ProgressDialog(this);
    dialog.setCancelable(true);
    dialog.setMessage("Envoi en cours...");
    // set the progress to be horizontal
    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    // reset the bar to the default value of 0
    dialog.setProgress(0);

    // get the maximum value
    //EditText max = (EditText) findViewById(R.id.maximum);
    // convert the text value to a integer
    //int maximum = Integer.parseInt(max.getText().toString());
    // set the maximum value

    // create a thread for updating the progress bar
    Thread background = new Thread (new Runnable() {
       public void run() {
           try {
               // enter the code to be run while displaying the progressbar.
        	    c = db.getINFOS();
        	    c.moveToFirst();
        	    String name = c.getString(c.getColumnIndex("value"));
        	    c = db.getNUMBERS();
        	    c.moveToFirst();
        	    
               // This example is just going to increment the progress bar:
               // So keep running until the progress value reaches maximum value
               do {
                   // wait 500ms between each update
                   Thread.sleep(500);
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(c.getString(c.getColumnIndexOrThrow("number")), null,
                    "au secour, "+name+" a besoin de votre aide.. BY SECOURISME APP", null,null);
                    // active the update handler //
                   progressHandler.sendMessage(progressHandler.obtainMessage());
               }while (dialog.getProgress()<= dialog.getMax() && c.moveToNext());
               dialog.dismiss();
           } catch (Exception e) {
               // if something fails do something smart
           }
       }
    });
    dialog.setMax(db.getNUMBERS().getCount());
    // display the progressbar
    if(db.getNUMBERS().getCount()!=0)
    {
    dialog.show();
    background.start();
    }
    else
    	Toast.makeText(getApplicationContext(), 
    			"aucun numŽro d'urgence n'a ŽtŽ trouver..",  Toast.LENGTH_LONG).show();

}

// handler for the background updating
@SuppressLint({ "HandlerLeak" })
Handler progressHandler = new Handler() {
    public void handleMessage(Message msg) {
        dialog.incrementProgressBy(increment);
    }
};
}
