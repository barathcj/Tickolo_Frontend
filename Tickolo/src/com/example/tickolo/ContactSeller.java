package com.example.tickolo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * This class just displays the page to contact a seller
 * @author Barath Jawahar
 *
 */

public class ContactSeller extends Activity {
	/**
	 * Sets page to  contact_seller.xml
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_seller);
		Intent i = getIntent();
		Button submit = (Button) findViewById(R.id.submit);
		final String type = i.getStringExtra("val");
		submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent();
            	myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
            	myIntent.putExtra("val",type);
    	    	startActivity(myIntent);
                //showDialog(DATE_DIALOG_ID);
            }
        });
		
	}

}
