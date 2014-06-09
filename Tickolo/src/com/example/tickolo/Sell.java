package com.example.tickolo;

/**
 * This class gets data from the UI, starts a thread to upload data to the server
 * @author Barath Jawahar
 *
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

public class Sell extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell);
        Intent i = getIntent();
		final String Etype = i.getStringExtra("val");
        Button submit = (Button) findViewById(R.id.submit);
       
        
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 
                 EditText name = (EditText)findViewById(R.id.editText1);
                 EditText date = (EditText)findViewById(R.id.editText3);
                 EditText section = (EditText)findViewById(R.id.editText4);
                 EditText row = (EditText)findViewById(R.id.editText5);
                 EditText price = (EditText)findViewById(R.id.editText7);
                 EditText fvalue = (EditText)findViewById(R.id.editText8);
                 EditText number = (EditText)findViewById(R.id.editText6);
                 final String Name = name.getText().toString();
                 final String Date = date.getText().toString();
                 final String Section = section.getText().toString();
                 final String Row = row.getText().toString();
                 final String Price = price.getText().toString();
                 final String Fvalue = fvalue.getText().toString();
                 final String Number = number.getText().toString();
                
            	UploadSell uploadsell = new UploadSell();
        		uploadsell.execute(Name, Date, Section, Row, Price, Fvalue, Number, Etype);
        		Toast.makeText(Sell.this, "ticket posted", Toast.LENGTH_SHORT).show();
        		//Toast.makeText(Report.this, "Seller reported", Toast.LENGTH_SHORT).show();
        		Intent myIntent = new Intent();
            	myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
            	myIntent.putExtra("val",Etype);
    	    	startActivity(myIntent);
                //showDialog(DATE_DIALOG_ID);
            }
        });
        
    }
	
	/**
	 * This class is used to start another thread, to post http data
	 * @author Barath Jawahar
	 *
	 */
	class UploadSell extends AsyncTask<String, Void, List<String>> {

		   
		/**
		 * A process runs in the background
		 * @param type - paramaters for the query
		 */
	    protected List<String> doInBackground(String... type) {
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			String result = null;
			InputStream is = null;
			List<String> thelist = new ArrayList<String>();
			//http post
			try{
			     HttpClient httpclient = new DefaultHttpClient();
			     HttpPost httppost;
			     httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_upload.php");
			     nameValuePairs.add(new BasicNameValuePair("name", type[0]));
			     nameValuePairs.add(new BasicNameValuePair("date", type[1]));
			     nameValuePairs.add(new BasicNameValuePair("section", type[2]));
			     nameValuePairs.add(new BasicNameValuePair("row", type[3]));
			     nameValuePairs.add(new BasicNameValuePair("price", type[4]));
			     nameValuePairs.add(new BasicNameValuePair("fvalue", type[5]));
			     nameValuePairs.add(new BasicNameValuePair("number", type[6]));
			     nameValuePairs.add(new BasicNameValuePair("type", type[7]));
			     //Why to use 10.0.2.2
			     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			     HttpResponse response = httpclient.execute(httppost);
			     HttpEntity entity = response.getEntity();
			     is = entity.getContent();
			     BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			     StringBuilder sb = new StringBuilder();
			     sb.append(reader.readLine() + "\n");
			     String error= sb.toString();
			     Log.e("log_tag", "Error in http connection"+error);
			     }catch(Exception e){
			         Log.e("log_tag", "Error in http connection"+e.toString());
			         return null;
			    }
			return null;
			//convert response to string
			
			
	    }
	    @Override
	    protected void onPostExecute(List<String> list) {
	        // dismiss the dialog after getting all products
	       // pDialog.dismiss();
	        // updating UI from Background Thread
	    	
	        runOnUiThread(new Runnable() {
	            public void run() {
	                /**
	                 * Updating parsed JSON data into ListView
	                 * */
	            	
	            }
	        });

	    }
	    
	    
	}
	

}
