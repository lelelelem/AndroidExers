package com.example.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class SingleFragmentActivity extends FragmentActivity {

	public abstract android.support.v4.app.Fragment getFragment();
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		android.support.v4.app.FragmentManager fm= getSupportFragmentManager();
		android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.container);
		
		fragment = getFragment();
		
		fm.beginTransaction().add(R.id.container,fragment).commit();
	}
	
}
