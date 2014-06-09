package com.example.tickolo;

/**
 * This class is used to display items which can be bought
 * Only displays tickets of chosen type
 * @author Barath Jawahar
 *
 */

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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



import android.app.ListActivity;
import android.content.Intent;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.tickolo.MobileArrayAdapter;



public class Page1 extends ListActivity {
	
	List<String> thelist = new ArrayList<String>();
	String Type;
	/**
	 * Sets page to  page1.xml
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page1);
		//setContentView(R.layout.page1);
		Intent i = getIntent();
		String type = i.getStringExtra("val");
		Log.d("filter", type);
		Type=type;
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
		LoadBuy loadbuy = new LoadBuy();
		loadbuy.execute(type);
		try {
			thelist = loadbuy.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListAdapter adapter = new MobileArrayAdapter(Page1.this, thelist, Type);
		setListAdapter(adapter);
	}
	@Override
/*	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
	
	/**
	 * To Capture clicks on the list
	 * @param l - the listview of the list
	 * @param v - the view
	 * @param l - the position that is clicked 
	 * @param l - id of the item clicked
	 */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
		Intent myIntent = new Intent();
    	//myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page2");
		myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
    	//myIntent.putExtra("val",selectedValue);
		myIntent.putExtra("val","page2");
		myIntent.putExtra("type_val",Type);
		myIntent.putExtra("selected",selectedValue);
    	startActivity(myIntent);
	}
	/**
	 * This class is used to start another thread, access http data and parse the json data
	 * @author Barath Jawahar
	 *
	 */
	class LoadBuy extends AsyncTask<String, Void, List<String>> {

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
		     httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_eventlist.php");
		     //Why to use 10.0.2.2
		     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		     HttpResponse response = httpclient.execute(httppost);
		     HttpEntity entity = response.getEntity();
		     is = entity.getContent();
		     }catch(Exception e){
		         Log.e("log_tag", "Error in http connection"+e.toString());
		         return null;
		    }
		//convert response to string
		try{
		      BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		       StringBuilder sb = new StringBuilder();
		       sb.append(reader.readLine() + "\n");

		       String line="0";
		       while ((line = reader.readLine()) != null) {
		                      sb.append(line + "\n");
		        }
		        is.close();
		        result=sb.toString();
		        }catch(Exception e){
		              Log.e("log_tag", "Error converting result "+e.toString());
		        }

		
		try{
			JSONArray jArray = new JSONArray(result);
		      JSONObject json_data=null;
		      for(int j=0;j<jArray.length();j++){
		             json_data = jArray.getJSONObject(j);
		             if(json_data.getString("event_type").equals(type[0])){
		             thelist.add(json_data.getString("event_name"));
		             
		             }
		             }
		      
		      return thelist;
		      }
				
				
		   catch(JSONException e1){
		      // Toast.makeText(getBaseContext(), "No Data Found" ,Toast.LENGTH_LONG).show();
		    	  return null;
		      }
		
		catch (ParseException e1) {
		   e1.printStackTrace();
		   return null;
		 }
    }
    @Override
    protected void onPostExecute(List<String> list) {
        // dismiss the dialog after getting all products
       // pDialog.dismiss();
        // updating UI from Background Thread
    	
       

    }
    
    
}
	
}
