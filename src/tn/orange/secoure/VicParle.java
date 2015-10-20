package tn.orange.secoure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VicParle extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.victimeparle);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setBackgroundColor(Color.parseColor("#990000"));
        btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Etouffement.class);
				startActivity(i);
			}
		});
        
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setBackgroundColor(Color.parseColor("#990000"));
        btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Saignement.class);
				startActivity(i);
			}
		});
        
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setBackgroundColor(Color.parseColor("#990000"));
       btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Malaise.class);
				startActivity(i);
			}
		});
        
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setBackgroundColor(Color.parseColor("#990000"));
        
        btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Plaie.class);
				startActivity(i);
			}
		});
        Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setBackgroundColor(Color.parseColor("#990000"));
        btn5.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Brulure.class);
				startActivity(i);
			}
		});
        
        Button btn6 = (Button) findViewById(R.id.button6);
        btn6.setBackgroundColor(Color.parseColor("#990000"));
        btn6.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Traumatisme.class);
				startActivity(i);
			}
		});
        
        Button btn7 = (Button) findViewById(R.id.button7);
        btn7.setBackgroundColor(Color.parseColor("#990000"));
        btn7.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(VicParle.this,Fracture.class);
				startActivity(i);
			}
		});
        
	}
}
