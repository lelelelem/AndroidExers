package com.lemuelcastro.activity.maps;

import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class UpdateLocation {

	
	public void upLoc(Location location, GoogleMap mGoogleMap){
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        
	}
	
}
