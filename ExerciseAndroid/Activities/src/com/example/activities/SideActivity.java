package com.example.activities;

import android.support.v4.app.Fragment;


public class SideActivity extends SingleFragment {

	@Override
	public Fragment newFragment() {
		
		return new SideFragment();
	}
	
}
