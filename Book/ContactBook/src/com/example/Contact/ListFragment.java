package com.example.Contact;

import java.util.ArrayList;

import com.example.diary.R;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends android.support.v4.app.ListFragment {

	private ArrayList<ModelClass> mModelClass;
	public static final String MODEL_CLASS="model";
	
	String Tag = "LISTFRAGMENT";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("Details..Details..and Details..");
		
		try {
			mModelClass = ModelSingleton.get(getActivity()).getDetails();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayAdapter<ModelClass> adapter = new ArrayAdapter<ModelClass>(getActivity(),android.R.layout.simple_list_item_1, mModelClass);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ModelClass modelClass = (ModelClass)(getListAdapter()).getItem(position);
		
		Intent intent=new Intent(getActivity(),ShowActivity.class);
		
		intent.putExtra(ShowFragment.MODEL_CLASS, modelClass.getUuid());
		Log.i(Tag, modelClass.getUuid().toString());
		
		startActivity(intent);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= super.onCreateView(inflater, container, savedInstanceState);
		
		ListView listView = (ListView)view.findViewById(android.R.id.list);
		
		registerForContextMenu(listView);
		
		return view;
		
	}

	
	private class ForListAdapter extends ArrayAdapter<ModelClass>{

		public ForListAdapter(ArrayList<ModelClass> modelClasses) {
			super(getActivity(), 0,modelClasses);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		
			if(convertView==null)
				convertView= getActivity().getLayoutInflater().inflate(R.layout.list_fragment_layout, null);
			
			//	ModelClass modelClass = getItem(position);
			
			
			
			return convertView;
			
		}
		
		
	}
		
}

