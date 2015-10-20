package com.example.boutonand;

import android.os.Bundle;
import android.os.Handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.BaseRequestListener;
import com.facebook.android.Facebook;
import com.facebook.android.SessionStore;

import tn.orange.secoure.*;
/**
 * This example shows how to post status to Facebook wall.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 * 
 * http://www.londatiga.net
 */
public class TestPost extends Activity{
	private Facebook mFacebook;
	private CheckBox mFacebookCb;
	private ProgressDialog mProgress;
	
	private Handler mRunOnUi = new Handler();
	
	private static final String APP_ID = "270637716371533";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.post_frm);
		 Bundle extras = getIntent().getExtras();
	        final String link = extras.getString(Intent.EXTRA_TEXT);
	       
		final EditText reviewEdit = (EditText) findViewById(R.id.revieew);
		mFacebookCb				  = (CheckBox) findViewById(R.id.cb_facebook);
		
		mProgress	= new ProgressDialog(this);
		
		mFacebook 	= new Facebook(APP_ID);
		
		SessionStore.restore(mFacebook, this);

		if (mFacebook.isSessionValid())
		{
			mFacebookCb.setChecked(true);
				
			String name = SessionStore.getName(this);
			name		= (name.equals("")) ? "Unknown" : name;
				
			mFacebookCb.setText("  Facebook  (" + name + ")");
		}
		
		
		((Button) findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				String review = reviewEdit.getText().toString();
				
				if (review.equals("")) Toast.makeText(getApplicationContext(), "votre commentaire est vide !", Toast.LENGTH_LONG).show();
			
				if (mFacebook.isSessionValid()) postToFacebook(review ,link);
				else 	Toast.makeText(getApplicationContext(), "vous n'êtes pas connecté !", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private void postToFacebook(String review,String link) 
	{	
	
		mProgress.setMessage("Posting ...");
		mProgress.show();
		
		AsyncFacebookRunner mAsyncFbRunner = new AsyncFacebookRunner(mFacebook);
		
		Bundle params = new Bundle();
    		
		params.putString("message", review);
		//params.putString("name", "text");
		//params.putString("caption", "name");
		params.putString("link", link);
		//params.putString("description", "description ");
		//params.putString("picture", "lien");
		
		mAsyncFbRunner.request("me/feed", params, "POST", new WallPostListener());
		
	}

	private final class WallPostListener extends BaseRequestListener {
        public void onComplete(final String response) {
        	mRunOnUi.post(new Runnable() {
        		
        		public void run() {
        			mProgress.cancel();
        			
        			Toast.makeText(TestPost.this, "Posted to Facebook", Toast.LENGTH_SHORT).show();
        		}
        	});
        }
    }
}