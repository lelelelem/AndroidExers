package com.example.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragment {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public Fragment newFragment() {		
		return new MainActivityFragment();
	}

	

}
