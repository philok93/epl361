package com.example.cheapbasket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Navigation navigation = new Navigation();
        getFragmentManager().beginTransaction().add(R.id.content, navigation).commit();
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.b1:
                Tlacitko1 tlacitko1  = new Tlacitko1();
                getFragmentManager().beginTransaction().replace(R.id.content, tlacitko1).addToBackStack(null).commit();
                break;
            case R.id.b2:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
