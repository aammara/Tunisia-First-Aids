package tn.orange.secoure;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ParserError")
public class DashBoardActivity extends Activity {
    GridView MyGrid;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        //*** SETTINGS START ***/
        ImageView settings;
        settings = (ImageView) findViewById(R.id.profil);
        settings.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent myIntent = new Intent (DashBoardActivity.this, Settings.class);
			        startActivity(myIntent);
				}
     });
       
      //*** SETTINGS END ***/
       
      //*** SETTINGS START ***/
        ImageView alert;
        alert = (ImageView) findViewById(R.id.alert);
        alert.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent myIntent = new Intent (DashBoardActivity.this, alert.class);
			        startActivity(myIntent);
				}
     });
       
      //*** SETTINGS END ***/
        
        //*** ABOUT START ***/
        ImageView about;
        about = (ImageView) findViewById(R.id.about);
        about.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent myIntent = new Intent (DashBoardActivity.this, about.class);
			        startActivity(myIntent);
				}
     });
       
      //*** ABOUT END ***/  

        MyGrid = (GridView) findViewById(R.id.MyGrid);
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		 
        HashMap<String, String> map;
        
        map = new HashMap<String, String>();
        map.put("titre", "Agir");
        map.put("img", String.valueOf(R.drawable.agir));
        listItem.add(map);
 
        map = new HashMap<String, String>();
        map.put("titre", "Se former");
        map.put("img", String.valueOf(R.drawable.former));
        listItem.add(map);
        
        map = new HashMap<String, String>();
        map.put("titre", "Hopitaux");
        map.put("img", String.valueOf(R.drawable.hopital));
        listItem.add(map);
        
        map = new HashMap<String, String>();
        map.put("titre", "Dons");
        map.put("img", String.valueOf(R.drawable.donner));
        listItem.add(map);
        
        
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.items,
               new String[] {"img", "titre"}, new int[] {R.id.img, R.id.title});
        MyGrid.setAdapter(mSchedule);
        MyGrid.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
         HashMap<String, String> map = (HashMap<String, String>) MyGrid.getItemAtPosition(position);
            if(map.get("titre")=="Agir")
            {
        	 Intent myIntent = new Intent (DashBoardActivity.this, MainActivity.class);
             startActivity(myIntent);
            }
            else if(map.get("titre")=="Se former")
            {
        	 Intent myIntent = new Intent (DashBoardActivity.this, seformer.class);
             startActivity(myIntent);
            }
            else if(map.get("titre")=="Hopitaux")
            {
        	 Intent myIntent = new Intent (DashBoardActivity.this, HelloGoogleMapActivity.class);
             startActivity(myIntent);
            }
            else if(map.get("titre")=="Dons")
            {
        	 Intent myIntent = new Intent (DashBoardActivity.this, DonnerActivity.class);
             startActivity(myIntent);
            }
            }
         	   });

    }
}