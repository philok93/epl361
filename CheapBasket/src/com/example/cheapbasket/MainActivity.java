package com.example.cheapbasket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SwitchView fragment = new SwitchView(R.layout.navigation);//activity_my_baskets);
		getSupportFragmentManager().beginTransaction()		.add(R.id.flContent, fragment).commit();
	}

	public void click(View view) {
		SwitchView fragment = new SwitchView();
		if (view.getId()==R.id.bScan) {
		
			fragment.layout = R.layout.scan;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("scan").commit();
			
				
		}else if (view.getId()==R.id.bTakePicture){
			fragment.layout = R.layout.activity_results;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("product").commit();
			/*Intent intent=new Intent(this,product.class);
			startActivity(intent);*/
		}else if(view.getId()==R.id.bSearch)
		{
			Intent intent=new Intent(MainActivity.this,SearchProduct.class);
			startActivity(intent);
		}else if (view.getId()==R.id.bBasket){
		
			fragment.layout = R.layout.activity_basket;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("basket").commit();
		}
		else if (view.getId()==R.id.bSettigs){
			fragment.layout = R.layout.settings;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("settings").commit();
		}
		
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
