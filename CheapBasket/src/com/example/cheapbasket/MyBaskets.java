package com.example.cheapbasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyBaskets extends Activity {
	private PopupWindow pw;
	private View popupView;
	private LayoutInflater inflater;
	Point p;
	  String[] values;
	  final ArrayList<String> list = new ArrayList<String>();
	  int k=3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_baskets);
		Button bt1=(Button)findViewById(R.id.button6);
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPopup(MyBaskets.this, p);
				
			}
		});
		 final ListView listview = (ListView) findViewById(R.id.listViewbaskets);
		    values = new String[20];
			      values[0]="Daily Basket";
			      values[1]="Kids List";
			      values[2]="Weekly Basket";
		
	    for (int i = 0; i < k; ++i) {
	      list.add(values[i]);
	    }
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
		        android.R.layout.simple_list_item_1, list);
		    listview.setAdapter(adapter);

		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view,
		          int position, long id) {
		        final String item = (String) parent.getItemAtPosition(position);
		        if (item.compareTo("Daily Basket")==0){
		        	Intent intent=new Intent(MyBaskets.this,Basket.class);
		        startActivity(intent);
		        }else if (item.compareTo("Kids List")==0){
		        	Intent intent=new Intent(MyBaskets.this,Kids_basket.class);
		        startActivity(intent);
		      }else {
		        	Intent intent=new Intent(MyBaskets.this,Weekly_Basket.class);
		        startActivity(intent);

		      } }});
		  }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	 
	   int[] location = new int[2];
	   Button button = (Button) findViewById(R.id.button6);
	 
	   // Get the x, y location and store it in the location[] array
	   // location[0] = x, location[1] = y.
	   button.getLocationOnScreen(location);
	 
	   //Initialize the Point with x, and y positions
	   p = new Point();
	   p.x = location[0];
	   p.y = location[1]+200;
	}
	 
	// The method that displays the popup.
	private void showPopup(final Activity context, Point p) {
	   int popupWidth = 480;
	   int popupHeight = 500;
	 
	   // Inflate the popup_layout.xml
	   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
	   LayoutInflater layoutInflater = (LayoutInflater) context
	     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);
	 
	   // Creating the PopupWindow
	   final PopupWindow popup = new PopupWindow(context);
	   popup.setContentView(layout);
	   popup.setWidth(popupWidth);
	   popup.setHeight(popupHeight);
	   popup.setFocusable(true);
	   // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	   int OFFSET_X = 30;
	   int OFFSET_Y = 30;
	 
	   popup.setBackgroundDrawable(new ColorDrawable(
	            android.graphics.Color.WHITE));
	   // Displaying the popup at the specified location, + offsets.
	   popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
	 
	   // Getting a reference to Close button, and close the popup when clicked.
	   Button close = (Button) layout.findViewById(R.id.sub);
	   close.setOnClickListener(new View.OnClickListener() {
	 
	     @Override
	     public void onClick(View v) {
	    	 /*TextView txtv=(TextView)findViewById(R.id.textViewpop);
	    	 String values1[]=new String[k+1];
	    	 values1=values;
	    	 values1[k]=((TextView)txtv).getText().toString();
	    	 k++;
	    	 list.add(values1[k-1]);*/
	    	 popup.dismiss();
	     }
	   });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_baskets, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

		  final class StableArrayAdapter extends ArrayAdapter<String> {

		    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		    public StableArrayAdapter(Context context, int textViewResourceId,
		        List<String> objects) {
		      super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);
		      }
		    }

		    @Override
		    public long getItemId(int position) {
		      String item = getItem(position);
		      return mIdMap.get(item);
		    }

		    @Override
		    public boolean hasStableIds() {
		      return true;
		    }

		  }
