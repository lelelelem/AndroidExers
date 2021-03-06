package com.example.diary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class JSONSerializer {
	
	private Context mContext;
	private String mFilename;
	String Tag = "JSONSERIALIZER";
	
	public JSONSerializer(Context c, String fname) {
		mContext =c;
		mFilename=fname;
	}
		
	public void saveDetails(ArrayList<ModelClass> modelClasses) throws JSONException, IOException{
		JSONArray jsonArray = new JSONArray();
		
		Log.i(Tag, "SIZE IS "+modelClasses.size());
		
		for(ModelClass mc:modelClasses){
			jsonArray.put(mc.toJsonObject());
			Log.i(Tag, "PLACED IT");
		}
		
		Writer writer=null;
		
		OutputStream outputStream=mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
		writer = new OutputStreamWriter(outputStream);
		writer.write(jsonArray.toString());
		Log.i(Tag, "THIS WAS ADDED!"+jsonArray.toString());
		
		
		
		if(writer!=null){
			writer.close();
			Log.i(Tag, "Closing File");
		}
	}
	
	public ArrayList<ModelClass> loadDetails() throws Exception{
		
		ArrayList<ModelClass> modelClasses = new ArrayList<ModelClass>();
		BufferedReader reader =null;

	
		InputStream inputStream = mContext.openFileInput(mFilename);
		reader =new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuilder json_string=new StringBuilder();
		String line=null;
		
		while((line=reader.readLine())!=null){
			Log.i(Tag, "Loading Files in Serializer "+line);
			json_string.append(line);
		}
		
		JSONArray jsonArray=(JSONArray)new JSONTokener(json_string.toString()).nextValue();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			modelClasses.add(new ModelClass(jsonArray.getJSONObject(i)));
			Log.i(Tag, "PLACING FILES INTO MODEL CLASSES "+jsonArray.getJSONObject(i).toString());
		}
		
		if(reader!=null)
			reader.close();
		
		
		return modelClasses;
	}
	
	//not yet implemented placed for future use
	public ArrayList<ModelClass> deleteDetails(int position) throws Exception{
		
		ArrayList<ModelClass> modelClasses = new ArrayList<ModelClass>();
		BufferedReader reader =null;

	
		InputStream inputStream = mContext.openFileInput(mFilename);
		reader =new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuilder json_string=new StringBuilder();
		String line=null;
		
		while((line=reader.readLine())!=null){
			Log.i(Tag, "Loading Files in Serializer "+line);
			json_string.append(line);
		}
		
		JSONArray jsonArray=(JSONArray)new JSONTokener(json_string.toString()).nextValue();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			if(i==position)
					continue;
			modelClasses.add(new ModelClass(jsonArray.getJSONObject(i)));
			Log.i(Tag, "PLACING FILES INTO MODEL CLASSES "+jsonArray.getJSONObject(i).toString());
		}
		
		if(reader!=null)
			reader.close();
		
		
		return modelClasses;
	}

}
