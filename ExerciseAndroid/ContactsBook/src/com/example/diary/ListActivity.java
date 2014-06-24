package com.example.diary;

import android.support.v4.app.Fragment;

public class ListActivity extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		return new ListFragment();
	}

}
