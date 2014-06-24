package com.lemuelcastro.exercises.volleylru;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

public class ListActivity extends SingleFragment{

	@Override
	public Fragment setUpFrag() {
		return new ListFragment();
	}

}
