package com.example.tickolo;
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

import com.example.tickolo.Page1.LoadBuy;

import android.app.ListActivity;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 * This class is used to delete items in the database
 * Sellers need to delete tickets after sold
 * @author Barath Jawahar
 *
 */
public class Modify extends ListActivity {
	List<String> thelist = new ArrayList<String>();
	String Type;
	/**
	 * Sets page to  modify_list.xml
	 * @param savedInstanceState
	 */
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.modify_list);
	//setContentView(R.layout.page1);
	Intent i = getIntent();
	String type = i.getStringExtra("val");
	Log.d("filter", type);
	Type=type;
	//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	//StrictMode.setThreadPolicy(policy);
	LoadModify loadmodify = new LoadModify();
	loadmodify.execute(type);
	try {
		thelist = loadmodify.get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ListAdapter adapter = new MobileArrayAdapter(Modify.this, thelist, Type);
	setListAdapter(adapter);
}

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
	
	LoadModify loadmodify = new LoadModify();
	loadmodify.execute("delete", selectedValue);
	try {
		thelist = loadmodify.get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
	Intent myIntent = new Intent();
	myIntent.setClassName("com.example.tickolo", "com.example.tickolo.Page0");
	//myIntent.putExtra("type_val",Type);
	//myIntent.putExtra("val","modify");
	myIntent.putExtra("val",Type);
	startActivity(myIntent);
}

/**
 * This class is used to start another thread, access http data and parse the json data
 * @author Barath Jawahar
 *
 */

class LoadModify extends AsyncTask<String, Void, List<String>> {



protected List<String> doInBackground(String... type) {
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	String result = null;
	InputStream is = null;
	List<String> thelist = new ArrayList<String>();
	//http post
	try{
	     HttpClient httpclient = new DefaultHttpClient();
	     HttpPost httppost;
	     
	     if(type[0]=="delete"){
		     httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_delete.php");
	     		nameValuePairs.add(new BasicNameValuePair("eventname", type[1]));
	     }else{
		    	 httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_modify.php");
		    	 nameValuePairs.add(new BasicNameValuePair("eventtype", type[0]));	
		     }
	     
	     
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
	             thelist.add(json_data.getString("event_name"));
	             //here "Name" is the column name in database
	             //Log.d("filter", thelist.get(j));
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



}
}
