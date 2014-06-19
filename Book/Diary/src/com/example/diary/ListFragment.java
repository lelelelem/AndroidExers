package com.example.diary;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends android.support.v4.app.ListFragment {

	private ArrayList<ModelClass> mModelClass;
	
	public static final String MODEL_CLASS="model";
	
	String Tag = "tq2";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("Details..Details..and Details..");
		
		try {
			mModelClass = ModelSingleton.get(getActivity()).getDetails();
			Log.i(Tag, Integer.toString(mModelClass.size()));
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
		
		intent.putExtra(ShowFragment.MODEL_CLASS, modelClass);
		startActivity(intent);
		
		
	}
	
	
	
	
}
