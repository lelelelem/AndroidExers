package com.lemuelcastro.exercises.volleylru;

import org.json.JSONObject;

import android.util.Log;

public class ModelClass {
	
	private String mTitle;
	private String mImgUrl;
	
	private static final String TAG="ModelClass";
	
	public ModelClass(JSONObject jsonObject){
		//no need for setters since Volley will update the classes itself
		//in the Volley Request in LoadFrag
		mTitle=jsonObject.optString("trackCensoredName");
		mImgUrl=jsonObject.optString("artworkUrl100");
		Log.i(TAG,"Track is "+mTitle+"/n Image URL is "+mImgUrl);
	}
	
	
	public String getTitle() {
		return mTitle;
	}
	public String getImgUrl() {
		return mImgUrl;
	}

	
}
