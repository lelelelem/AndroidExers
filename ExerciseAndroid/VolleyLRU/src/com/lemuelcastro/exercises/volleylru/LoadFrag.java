package com.lemuelcastro.exercises.volleylru;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class LoadFrag extends Fragment {

	private RequestQueue mRequestQueue;
	private ModelSingleton mModelSingleton;
	private static final String TAG = "LOADFRAG";

	private Intent intent;
	
	private CharSequence mCharSequence;
	private EditText mEditText;

	private Button mSearch;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.search_button:
			
				
				intent.putExtra(ListFrag.BAND_NAME, "Results for: "+mCharSequence.toString());
				
				//removes spaces in search input for example the input is fall out boy
				//the final resulting url will be "http://itunes.apple.com/search?term=falloutboy"
				new ForLoader(getActivity()).execute(mCharSequence.toString().replaceAll(
						" ", ""));

				break;
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 intent = new Intent(getActivity(),
					ListActivity.class);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.title_fragment, container, false);

		mEditText = (EditText) view.findViewById(R.id.artist_edittext);

		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mCharSequence = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//not used
			}

			@Override
			public void afterTextChanged(Editable s) {
				//not used
			}
		});

		mSearch = (Button) view.findViewById(R.id.search_button);
		mSearch.setOnClickListener(mClickListener);

		return view;
	}

	public class ForLoader extends AsyncTask<String, Void, Void> {

		private ProgressDialog mProgress;
		private static final String URL= "http://itunes.apple.com/search?term="; 
		
		public ForLoader(Context c) {
			mProgress = new ProgressDialog(c);
			//might not be seen since the loading is actually fast
			mProgress.setTitle("Please Wait");
		}

		
		@Override
		protected Void doInBackground(String... params) {

			mModelSingleton = new ModelSingleton(getActivity());

			mRequestQueue = Volley.newRequestQueue(getActivity());
			Log.i(TAG, "YOHO" + params[0]);
			
			//Volley request are not run in the main thread per se 
			//so should I remove it inside the AsyncTask?
			JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,
					URL + params[0], null,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {

							try {
								mModelSingleton.update(response);
								Log.i(TAG, response.toString());
								
								
								
								startActivity(intent);
								

							} catch (JSONException e) {

								e.printStackTrace();
							}
						}

					}, null);

			mRequestQueue.add(jr);
			return null;
			
		}

		@Override
		protected void onPostExecute(Void result) {
			//not used
		}

	}

}
