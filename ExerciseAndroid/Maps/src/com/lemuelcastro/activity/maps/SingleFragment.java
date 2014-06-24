package com.lemuelcastro.activity.maps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragment extends FragmentActivity {
	
	
	public abstract android.support.v4.app.Fragment SetUpFragment();
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		
		FragmentManager fm = getSupportFragmentManager();
		android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.container);
		
		fragment = SetUpFragment();
		
		fm.beginTransaction().add(R.id.container,fragment).commit();
		
	
	}
	
	


}
