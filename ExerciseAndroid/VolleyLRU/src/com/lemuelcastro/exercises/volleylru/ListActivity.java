package com.lemuelcastro.exercises.volleylru;

import android.support.v4.app.Fragment;

public class ListActivity extends SingleFragment{

	@Override
	public Fragment setUpFrag() {
		return new ListFrag();
	}

}
