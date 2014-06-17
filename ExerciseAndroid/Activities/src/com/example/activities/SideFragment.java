package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SideFragment extends Fragment {
	
	public static final String NAME_EXTRA = "NameFromMainActivity";
	private String mNameString;
	private TextView mTextView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mNameString = (String)getActivity().getIntent().getSerializableExtra(NAME_EXTRA);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.sidefragment, container,false);
		
		mTextView = (TextView)view.findViewById(R.id.resulting_textview);
		
		mTextView.setText("The Big Brown "+mNameString+" Jumped over the happy little Squid");
		
		
		
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
}
