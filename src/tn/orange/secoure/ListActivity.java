package tn.orange.secoure;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListActivity extends Activity {
	String url;
	JSONObject e=null ;
	@Override
    public void onCreate(Bundle savedInstanceState) {          

		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.list);
		        
		      //*** ABOUT START ***/
		        ImageView about;
		        about = (ImageView) findViewById(R.id.about);
		        about.setImageDrawable(null);
		      //*** ABOUT END ***/  
		        
		        JSON jsn = new JSON();
		            	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		            	final double[] listMyLoc = HelloGoogleMapActivity.getMyPos2() ; 	
		            			   try{
		            				   url = "http://www.atpee2011.com/hos/connexion.php?"+HelloGoogleMapActivity.getMyPos();
		            			JSONObject json = jsn.getJSONfromURL(url);
		            		    JSONArray  infoHopital = json.getJSONArray("hopitaux");
		            	        for(int i=0;i < infoHopital.length();i++){						
		            	        	HashMap<String, String> map = new HashMap<String, String>();
		            	          e = infoHopital.getJSONObject(i);
		            	        	
		            	        	map.put("title",  e.getString("nom"));
		            	        	map.put("address",  e.getString("adresse"));
		            	        	map.put("tel",e.getString("tel"));
		            	        	map.put("id",e.getString("id"));
		            	        	map.put("latitude",e.getString("latitude"));
		            	        	map.put("longitude",e.getString("longitude"));
		            	        	mylist.add(map);
		            	        	
		            		}
		            	       }catch(JSONException e)        {
		            	    	  
		            	       }
		            	       final ListView list = (ListView)findViewById(R.id.h_list);
		            	       SimpleAdapter mSchedule = new SimpleAdapter (getBaseContext(), mylist, R.layout.h_items,
		            	               new String[] {"title", "address", "tel"}, new int[] { R.id.title, R.id.address, R.id.tel });
		            	        list.setAdapter(mSchedule);
		            	        list.setOnItemClickListener(new OnItemClickListener() {
		            	        	 //  @SuppressWarnings("unchecked")
		            	        	          public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		            	        	       @SuppressWarnings("unchecked")
										HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(position); 
		            	        	      /* Bundle myBundle = new Bundle();
		            	        	       myBundle.putString("id_hospital", map.get("id"));
		            	        	       Intent myIntent = new Intent(ListActivity.this, TestActivity.class );
		            	        	   	   myIntent.putExtras(myBundle);
		            	        	   	  startActivity(myIntent)*/
		            	        	       
		            	        	       
		            	        	       
		            	        	     url = "http://maps.google.com/maps?saddr=" +listMyLoc[0]+","+listMyLoc[1]+"&daddr="+map.get("latitude")+","+map.get("longitude")+"&maptype=hybrid&sensor=false&key=0efAtp0ViHEQEueBdBDBST0Dg1PbT__lHK0LYjQ";
										
		            	        	       Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
		            	        	       intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		            	        	       startActivity(intent);
		            	        	   	   
		            	        	          }
		            	        	   });
		            }
	      


    }

