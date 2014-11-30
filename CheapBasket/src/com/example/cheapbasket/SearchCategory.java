package com.example.cheapbasket;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchCategory extends Activity {
	 MyCustomAdapter dataAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_category);
		displayListView();
		checkButtonClick();
	}
	
	private void displayListView() {
		 
		  //Array list of countries
		  ArrayList<Categories> catList = new ArrayList<Categories>();
		  Categories cat = new Categories("Dairy",false);
		  catList.add(cat);
		 cat = new Categories("Fruits and Vegetables",false);
		 catList.add(cat);
		   cat = new Categories("Meat",false);
		   catList.add(cat);
		   cat = new Categories("Vegetables",false);
		   catList.add(cat);
		   cat = new Categories("Cereals",false);
		   catList.add(cat);
		 
		  //create an ArrayAdaptar from the String Array
		  dataAdapter = new MyCustomAdapter(this,R.layout.catinfo, catList);
		  ListView listView = (ListView) findViewById(R.id.listView2);
		  // Assign adapter to ListView
		  listView.setAdapter(dataAdapter);
		 
		 
		  listView.setOnItemClickListener(new OnItemClickListener() {
		   public void onItemClick(AdapterView<?> parent, View view,
		     int position, long id) {
		    // When clicked, show a toast with the TextView text
			   Categories country = (Categories) parent.getItemAtPosition(position);
		    Toast.makeText(getApplicationContext(),
		      "Clicked on Row: " + country.getName(), 
		      Toast.LENGTH_SHORT).show();
		   }
		  });
		 
		 }
		 
		 private class MyCustomAdapter extends ArrayAdapter<Categories> {
		 
		  private ArrayList<Categories> catList;
		 
		  public MyCustomAdapter(Context context, int textViewResourceId, 
		    ArrayList<Categories> catList) {
		   super(context, textViewResourceId, catList);
		   this.catList = new ArrayList<Categories>();
		   this.catList.addAll(catList);
		  }
		 
		  private class ViewHolder {
		   CheckBox name;
		  }
		 
		  public View getView(int position, View convertView, ViewGroup parent) {
		 
		   ViewHolder holder = null;
		   Log.v("ConvertView", String.valueOf(position));
		 
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.catinfo, null);
		 
		   holder = new ViewHolder();
		   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox2);
		   convertView.setTag(holder);
		 
		    holder.name.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {  
		      CheckBox cb = (CheckBox) v ;  
		      Categories country = (Categories) cb.getTag();  
		      Toast.makeText(getApplicationContext(),
		       "Clicked on Checkbox: " + cb.getText() +
		       " is " + cb.isChecked(), 
		       Toast.LENGTH_SHORT).show();
		      country.setSelected(cb.isChecked());
		     }  
		    });  
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   Categories country = catList.get(position);
		   holder.name.setText(country.getName());
		   holder.name.setChecked(country.isSelected());
		   holder.name.setTag(country);
		 
		   return convertView;
		 
		  }
		 
		 }
		 
		 private void checkButtonClick() {
		 
		 
		  Button myButton = (Button) findViewById(R.id.buttoncheck);
		  myButton.setOnClickListener(new View.OnClickListener() {
		 
		   @Override
		   public void onClick(View v) {
		 
		    StringBuffer responseText = new StringBuffer();
		    responseText.append("The following were selected:\n");
		 
		    ArrayList<Categories> catList = dataAdapter.catList;
		    for(int i=0;i<catList.size();i++){
		    	Categories cat = catList.get(i);
		     if(cat.isSelected()){
		      responseText.append("\n" + cat.getName());
		     }
		    }
		 
		    Toast.makeText(getApplicationContext(),
		      responseText, Toast.LENGTH_LONG).show();
		 
		   }
		  });
		 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_category, menu);
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
