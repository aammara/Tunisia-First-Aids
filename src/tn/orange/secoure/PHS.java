package tn.orange.secoure;
 
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
 
public class PHS extends Activity {
	DbHelper db;
	ListView list;
	ImageView help;
	ImageView clear;
	
	void fillList()
	 {
		 ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		 Cursor c = db.getINFOS();
		   startManagingCursor(c);
		   if(c.getCount()>0)
		   {
		  if(c!=null)
		  {
		   do
		   {
			   HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("title", c.getString(0));
	        	map.put("value",  c.getString(1));
	        	mylist.add(map);
		   }
		   while(c.moveToNext());
		  }
		  list = (ListView)findViewById(R.id.info_list);
	       SimpleAdapter mSchedule = new SimpleAdapter (getBaseContext(), mylist, R.layout.phs_items,
	               new String[] {"title", "value"}, new int[] { R.id.title, R.id.value });
	        list.setAdapter(mSchedule);
		   }
		   else
			   list.setAdapter(new ArrayAdapter<String>(this,R.id.info_list, 0));   
		c.close();
	 }
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phs);
        
        //////////////// DATABASE LINKING ///////////
        db = new DbHelper(getApplicationContext());
        list = (ListView) findViewById(R.id.info_list);
        fillList();
        list.setOnItemLongClickListener(new OnItemLongClickListener() 
        {
        	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
    {
        		@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(arg2);
        	    
    editChoice(map.get("title"),map.get("value"));
    	return false;
    }
      		});
        
        help = (ImageView)findViewById(R.id.help);
        help.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				helpDialog ();
			}
        });
        
        clear = (ImageView)findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int i=0; i<=8; i++)
				{
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(i);
				db.setINFOS(map.get("title").replace("'", "''"), "");
				fillList();
			}
			}
        });
    }

    void editChoice (final String title, final String value)
    {
    	// Setting  dialog
        final Dialog add_dialog = new Dialog(PHS.this);
        add_dialog.setContentView(R.layout.edit_infos);
      //  add_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        add_dialog.setTitle("Modifier : ");
        final TextView titleTEXT = (TextView) add_dialog.findViewById(R.id.editer_title);
        final EditText valueTEXT = (EditText) add_dialog.findViewById(R.id.value);
        titleTEXT.setText(title+" : ");
        valueTEXT.setText(value);
        Button yes_button = (Button) add_dialog.findViewById(R.id.yes_button);
//yes Button
        yes_button.setText("OK");
        yes_button.setTextAppearance(PHS.this,R.style.Button);
        yes_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	db.setINFOS(title.replace("'", "''"), valueTEXT.getText().toString());
            	fillList();
            	add_dialog.dismiss(); 
            }
        });
//No Button
        Button no_button = (Button) add_dialog.findViewById(R.id.no_button);
        no_button.setText("annuler");
        no_button.setTextAppearance(PHS.this,R.style.Button);
        no_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	add_dialog.dismiss();
            }
        });
        
//show Exit Dialog
        add_dialog.show();
        
        
}
 
    void helpDialog ()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Aide ?");
		builder.setMessage("Appuie long sur une ligne pour modifier son contenu.")
		       .setCancelable(false)
		       .setPositiveButton("  OK  ", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {

		        	   dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
    
protected void onDestroy()
{
db.close();
super.onDestroy();
}
}