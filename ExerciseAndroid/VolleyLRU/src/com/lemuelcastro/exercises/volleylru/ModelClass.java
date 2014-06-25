package com.lemuelcastro.exercises.volleylru;

import org.json.JSONObject;

public class ModelClass {
	
	private String mTitle;
	private static final String TAG="ModelClass";
	
	public ModelClass(JSONObject jsonObject){
		mTitle=jsonObject.optString("title");
	}
	
	
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
}
