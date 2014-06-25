package com.example.Contact;
import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


@SuppressWarnings("serial")
public class ModelClass implements Serializable {
	
	private final String TAG="MODELCLASS";
	
	private final String JSON_UID="id";
	private String JSON_ID_TEXT="_text";
	private String JSON_ID_IMG="_img";
	
	
	private String mName, mPicPath;
	
	private UUID mId;
	
	
	public UUID getUuid(){
		return mId;
	}
	
	
	public ModelClass() {
		mId = UUID.randomUUID();
	}
	
	public ModelClass(JSONObject jsonObject) throws JSONException{
		
		
		Log.i(TAG, "JSON LENGTH "+jsonObject.length());
		Log.i(TAG, "JSON VALUE "+jsonObject.toString());
		
		mId=UUID.fromString(jsonObject.getString(JSON_UID)); 
		mName = jsonObject.getString(JSON_ID_TEXT);
		mPicPath=jsonObject.getString(JSON_ID_IMG);
	}
	
	
	public JSONObject toJsonObject() throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
		
		
		jsonObject.put(JSON_UID, mId.toString());
		jsonObject.put(JSON_ID_TEXT, mName);
		jsonObject.put(JSON_ID_IMG, mPicPath);
		
		return jsonObject;
	}

	public String getPicPath() {
		return mPicPath;
	}

	public void setPicPath(String picPath) {
		mPicPath = picPath;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
		Log.i(TAG, mName+" "+mId);
	}
	
	
}
