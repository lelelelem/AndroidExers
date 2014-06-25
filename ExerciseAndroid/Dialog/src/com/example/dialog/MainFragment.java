package com.example.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment {
	
	private Button mButtonBad;
	private Button mButtonGood;
	private TextView mTextView;
	
	private static final int BAD_GUY=0;
	private static final int GOOD_GUY=1;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	private View.OnClickListener mClickListener= new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			android.support.v4.app.FragmentManager fm= getActivity().getSupportFragmentManager();
			switch (v.getId()) {
			case R.id.evil_button:
				DialogClassBad dialog_class= new DialogClassBad();
				dialog_class.setTargetFragment(MainFragment.this, BAD_GUY);
				dialog_class.show(fm, "");
				break;
			case R.id.good_button:
				DialogClassGood dialog_class2= new DialogClassGood();
				dialog_class2.setTargetFragment(MainFragment.this, GOOD_GUY);
				dialog_class2.show(fm, "");
				break;
			}
			
		}
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mainfragment, container,false);
		
		mButtonBad = (Button)view.findViewById(R.id.evil_button);
		mButtonGood = (Button)view.findViewById(R.id.good_button);
		mTextView = (TextView)view.findViewById(R.id.text_view);
		
		
		mButtonBad.setOnClickListener(mClickListener);
		mButtonGood.setOnClickListener(mClickListener);
		
		
		
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode==Activity.RESULT_OK) {
			if (requestCode==BAD_GUY) {
				mTextView.setText("You Just Obliterated Mars Into Pieces!!!");
			}
			else if (requestCode==GOOD_GUY) {
				mTextView.setText("Congratulations You Just Cured Cancer!!");
			}
			
		}
		
		else {
			if (requestCode==BAD_GUY) {
				mTextView.setText("Nice Call Who Knows Mars can be our Home Someday!");
			}
			else if (requestCode==GOOD_GUY) {
				mTextView.setText("Good Call Cancer cant be cured anyway!");
			}
			
		}
	}

}
