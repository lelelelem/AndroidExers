package com.lemuelcastro.exercises.volleylru;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListFrag extends android.support.v4.app.ListFragment{

	private ArrayList<ModelClass> mModelClasses;
	public static final String TAG="ListFrag";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActivity().setTitle("YAHOO NEWS");
		
		mModelClasses = ModelSingleton.returnAll();
		
		ForListAdapter adapter=new ForListAdapter(mModelClasses);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ModelClass modelClass=((ForListAdapter)getListAdapter()).getItem(position);
		Toast.makeText(getActivity(),modelClass.getTitle(),Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=super.onCreateView(inflater, container, savedInstanceState);
		
		ListView listView=(ListView)view.findViewById(android.R.id.list);
		
		registerForContextMenu(listView);
		
		return view;
		
	}
	
	
	public class ForListAdapter extends ArrayAdapter<ModelClass>{
		
		public ForListAdapter(ArrayList<ModelClass> modelClasses){
			super(getActivity(), 0,modelClasses);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
			if (convertView==null)
				convertView=getActivity().getLayoutInflater().inflate(R.layout.list_fragment, null);
			
			ModelClass c=getItem(position);
				
			TextView textView=(TextView)convertView.findViewById(R.id.title_textview);
			
			textView.setText(c.getTitle());
			
			
			
			return convertView;
		}
		
		
	}
	
}
