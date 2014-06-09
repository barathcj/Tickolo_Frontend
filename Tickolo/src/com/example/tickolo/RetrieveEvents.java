package com.example.tickolo;
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

import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
/**
 * This class starts a thread to retrieve data from the server
 * It returns a list of data
 * @author Barath Jawahar
 *
 */

class RetrieveEvents extends AsyncTask<String, Void, List<String>> {

   
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
		     //Why to use 10.0.2.2
		     if(type[0]=="eventlist")
		     httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_eventlist.php");
		     else{
		    	 httppost = new HttpPost("http://tickolo-barathcj.rhcloud.com/tickolo_eventdetails.php");
		    	 nameValuePairs.add(new BasicNameValuePair("eventname", type[1]));	
		     }
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
		             if(type[0]=="eventlist"){
		             if(json_data.getString("event_type").equals(type[1])){
		             thelist.add(json_data.getString("event_name"));//here "Name" is the column name in database
		             //Log.d("filter", thelist.get(j));
		             }
		            	 
		             }
		             else{
		            	 thelist.add(json_data.getString("event_name"));
		            	 thelist.add(json_data.getString("event_type"));
		            	 thelist.add(json_data.getString("section"));
		            	 thelist.add(json_data.getString("row"));
		            	 thelist.add(json_data.getString("quantity"));
		            	 thelist.add(json_data.getString("price"));
		            	 thelist.add(json_data.getString("event_date"));
		            	 thelist.add(json_data.getString("facevalue"));
		            	 
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
    
    
    

    
}