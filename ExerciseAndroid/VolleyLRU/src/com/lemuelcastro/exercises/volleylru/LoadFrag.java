package com.lemuelcastro.exercises.volleylru;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lemuelcastro.exercises.volleylru.ListFrag.FetchTitle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class LoadFrag extends Fragment {
	
	private RequestQueue mRequestQueue;
	private ModelSingleton mModelSingleton;
	private static final String TAG="LOADFRAG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);

		mModelSingleton = new ModelSingleton(getActivity());

		mRequestQueue = Volley.newRequestQueue(getActivity());

		JsonObjectRequest jr = new JsonObjectRequest(
				Request.Method.GET,
				"http://pipes.yahooapis.com/pipes/pipe.run?_id=giWz8Vc33BG6rQEQo_NLYQ&_render=json",
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {

						try {
							mModelSingleton.update(response);
							Log.i(TAG,response.toString());
							Intent intent =new Intent(getActivity(),ListActivity.class);
							startActivity(intent);
						} catch (JSONException e) {

							e.printStackTrace();
						}
					}

				}, null);

		mRequestQueue.add(jr);
		
		
	}

}
