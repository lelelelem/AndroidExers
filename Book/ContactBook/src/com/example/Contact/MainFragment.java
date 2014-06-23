package com.example.Contact;

import com.example.diary.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment{

	private Button mViewButton;
	private Button mAddButton;
	
	private View.OnClickListener mClickListener=new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			switch (v.getId()) {
			case R.id.view_button:
				intent = new Intent(getActivity(),ListActivity.class);				
				break;
			case R.id.add_button:
				intent = new Intent(getActivity(),AddActivity.class);				
				break;
			}
			startActivity(intent);
		}
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View view= inflater.inflate(R.layout.mainfragment, container,false);
		
		mViewButton = (Button)view.findViewById(R.id.view_button);
		mAddButton = (Button)view.findViewById(R.id.add_button);
		
		mAddButton.setOnClickListener(mClickListener);
		mViewButton.setOnClickListener(mClickListener); 
		
		
		return view;
		
	}
	
	
}
