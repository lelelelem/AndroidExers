package com.example.diary;
import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


@SuppressWarnings("serial")
public class ModelClass implements Serializable {
	
	private final String TAG="arf";
	
	private final String JSON_UID="id";
	private String JSON_ID_TEXT="_text";
	private String JSON_ID_IMG="_img";
	
	private String JSON_FINAL_ID;
	
	private int seqID = 0;
	private UUID mId;
	private LinkedList mLinkedList;
	
	public LinkedList mLinkedList2(){
		return mLinkedList;
	}
	
	public ModelClass(LinkedList mLinkedList) {
		mId = UUID.randomUUID();
		this.mLinkedList = mLinkedList;
	}
	
	public ModelClass(JSONObject jsonObject) throws JSONException{
		mLinkedList = new LinkedList();
		Log.i(TAG, "Welp "+jsonObject.length());
		Log.i(TAG, "Welp "+jsonObject.toString());
		mLinkedList.add(jsonObject.getString(JSON_UID), 0); 
		
		for (int i = 0; i < jsonObject.length(); i++) {
			if(jsonObject.getString(Integer.toString(seqID)+JSON_ID_TEXT)!=null){				
				mLinkedList.add(jsonObject.getString(Integer.toString(seqID)+JSON_ID_TEXT), 0);
			}
			else{
				mLinkedList.add(jsonObject.getString(Integer.toString(seqID)+JSON_ID_IMG), 1);
			}
		
			seqID++;
		}
	}
	
	
	public UUID getId() {
		return mId;
	}
	
	public void setId(UUID id) {
		mId = id;
	}

	
	public JSONObject toJsonObject() throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(JSON_UID, mId.toString());
		
		
		for(DataNode dataNode = mLinkedList.getInfo();dataNode!=null;dataNode=mLinkedList.getInfo()){
			Log.i(TAG, "doing it");
			JSON_FINAL_ID = dataNode.type==0 ? Integer.toString(seqID++)+JSON_ID_TEXT:Integer.toString(seqID++)+JSON_ID_IMG;
			jsonObject.put(JSON_FINAL_ID, dataNode.detail);
			Log.i(TAG, "placed "+JSON_FINAL_ID);
		}
		
		return jsonObject;
	}
	
	
}
