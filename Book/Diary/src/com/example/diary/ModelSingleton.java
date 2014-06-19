package com.example.diary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class ModelSingleton {
	
	
	private static final String FILENAME = "crimes.json";

	private ArrayList<ModelClass> mModelClasses;
	
	private JSONSerializer mJsonSerializer;
	
	
	private static ModelSingleton sModelSingleton;
	private Context mContext;
	
	
	
	public ModelSingleton(Context context){
		mContext = context;
		mJsonSerializer = new JSONSerializer(mContext, FILENAME);
		
		try {
			String Tag = "tq1";
			Log.i(Tag, Integer.toString(mModelClasses.size()));
			mModelClasses = mJsonSerializer.loadDetails();
		} catch (Exception e) {
			String Tag = "tq1";
			Log.i(Tag, "aint doing it");
			mModelClasses = new ArrayList<ModelClass>();
			e.printStackTrace();
		}
	}
	
	
	
	public static ModelSingleton get(Context c) throws Exception{
		if (sModelSingleton==null)
			sModelSingleton = new ModelSingleton(c.getApplicationContext());
		
		return sModelSingleton;
	}
	
	public ArrayList<ModelClass> getDetails(){
		return mModelClasses;
	}
	
	public void addDetails(ModelClass c) {
		mModelClasses.add(c);
	}

	
	public ModelClass getDetail(UUID id){
		return null;
	}
	
	public boolean saveDetails() throws JSONException, IOException{
		mJsonSerializer.saveDetails(mModelClasses);
		return true;
	}

}
