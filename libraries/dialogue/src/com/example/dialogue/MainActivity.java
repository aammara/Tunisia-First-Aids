package com.example.dialogue;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Setting  dialog
		final Dialog dialog1 = new Dialog(MainActivity.this);
		dialog1.setContentView(R.layout.exit_dialog_layout);
		dialog1.setTitle("la victime parle?");
		Button yes_button = (Button) dialog1
				.findViewById(R.id.yes_button);
//yes Button
		yes_button.setText("Oui");
		yes_button.setTextAppearance(MainActivity.this,
				R.style.Button);

		yes_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,VicParle.class);
				startActivity(i);
			}
		});
//No Button
		Button no_button = (Button) dialog1
				.findViewById(R.id.no_button);
		no_button.setText("Non");
		no_button.setTextAppearance(MainActivity.this,
				R.style.Button);

		no_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,MainActivitydeux.class);
				startActivity(i);
				
				
			}
		});
		
//show Exit Dialog
		dialog1.show();
        
        
		
		
		
		
		
		
		
		
		
		
		
		
		
        
    }
    
    
   
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
