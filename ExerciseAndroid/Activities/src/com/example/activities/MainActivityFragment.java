package com.example.activities;

import android.content.Intent;
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

public class MainActivityFragment extends Fragment {

	private String TAG = "log";
	
	//creates a CharSequence which will be sent as an extra
	private CharSequence mCb;
	private EditText mEditText;
	private Button mButton;
	
	private View.OnClickListener mClickListener=new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(getActivity(),SideActivity.class);
			
			intent.putExtra(SideFragment.NAME_EXTRA, mCb.toString());
			//clears the EditTextButton
			mEditText.setText("");			
			startActivity(intent);
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view= inflater.inflate(R.layout.mainfragment, container,false);
		
		mEditText = (EditText)view.findViewById(R.id.name_textview);
		
		
		
		mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//places s on mCb
				mCb = s;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mButton = (Button)view.findViewById(R.id.submit_button);
		mButton.setOnClickListener(mClickListener);
		
		
		return view;
	}

	
	
	
}
