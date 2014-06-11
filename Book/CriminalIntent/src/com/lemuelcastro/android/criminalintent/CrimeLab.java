package com.lemuelcastro.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	private ArrayList<Crime> mCrimes;
	private static CrimeLab mCrimeLab;
	
	private Context mAppContext;
	
	private CrimeLab(Context AppContext){
		mAppContext = AppContext;
		mCrimes = new ArrayList<Crime>();
		
		for (int i = 0; i < 100; i++) {
			Crime c = new Crime();
			c.setTitle("Crime #" + i);
			c.setSolved(i % 2 == 0); // Every other one
			mCrimes.add(c);
		}

	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public static CrimeLab get(Context c) {
		if (mCrimeLab == null) {
			mCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return mCrimeLab;
		}


	public Crime getCrime(UUID id) {
		for (Crime c : mCrimes) {
			if (c.getId().equals(id))
				return c;
			}
		return null;
	}
	
	

}
