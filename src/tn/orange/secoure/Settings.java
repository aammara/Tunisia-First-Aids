package tn.orange.secoure;
 
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class Settings extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_frame);
 
        final TabHost tabHost = getTabHost();
 
        // Tab for Songs
        
        TabSpec songspec = tabHost.newTabSpec("Badge");
        songspec.setIndicator("", getResources().getDrawable(R.drawable.badge));
        Intent songsIntent = new Intent(this, PHS.class);
        songspec.setContent(songsIntent);

     // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Contacts");
        photospec.setIndicator("", getResources().getDrawable(R.drawable.contacts));
        Intent photosIntent = new Intent(this, Contacts.class);
        photospec.setContent(photosIntent);
        
        tabHost.getTabWidget().setBackgroundColor(Color.parseColor("#FFFFFF"));
        tabHost.addTab(songspec);
        tabHost.addTab(photospec); // Adding photos tab
        setTabColor(tabHost);
        
        // Adding all TabSpec to TabHost
    }
    public static void setTabColor(TabHost tabhost) {

        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)

        {

            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#990000")); //unselected

        }

        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#990000")); // selected

    }
}