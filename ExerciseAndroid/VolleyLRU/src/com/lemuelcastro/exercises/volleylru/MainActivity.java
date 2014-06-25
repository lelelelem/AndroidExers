package com.lemuelcastro.exercises.volleylru;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	private RequestQueue mRequestQueue;
	private static final String TAG="Main Volley";
	private ModelSingleton mModelSingleton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mModelSingleton=new ModelSingleton(getApplication());
		
		mRequestQueue = Volley.newRequestQueue(this);

		JsonObjectRequest jr=new JsonObjectRequest(Request.Method.GET,"http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json",null,new Response.Listener<JSONObject>(){
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.i(TAG, response.toString());
				try {
					mModelSingleton.update(response);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}, null);
		
		
		
		mRequestQueue.add(jr);
	}

}
