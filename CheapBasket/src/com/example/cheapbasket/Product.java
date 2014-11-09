package com.example.cheapbasket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Product extends Activity{
	
	
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.product);
	        
	        Button bt1=(Button)findViewById(R.id.button1);
	        bt1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(Product.this,MainActivity.class);
					   startActivity(intent);
				}
			});
	        
	    }
	  
}
