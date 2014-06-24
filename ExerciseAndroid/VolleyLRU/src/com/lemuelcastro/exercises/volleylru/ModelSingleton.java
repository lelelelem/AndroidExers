package com.lemuelcastro.exercises.volleylru;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class ModelSingleton {
	
	private static ArrayList<ModelClass> mModelClasses;
	
	private static ModelSingleton mModelSingleton;
	
	private static final String TAG="ModelSingleTon";
	private Context mContext;
	
	
	private JSONSerializer mjsonSerializer;
	
	public ModelSingleton(Context c){
		mContext = c;
		mModelClasses = new ArrayList<ModelClass>();
		Log.i(TAG, "WOHOO first"+mModelClasses.size());
	}
	
	public void update(JSONObject response) throws JSONException{
		
		mjsonSerializer = new JSONSerializer();
		
		mModelClasses = mjsonSerializer.method(mContext, response);
		
		Log.i(TAG, "WOHOO "+mModelClasses.size());
	}
	
	public ModelClass get(String title){
		for(ModelClass mc:mModelClasses){
			if(mc.getTitle().equals(title)){
				return mc;
			}
		}
		return null;
	}
	
	public static ArrayList<ModelClass> returnAll(){
		Log.i(TAG, "WOHOO HEre!"+mModelClasses.size());
		return mModelClasses;
	}
	
	

}
