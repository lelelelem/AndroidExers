package com.lemuelcastro.exercises.volleylru;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragment extends FragmentActivity{

	public abstract Fragment setUpFrag();
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		FragmentManager fm=getSupportFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.container);
		
		fragment=setUpFrag();
		
		fm.beginTransaction().add(R.id.container, fragment).commit();
		
	}
}
