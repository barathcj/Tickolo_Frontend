package com.example.tickolo;
//import com.facebook.*;
//import com.facebook.model.*;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

/**
 * This class displays all the tabs and calls appropriate classes when tabs are clicked
 * This page also has facebook login commented out. It works, but takes a long time for user to login to facebook
 * The 'buy' tab also loads multiple activities
 * @author Barath Jawahar
 *
 */
public class Page0 extends TabActivity {
	
	private static final String BUY = "Buy";
    private static final String SELL = "Sell";
    private static final String MODIFY = "Delete";
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page0);
        Intent i = getIntent();
        String type = i.getStringExtra("val");
        
        TabHost tabHost = getTabHost();
       // if(type.equals("modify")){
       // 	type=i.getStringExtra("type_val");
       // 	setDefaultTab(2);
       // }
        // Sell Tab
        TabSpec buySpec = tabHost.newTabSpec(BUY);
        buySpec.setIndicator(BUY, getResources().getDrawable(R.drawable.icon_page1));
        // Tab Icon
        Intent buyIntent;
        if(type.equalsIgnoreCase("page2")){

            buyIntent = new Intent(this, Page2.class);
            String selected = i.getStringExtra("selected");
            buyIntent.putExtra("val",selected);
            buyIntent.putExtra("type_val",i.getStringExtra("type_val"));
            type=i.getStringExtra("type_val");
        }
        else if(type.equalsIgnoreCase("contact_seller")){

            buyIntent = new Intent(this, ContactSeller.class);
            //String selected = i.getStringExtra("selected");
            //inboxIntent.putExtra("val",selected);
            buyIntent.putExtra("val",i.getStringExtra("type_val"));
            type=i.getStringExtra("type_val");
        }
        else if(type.equalsIgnoreCase("report")){

            buyIntent = new Intent(this, Report.class);
            //String selected = i.getStringExtra("selected");
            buyIntent.putExtra("val",i.getStringExtra("type_val"));
            type=i.getStringExtra("type_val");
        }
        else{
        buyIntent = new Intent(this, Page1.class);
        buyIntent.putExtra("val",type);
        }
        
    	   // Tab Content
        buySpec.setContent(buyIntent);
         
        // Sell Tab
        TabSpec sellSpec = tabHost.newTabSpec(SELL);
        sellSpec.setIndicator(SELL, getResources().getDrawable(R.drawable.icon_sell));
        Intent sellIntent = new Intent(this, Sell.class);
        sellIntent.putExtra("val",type);
        sellSpec.setContent(sellIntent);
         
        // modify Tab
        TabSpec modifySpec = tabHost.newTabSpec(MODIFY);
        modifySpec.setIndicator(MODIFY, getResources().getDrawable(R.drawable.icon_modify));
        Intent modifyIntent = new Intent(this, Modify.class);
        modifyIntent.putExtra("val",type);
        modifySpec.setContent(modifyIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(buySpec); // Adding Inbox tab
        tabHost.addTab(sellSpec); // Adding Outbox tab
        tabHost.addTab(modifySpec); // Adding Profile tab
    
        
     // start Facebook Login
	  /*  Session.openActiveSession(this, true, new Session.StatusCallback() {

	      // callback when session changes state
	      @Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {

	          // make request to the /me API
	        	Request.newMeRequest(session, new Request.GraphUserCallback() {

	        		  // callback after Graph API response with user object
	        		  @Override
	        		  public void onCompleted(GraphUser user, Response response) {
	        		    if (user != null) {
	        		      TextView welcome = (TextView) findViewById(R.id.textView2);
	        		      welcome.setText("Hello " + user.getName() + "!");
	        		    }
	        		  }
	        		}).executeAsync();
	        }
	      }
	    });
        
    */
    }
    
  /*  @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	*/
}
