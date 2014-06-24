package com.lemuelcastro.exercises.volleylru;

import org.json.JSONObject;

import android.util.Log;

public class ModelClass {
	
	private String mTitle;
	private static final String TAG="ModelClass";
	
	
	public ModelClass(JSONObject jsonObject){
		mTitle=jsonObject.optString("title");
		Log.i(TAG, "added "+mTitle);
	}
	
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
}
