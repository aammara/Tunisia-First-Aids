package tn.orange.secoure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class fourth_step extends Activity{
	ImageView about;
	ImageView home;
	ImageView next_step;
	ImageView prev_step;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_step);
        
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
        
        
        
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setBackgroundColor(Color.parseColor("#990000"));
        btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Etouffement.class);
				startActivity(i);
			}
		});
        
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setBackgroundColor(Color.parseColor("#990000"));
        btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Hemoragie.class);
				startActivity(i);
			}
		});
        
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setBackgroundColor(Color.parseColor("#990000"));
       btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Inconscience.class);
				startActivity(i);
			}
		});
        
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setBackgroundColor(Color.parseColor("#990000"));
        
        btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Malaise.class);
				startActivity(i);
			}
		});
        Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setBackgroundColor(Color.parseColor("#990000"));
        btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Brulure.class);
				startActivity(i);
			}
		});
        
        Button btn6 = (Button) findViewById(R.id.button6);
        btn6.setBackgroundColor(Color.parseColor("#990000"));
        btn6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Traumatisme.class);
				startActivity(i);
			}
		});
        
        Button btn7 = (Button) findViewById(R.id.button7);
        btn7.setBackgroundColor(Color.parseColor("#990000"));
        btn7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(fourth_step.this,com.example.boutonand.Fracture.class);
				startActivity(i);
			}
		});
        //******** HOME IMAGE END ********//
}
}
