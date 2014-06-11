package com.lemuelcastro.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
	
	private Crime mCrime;
	private EditText mCrimeTitle;
	
	private Button mDate;
	
	private CheckBox mSolved;
	
	private TextWatcher mTitleListener = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mCrime.setTitle(s.toString());
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {					
		}
		
		@Override
		public void afterTextChanged(Editable s) {		
		}
	};
	
	
	private OnCheckedChangeListener mIsSolveListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			mCrime.setSolved(isChecked);
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCrime = new Crime();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		
			View view = inflater.inflate(R.layout.crime_fragment, parent, false); 
			
			mCrimeTitle = (EditText)view.findViewById(R.id.crime_title);
			mDate = (Button)view.findViewById(R.id.crime_date);
			mSolved = (CheckBox)view.findViewById(R.id.crime_solved);
			
			mDate.setText(mCrime.getDate().toString());
			mDate.setEnabled(false);
			
			
			
			mCrimeTitle.addTextChangedListener(mTitleListener);
			
			return view;
	}
	
}
