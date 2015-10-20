package tn.orange.secoure;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class Defaultdashboardactivity extends Activity {

	public void onClickHome (View v)
	{
	    goHome (this);
	}
	public void goHome(Context context) 
	{@SuppressWarnings("unused")
	Intent show;
		
			show = new Intent(context,
					DashBoardActivity.class);

		
			
		}
		
	    
	}

