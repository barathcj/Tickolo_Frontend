package com.example.tickolo;

/**
 * This class displays the report page to report a seller 
 * @author Barath Jawahar
 *
 */

import com.example.tickolo.Sell.UploadSell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Report extends Activity {
	/**
	 * Sets page to  report.xml
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		Button submit = (Button) findViewById(R.id.button1);
		Intent i = getIntent();
		final String type = i.getStringExtra("val");
		submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

        		Toast.makeText(Report.this, "Seller reported", Toast.LENGTH_SHORT).show();
        		Intent myIntent = new Intent();
            	myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
            	myIntent.putExtra("val",type);
    	    	startActivity(myIntent);
                //showDialog(DATE_DIALOG_ID);
            }
        });
		
	}

}
