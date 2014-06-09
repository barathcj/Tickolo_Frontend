
package com.example.tickolo;

/**
 * This class is used to display particular events in detail
 * It displays a tablelayout
 * @author Barath Jawahar
 *
 */

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Page2 extends Activity implements OnClickListener {
	
	String Type;
	/**
	 * Sets page to  page2.xml
	 * starts a new thread, gets data from it and displays it
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView eventHeading;
		TextView eventDate;
		Intent i = getIntent();
		String event = i.getStringExtra("val");
		Type = i.getStringExtra("type_val");
		Log.d("filter", event);
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.page2);
		TableLayout tl = (TableLayout)findViewById(R.id.tableLayout1);
		List<String> eventDetails = new ArrayList<String>();
		RetrieveEvents eventDetailsThread = new RetrieveEvents();
		eventDetailsThread.execute("eventdetails", event);
		try {
			
			eventDetails = eventDetailsThread.get();
			for (int a =0; a<eventDetails.size()/8; a++){
				eventHeading = (TextView) findViewById(R.id.eventHeading);
				eventHeading.setText(eventDetails.get(a));
				eventDate = (TextView) findViewById(R.id.eventDate);
				String date = eventDetails.get(a+6);
				date = date.substring(0, date.length()-9);
				eventDate.setText(date);
				TableRow tr = new TableRow(this);
			    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
			    tr.setPadding(40, 20, 0, 0);
			    tr.setId(a);
			    tr.setClickable(true);
			    tr.setOnClickListener(this);
			    
			/*    String uri = "@drawable/row.xml";
			    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
			    Drawable res = getResources().getDrawable(imageResource);
			    tr.setBackgroundDrawable(res);
			  */  
			    
			    TextView tv1 = new TextView(this);
				//tv1.setPadding(8, 8, 8, 8);
				tv1.setText(eventDetails.get(a*8+2));
				TextView tv2 = new TextView(this);
				//tv2.setPadding(8, 8, 8, 8);
				tv2.setText(eventDetails.get(a*8+3));
				TextView tv3 = new TextView(this);
				//tv2.setPadding(5, 5, 5, 5);
				tv3.setText(eventDetails.get(a*8+4));
				TextView tv4 = new TextView(this);
				//tv1.setPadding(5, 5, 5, 5);
				tv4.setText(eventDetails.get(a*8+5));
				TextView tv5 = new TextView(this);
				//tv1.setPadding(5, 5, 5, 5);
				tv5.setText(eventDetails.get(a*8+7));
				tr.addView(tv1);
				tr.addView(tv2);
				tr.addView(tv3);
				tr.addView(tv4);
				tr.addView(tv5);
			    tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * To Capture clicks on the table
	 * @param v - the view being clicked on
	 */
	@Override
	  public void onClick(View v) {
		
		int id  = v.getId();
		openOptionsMenu();
	
	}
	/**
	 * initialising menu
	 * @param menu - the menu to be displayed
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
            return true;

    }
	/**
	 * if items in the menu are clicked on it starts the appropriate activity
	 * @param menu - the menu item being clicked
	 */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.contact_seller:{
	        	
	    		Intent myIntent = new Intent();
	        	//myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page2");
	    		myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
	        	//myIntent.putExtra("val",selectedValue);
	    		myIntent.putExtra("val","contact_seller");
	    		myIntent.putExtra("type_val",Type);
	    		//myIntent.putExtra("selected",selectedValue);
	        	startActivity(myIntent);
	        	
	            return true;}
	        case R.id.report:{
	        	//String selectedValue = (String) getListAdapter().getItem(position);
	    		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
	    		Intent myIntent = new Intent();
	        	//myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page2");
	    		myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
	        	//myIntent.putExtra("val",selectedValue);
	    		myIntent.putExtra("val","report");
	    		myIntent.putExtra("type_val",Type);
	    		//myIntent.putExtra("selected",selectedValue);
	        	startActivity(myIntent);
	            return true;}
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	 
	

}
