package com.lemuel.exercise.contextmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		android.support.v4.app.FragmentManager fm= getSupportFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.container);
		
		fragment = new FragmentMenu();
		fm.beginTransaction().add(R.id.container, fragment).commit();
		
		
	}

	

}
