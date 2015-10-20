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

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloGoogleMapActivity extends MapActivity implements
	LocationListener {
    private MapView mapView = null;
    private LocationManager lm = null;
    static double lat = 0;
    static double lng = 0;
    private GeoPoint p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
    private MapController mc = null;
    private MyLocationOverlay myLocation = null;
    Drawable drawable1;
    ListItimizedOverlay itemizedoverlay1;
	OverlayItem overlayitem1;
	List<Overlay> mapOverlays ;
	SimpleItemizedOverlay itemizedOverlay;
	private String strCompleteAddress= "my pos";

	public String getStrCompleteAddress() {
		return strCompleteAddress;
	}



	static String getMyPos ()
	{
		
		return ("latitude=" + Double.toString(lat) +"&longitude=" +Double.toString(lng)).toString();
	}
	

	
	static double[] getMyPos2 ()
	{
	double ListMyLoc[] = {lat, lng};
	
	return ListMyLoc;
		
		
	}

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.map);
	
	//*** ABOUT START ***/
    ImageView about;
    about = (ImageView) findViewById(R.id.about);
    about.setImageResource(R.drawable.h_button);
    about.setOnClickListener(new OnClickListener() {
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 Intent myIntent = new Intent (HelloGoogleMapActivity.this, ListActivity.class);
		        startActivity(myIntent);
			}
 });
   
  //*** ABOUT END ***/  
	
	
	mapView = (MapView) this.findViewById(R.id.mapView);
	mapView.setBuiltInZoomControls(true);

	lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
	lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0,
		this);
 
	myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
	
	myLocation.runOnFirstFix(new Runnable() {
		
	    public void run() {
		mc.animateTo(myLocation.getMyLocation());
		mc.setZoom(12);

		 
	    }
	});
	
	mapView.getOverlays().add(myLocation);
	mapView.invalidate() ;
	
    }

    @Override
    protected void onResume() {
	super.onResume();
	myLocation.enableMyLocation();
	myLocation.enableCompass();
    }

    @Override
    protected boolean isRouteDisplayed() {
	return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (keyCode == KeyEvent.KEYCODE_S) {
	    mapView.setSatellite(!mapView.isSatellite());
	    mapView.setStreetView(true);
	    return true;
	}
	return super.onKeyDown(keyCode, event);
    }

    public void onLocationChanged(Location location) {

	lat = location.getLatitude();
	lng = location.getLongitude();
	
	//String latitude= lat.toString();
	
	
	/*seding long lat from bd*/
	 URL data = null;
	try {
		data = new URL("http://www.atpee2011.com/hos/connexion.php?latitude=" + lat +"&longitude=" +lng);
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
    
 	
    //adding marker on hospital
    JSON jsn = new JSON();
			   try{
			   JSONObject json = jsn.getJSONfromURL("http://www.atpee2011.com/hos/connexion.php?"+getMyPos());
		JSONArray  infoHopital = json.getJSONArray("hopitaux");
	        for(int i=0;i < infoHopital.length();i++){						

	        	JSONObject e = infoHopital.getJSONObject(i);
	        	
	        	
	        	double lg = Float.valueOf(e.getString("longitude").trim()).floatValue();
	        	double lt = Float.valueOf(e.getString("latitude").trim()).floatValue();
	        	GeoPoint pnt = new GeoPoint((int) (lt * 1E6), (int) (lg * 1E6));
	        	SimpleItemizedOverlay itemizedOverlayOTHER;
	        	if(e.getString("nom").contains("Hopital"))
				{itemizedOverlayOTHER = new SimpleItemizedOverlay(this.getResources().getDrawable(R.drawable.hop), mapView);}
	        	else
	        	{ itemizedOverlayOTHER = new SimpleItemizedOverlay(this.getResources().getDrawable(R.drawable.clinic), mapView);}
	        	OverlayItem overlayItem2 = new OverlayItem(pnt, e.getString("nom") , e.getString("adresse"));
	        	itemizedOverlayOTHER.addOverlay(overlayItem2);
	        	mapOverlays = mapView.getOverlays();
	        	mapOverlays.add(itemizedOverlayOTHER);
		}
	       }catch(JSONException e)        {
	    	  
	       }
	
	
    	p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
    	Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
    
   		 
    	try {
   		 List<Address> addresses = geocoder.getFromLocation(p.getLatitudeE6() / 1E6, p.getLongitudeE6() / 1E6, 1);

   		
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
    	
  
	
	mc = mapView.getController();
	mc.setZoom(15);
}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
    

}
