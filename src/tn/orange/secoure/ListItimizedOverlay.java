package tn.orange.secoure;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
//for adding marker
public class ListItimizedOverlay extends ItemizedOverlay<OverlayItem>
{

	private Context context;
	private ArrayList<OverlayItem> arrayListOverlayItem = new ArrayList<OverlayItem>();
	
	public ListItimizedOverlay(Drawable defaultMarker) 
	{
		super(boundCenterBottom(defaultMarker));
	}
	
	public ListItimizedOverlay(Drawable defaultMarker, Context pContext) 
	{
	  super(boundCenterBottom(defaultMarker));
	  this.context = pContext;
	}

	@Override
	protected OverlayItem createItem(int i) 
	{
		return arrayListOverlayItem.get(i);
	}

	@Override
	public int size() 
	{
		return arrayListOverlayItem.size();
	}

	@Override
	protected boolean onTap(int index) 
	{
	  OverlayItem item = arrayListOverlayItem.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	

	
	public void addOverlayItem(OverlayItem overlay) 
	{
		arrayListOverlayItem.add(overlay);
	    populate();
	}
	
}
