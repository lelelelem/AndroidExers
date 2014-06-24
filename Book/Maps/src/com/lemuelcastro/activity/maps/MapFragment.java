package com.lemuelcastro.activity.maps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends SupportMapFragment implements LocationListener{

	public static final String POSITION = "Position";
	
	private GoogleMap mGoogleMap;
	private UpdateLocation updater;
	private static final String TAG="MAP";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		
		if (status!=ConnectionResult.SUCCESS) {
			Toast.makeText(getActivity(), R.string.not_available, Toast.LENGTH_SHORT).show();
		}
		
		updater=new UpdateLocation();
		
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mGoogleMap = getMap();
		mGoogleMap.setMyLocationEnabled(true);
		
		LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		
		String provider = locationManager.getBestProvider(criteria, true);
		
		Log.i(TAG,"provider "+provider);
		
		Location location = mGoogleMap.getMyLocation();
		
        if (location==null)
        	Log.i(TAG,"Null");
		
		return view;
	}


	@Override
	public void onLocationChanged(Location location) {
		updater.upLoc(location, mGoogleMap);
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		//not used
	}


	@Override
	public void onProviderEnabled(String provider) {
		//not used
		
	}


	@Override
	public void onProviderDisabled(String provider) {
		//not used
		
	}

	
}
