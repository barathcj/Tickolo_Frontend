package com.example.tickolo;
 
/**
 * This class displays content in listlayouts
 * @author Barath Jawahar
 *
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> values;
	private final String type;
	/**
	 * Constructor
	 * @param context
	 * @param values - the list of strings to be displayed
	 * @param type - the type of ticket to be displayed
	 */
	
	public MobileArrayAdapter(Context context, List<String> values, String type) {
		super(context, R.layout.page1, values);
		this.context = context;
		this.values = values;
		this.type=type;
	}
	
	/**
	 * sets the view for the custom listlayout
	 * images are chosen on type
	 * @param position - the position of the items in the list
	 * @param convertView - the view in which the list is
	 * @param parent - the parent viewgroup
	 */
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.page1_item, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		
		// Change icon based on name
		String s = values.get(position);
 
		System.out.println(s);
 
		if (type.equals("sports")) {
			imageView.setImageResource(R.drawable.sports);
		} else if (type.equals("movies")) {
			imageView.setImageResource(R.drawable.movies);
		}
		else if (type.equals("events")) {
			imageView.setImageResource(R.drawable.ic_launcher);
		} 
		else if (type.equals("music")) {
			imageView.setImageResource(R.drawable.music);
		} 
		textView.setText(values.get(position));
	
		return rowView;
		
		//return rowView;
	}
	
	
}
