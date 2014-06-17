package com.example.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public abstract class SingleFragment extends FragmentActivity{

	public abstract Fragment newFragment();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.activity_main);
		android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.container);
	
		fragment = newFragment();
		
		fm.beginTransaction()
				.add(R.id.container,fragment)
				.commit();
	}
	
}
