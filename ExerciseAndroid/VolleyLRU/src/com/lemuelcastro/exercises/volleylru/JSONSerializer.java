package com.lemuelcastro.exercises.volleylru;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class JSONSerializer {
	private ArrayList<ModelClass> modelClasses;

	
	
	public ArrayList<ModelClass> method(Context c, JSONObject response)
			throws JSONException {

		modelClasses = new ArrayList<ModelClass>();
		JSONArray items = response.getJSONArray("results");

		for (int i = 0; i < items.length(); i++) {
			modelClasses.add(new ModelClass(items.getJSONObject(i)));
		}

		return modelClasses;

	}

}
