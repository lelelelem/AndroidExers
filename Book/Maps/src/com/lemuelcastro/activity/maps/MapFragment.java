package com.lemuelcastro.activity.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends SupportMapFragment {

	public static final String POSITION = "Position";
	
	private GoogleMap mGoogleMap;
	
	
	public MapFragment newInstance(Long pos){
		Bundle args=new Bundle();
		MapFragment mapFragment = new MapFragment();
		args.putLong(POSITION, pos);
		
		mapFragment.setArguments(args);
		
		return mapFragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mGoogleMap = getMap();
		mGoogleMap.setMyLocationEnabled(true);
		
		return view;
		
		
		
	}
	
}
