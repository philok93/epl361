package com.example.cheapbasket;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SwitchView fragment = new SwitchView(R.layout.navigation);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.flContent, fragment).commit();
	}

	public void click(View view) {
		SwitchView fragment = new SwitchView();
		switch (view.getId()) {
		case R.id.bScan:
			fragment.layout = R.layout.scan;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("scan").commit();
			break;

		case R.id.bSearch:
			fragment.layout = R.layout.search;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("search").commit();
			break;

		case R.id.bBasket:
			fragment.layout = R.layout.basket;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("basket").commit();
			break;

		case R.id.bSettigs:
			fragment.layout = R.layout.settings;
			getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).addToBackStack("settings").commit();
			break;
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
