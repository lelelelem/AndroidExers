package com.lemuelcastro.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;

public class CrimePagerActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private ArrayList<Crime> mCrimes;
	
	
	private ViewPager.OnPageChangeListener mChangeListener = new ViewPager.OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			Crime crime = mCrimes.get(arg0);
			if(crime.getTitle()!=null){
				setTitle(crime.getTitle());
			}
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mCrimes = CrimeLab.get(this).getCrimes();
		
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return mCrimes.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				Crime crime = mCrimes.get(arg0);
				return CrimeFragment.newInstance(crime.getId());
			}
		});
		
		mViewPager.setOnPageChangeListener(mChangeListener);
		
		UUID crimeId = (UUID)getIntent()
					.getSerializableExtra(CrimeFragment.EXTRA_CRIME_TITLE);
		for (int i = 0; i < mCrimes.size(); i++) {
			if(mCrimes.get(i).getId().equals(crimeId)){
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}
	
	
	
}
