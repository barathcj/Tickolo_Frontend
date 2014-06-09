package com.example.tickolo;

import com.facebook.*;
import com.facebook.model.*;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * This class is the main acitivy class and displays the first page.
 * This page contains four image buttons and users can navigate to what type of tickets they want
 * @author Barath Jawahar
 *
 */

public class MainActivity extends Activity implements OnClickListener {
	
	/**
	 * Sets page to  activity_main.xml and adds listeners to buttons
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageButton button_music = (ImageButton)findViewById(R.id.imageButton1);
        button_music.setOnClickListener(this);
        ImageButton button_sports = (ImageButton)findViewById(R.id.imageButton2);
        button_sports.setOnClickListener(this);
        ImageButton button_movies = (ImageButton)findViewById(R.id.ImageButton01);
        button_movies.setOnClickListener(this);
        ImageButton button_events = (ImageButton)findViewById(R.id.ImageButton02);
        button_events.setOnClickListener(this);
        
        // start Facebook Login
  	    Session.openActiveSession(this, true, new Session.StatusCallback() {

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
  	        		      //TextView welcome = (TextView) findViewById(R.id.textView2);
  	        		      //welcome.setText("Hello " + user.getName() + "!");
  	        		    }
  	        		  }
  	        		}).executeAsync();
  	        }
  	      }
  	    });
          
      
		
	}
	
	// Implement the OnClickListener callback
	/**
	 * Actions when a view is clicked
	 * @param v the view being clicked
	 */
    public void onClick(View v) {
      // do something when the button is clicked
    	Intent myIntent = new Intent();
    	myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
    	switch (v.getId()) {
	    case R.id.imageButton1:{
	    	Log.d("filter", "music");
	    	//myIntent.putExtra("com.android.samples.SpecialValue", "Hello, Joe!"); // key/value pair, where key needs current package prefix.
	    	myIntent.putExtra("val","music");
	    	startActivity(myIntent);
	    	break;
	    }
	    case R.id.imageButton2:{
	    	Log.d("filter", "sports");
	    	myIntent.putExtra("val","sports");
	    	startActivity(myIntent);
	    	break;
	    }
	    case R.id.ImageButton01:{
	    	Log.d("filter", "movies");
	    	myIntent.putExtra("val","movies");
	    	startActivity(myIntent);
	    	break;
	    }
	    case R.id.ImageButton02:{
	    	Log.d("filter", "events");
	    	myIntent.putExtra("val","events");
	    	startActivity(myIntent);
	    	break;
	    }
	    	
    	}
	    	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
