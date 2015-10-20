package tn.orange.secoure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class TestActivity extends MapActivity{
	static GeoPoint myP, hosP ;
	  private MapView mapView = null;
	    static double lat = 0;
	    static double lng = 0;
	    String strCompleteAddress= "";

	    Drawable drawable, drawable2;

		OverlayItem overlayItem;
		OverlayItem overlayItem2;
		
		List<Overlay> mapOverlays ;
		List<Overlay> mapOverlays2 ;
		SimpleItemizedOverlay itemizedOverlay;
		SimpleItemizedOverlay itemizedOverlay2;
		 String url= "";
		
			JSONObject e;
			@Override
    public void onCreate(Bundle savedInstanceState) {          

		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.test);
		        mapView = (MapView) findViewById(R.id.mapView2);
				mapView.setBuiltInZoomControls(true);

				final Bundle receiver = this.getIntent().getExtras();
				String id=receiver.getString("id_hospital");
				 URL data = null;
					try {
						data = new URL("http://www.atpee2011.com/hos/idHos.php?id="+id);
						System.out.println(data);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    URLConnection yc = null;
					try {
						yc = data.openConnection();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    BufferedReader in = null;
					try {
						in = new BufferedReader(
						                         new InputStreamReader(
						                         yc.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    String inputLine;

				    try {
						while ((inputLine = in.readLine()) != null) 
						     System.out.println(inputLine);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
			          JSON jsn = new JSON();
					   try{
					   JSONObject json = jsn.getJSONfromURL(data.toString());
				JSONArray  infoHopital = json.getJSONArray("hopitaux");
			        for(int i=0;i < infoHopital.length();i++){						

			        	JSONObject e = infoHopital.getJSONObject(i);
			        	
			        	
			        	double lg = Float.valueOf(e.getString("longitude").trim()).floatValue();
			        	double lt = Float.valueOf(e.getString("latitude").trim()).floatValue();
			        	GeoPoint hosP = new GeoPoint((int) (lt * 1E6), (int) (lg * 1E6));

			        	if(e.getString("nom").contains("Hopital"))
						{itemizedOverlay2 = new SimpleItemizedOverlay(this.getResources().getDrawable(R.drawable.hop), mapView);}
			        	else
			        	{ itemizedOverlay2 = new SimpleItemizedOverlay(this.getResources().getDrawable(R.drawable.clinic), mapView);}
			        	overlayItem2 = new OverlayItem(hosP, e.getString("nom") , e.getString("adresse"));
			        	itemizedOverlay2.addOverlay(overlayItem2);
			        	mapOverlays2 = mapView.getOverlays();
			        	mapOverlays2.add(itemizedOverlay2);
			        	 
			        }
				       }catch(JSONException e)        {
				    	  
				       }
			          

					    //getting my location
						   double[] listMyLoc = HelloGoogleMapActivity.getMyPos2() ; 			  
			               myP = new GeoPoint((int) (listMyLoc[0] * 1E6), (int) (listMyLoc[1]* 1E6));
			               itemizedOverlay = new SimpleItemizedOverlay(this.getResources().getDrawable(R.drawable.ic_launcher), mapView);
					   
				          // info-bulle
				          Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
				          try {
				        		 List<Address> addresses = geocoder.getFromLocation(myP.getLatitudeE6() / 1E6, myP.getLongitudeE6() / 1E6, 1);

				        		
				        		 if (addresses.size() > 0)
				        		 {
				        		 
				        		strCompleteAddress= addresses.get(0).getAddressLine(0) ;
				        		 }
				        		 Log.i("MyLocTAG => ", strCompleteAddress);
				        		//Toast.makeText(getBaseContext(), strCompleteAddress, Toast.LENGTH_LONG).show();
				        		 }
				        		 catch (IOException e) {
				        		 Log.i("MyLocTAG => ", "this is the exception part");
				        		 e.printStackTrace();
				        		 }
				          //puting marker on it
			              overlayItem = new OverlayItem(myP, "MyPlace" , strCompleteAddress);
			              itemizedOverlay.addOverlay(overlayItem);
			              mapOverlays = mapView.getOverlays();
				          mapOverlays.add(itemizedOverlay);
				          
				          
				    
			      
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
