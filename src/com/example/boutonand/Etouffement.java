package com.example.boutonand;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import tn.orange.secoure.*;
public class Etouffement extends Activity {
	QuickAction mQuickAction;
	ActionItem facebookicon = new ActionItem();


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       


        
        
        setContentView(R.layout.etouffement_frm);
       final CheckConnectivity checkConnectivity = new CheckConnectivity();
       Button btn1 = (Button) findViewById(R.id.button1);
       
    
		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				if (checkConnectivity.checkNow(Etouffement.this)){ 
			
					
					
					
					
					
					
					
					Intent videoClient = new Intent(Intent.ACTION_VIEW);
					videoClient.setData(Uri.parse("http://www.youtube.com/watch?v=zWBbOt9C-u0&list=PL9AB7E1F4DEBCA00A&index=18&feature=player_embedded"));//you can try here your own video url
					videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity");
					try{
					    startActivity(videoClient);
					}catch(ActivityNotFoundException excp){
					    try{
					        videoClient.setClassName("com.google.android.youtube", "com.google.android.youtube.WatchActivity");
					         startActivity(videoClient);
					    }catch(ActivityNotFoundException exc){
					        exc.printStackTrace();
					    }
					
					
					
					
					
					
					
					
					
					
					} 	
			}
		     
			   
		   	else {  
				Toast.makeText(getApplicationContext(), "pas de connexion", Toast.LENGTH_SHORT).show();
		}
			}
		});
			
		
        
	
	        
			
	        btn1.setOnLongClickListener(new View.OnLongClickListener() {

				
				public boolean onLongClick(View v) {

					mQuickAction = new QuickAction(v);
					facebookicon.setTitle("facebook");
					facebookicon.setIcon(getResources().getDrawable(R.drawable.facebook));
					mQuickAction.addActionItem(facebookicon);
					
					mQuickAction.setAnimStyle(R.anim.rail);
					mQuickAction.show();
					return true;
				
					
					
				}
			});
			
			facebookicon.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					mQuickAction.dismiss();
					Intent e = new Intent(Etouffement.this, TestConnect.class);
					 e.putExtra(android.content.Intent.EXTRA_TEXT,"http://www.youtube.com/watch?v=zWBbOt9C-u0&list=PL9AB7E1F4DEBCA00A&index=18&feature=player_embedded");
					
					startActivity(e);
					
				}
				
			
			
			});
       
			
			
    }

    }


