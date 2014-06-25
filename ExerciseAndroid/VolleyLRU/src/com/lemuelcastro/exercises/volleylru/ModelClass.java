package com.lemuelcastro.exercises.volleylru;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import android.util.Log;

public class ModelClass {
	
	private String mTitle;
	private static final String TAG="ModelClass";
	private JSONArray mArray;
	
	public ModelClass(JSONObject jsonObject){
		mTitle=jsonObject.optString("title");
		
		
		try {
			
		} catch (JSONException e) {
			Log.i(TAG, "ITEM IS "+jsonObject.toString());
		}
		
		
		Log.i(TAG, "added "+mTitle);
	}
	
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
}
