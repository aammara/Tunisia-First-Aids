package tn.orange.secoure;


import java.util.ArrayList;
import java.util.HashMap;


import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView ;
import android.widget.SimpleAdapter;
import android.widget.Toast;


@SuppressWarnings("deprecation")
@SuppressLint("ParserError")
public class Contacts extends Activity {
	 String txt = null;
	 ImageView new_contact;
	 ImageView add;
	 EditText number;
	 ListView list;
	 DbHelper db;
	 public static final int PICK_CONTACT    = 1;

/////////// FILL LIST VOID START /////////////
	 void fillList()
	 {
		 ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		 Cursor c = db.getNUMBERS();
		   startManagingCursor(c);
		   if(c.getCount()>0)
		   {
		  if(c!=null)
		  {
		   do
		   {
			   HashMap<String, String> map = new HashMap<String, String>();
	        	
	        	map.put("name",  c.getString(1));
	        	map.put("num",  c.getString(0));
	        	
	        	mylist.add(map);
		   }
		   while(c.moveToNext());
		  }
		  list = (ListView)findViewById(R.id.listView1);
	       SimpleAdapter mSchedule = new SimpleAdapter (getBaseContext(), mylist, R.layout.contact_list_items,
	               new String[] {"name", "num"}, new int[] { R.id.name_list, R.id.num_list });
	        list.setAdapter(mSchedule);
		   }
		   else
			   list.setAdapter(new ArrayAdapter<String>(this,R.id.listView1, 0));   
		c.close();
	 }
/////////// FILL LIST VOID END /////////////
	 
/////////// DELETE DIALOG CHOICE START /////////////
	 void deleteChoice(final String num)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Supprimer ?");
			builder.setMessage("Voulez vous vraiment supprimer"+" "+num+"?")
			       .setCancelable(false)
			       .setPositiveButton("supprimer", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	    db = new DbHelper(getBaseContext());
			        	   db.remove(num);
			        	   fillList();
			        	   dialog.cancel();
			           }
			       })
			       .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
	 	              public void onClick(DialogInterface dialog, int id) {
	 	            	  dialog.cancel();
	 	              }
	 	           });
			AlertDialog alert = builder.create();
			alert.show();
		}
/////////// DELETE DIALOG CHOICE END //////////////////
		 
	 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        db = new DbHelper(getApplicationContext());
        list = (ListView) findViewById(R.id.listView1);
        fillList();
        list.setOnItemLongClickListener(new OnItemLongClickListener() 
        {
        	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
    {
        		@SuppressWarnings("unchecked")
				HashMap<String, String> map = (HashMap<String, String>) list.getItemAtPosition(arg2);
        	    
    deleteChoice(map.get("num"));
    	return false;
    }
      		});

        new_contact = (ImageView)findViewById(R.id.add_contact);
        new_contact.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				callDialog("","");
			}
        });
        
     add = (ImageView)findViewById(R.id.ajouter);
     add.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				 Intent intent = new Intent(Intent.ACTION_PICK, Phone.CONTENT_URI);
                 startActivityForResult(intent, PICK_CONTACT);
			}
				// TODO Auto-generated method stub
				/* 
				*/
			
     });
    }
/////////// CALL DIALOG START /////////////
  
    void callDialog (String num, String name)
    {
    	// Setting  dialog
        final Dialog add_dialog = new Dialog(Contacts.this);
        add_dialog.setContentView(R.layout.add_contact);
      //  add_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        add_dialog.setTitle("Nouveau Contact : ");
        final EditText nameTEXT = (EditText) add_dialog.findViewById(R.id.name);
        final EditText numTEXT = (EditText) add_dialog.findViewById(R.id.num);
        nameTEXT.setText(name);
        numTEXT.setText(num);
        Button yes_button = (Button) add_dialog.findViewById(R.id.yes_button);
//yes Button
        yes_button.setText("OK");
        yes_button.setTextAppearance(Contacts.this,R.style.Button);

        yes_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(numTEXT.getText().length()==0)
    				Toast.makeText(getApplicationContext(), "Désolé, mais vous n'avez saisi aucun numéro.." , Toast.LENGTH_LONG).show();
    				else
    				{
    				String num = numTEXT.getText().toString();
    				String name = nameTEXT.getText().toString();	
    				if(db.exist(num))
    				Toast.makeText(getApplicationContext(), "Numéro existe déja.." , Toast.LENGTH_LONG).show();
    				else
    				{	
    				db.insertNUM(num, name);
    				Toast.makeText(getApplicationContext(), "Numéro enregistrer.." , Toast.LENGTH_LONG).show();
    				fillList();
    				}
    				}
            	add_dialog.dismiss();
            }
        });
//No Button
        Button no_button = (Button) add_dialog
                .findViewById(R.id.no_button);
        no_button.setText("annuler");
        no_button.setTextAppearance(Contacts.this,
                R.style.Button);

        no_button.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
            	add_dialog.dismiss();
            }
        });
        
//show Exit Dialog
        add_dialog.show();
        
        
}

    
/////////// CALL DIALOG END /////////////  
 
     @Override
     public void onActivityResult(int reqCode, int resultCode, Intent data) {
             super.onActivityResult(reqCode, resultCode, data);

             switch (reqCode) {
                     case (PICK_CONTACT):
                             if (resultCode == Activity.RESULT_OK) {
                                     Uri contactData = data.getData();
                                     Cursor c = managedQuery(contactData, null, null, null, null);
                                     if (c.moveToFirst()) {
                                             String name = c.getString(c.getColumnIndexOrThrow(People.DISPLAY_NAME));
                                             String num = c.getString(c.getColumnIndexOrThrow(Phone.NUMBER));
                                             callDialog(num,name);
                                     }
                             }
                             break;
             }
             
    }
   
protected void onDestroy()
{
db.close();
super.onDestroy();
}
}