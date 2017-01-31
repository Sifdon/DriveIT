package hr.air.projekt.driveit;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Helper.CalculateKilometers;
import hr.air.projekt.driveit.Helper.CurrentActivity;

/**
 * Created by Stjepan on 30.1.2017..
 */

public class GpsTracking implements CalculateKilometers {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;

    private float distance;
    private Location lastLoc;
    private LocationManager locationManager;
    private LocationListener locationListener;


    private void trackDistance() {
        locationManager = (LocationManager) CurrentActivity.getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String pos = "Longtitude: " + location.getLongitude() + "Latitutde: " + location.getLatitude();
                Toast.makeText(CurrentActivity.getActivity(), Float.toString(distance), Toast.LENGTH_LONG).show();

                if (lastLoc == null) {
                    lastLoc = location;
                    distance = 0;


                } else if (lastLoc.distanceTo(location) > 10) {
                    lastLoc = location;
                    distance += lastLoc.distanceTo(location);
                }


            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        if (ActivityCompat.checkSelfPermission(CurrentActivity.getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(CurrentActivity.getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
    }

    public void stopTrackingDistance() {

        if (ActivityCompat.checkSelfPermission(CurrentActivity.getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CurrentActivity.getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void setCrossedDistance(TravelOrder travelOrder) {
        travelOrder.setCrossedKm((int)distance/1000);
    }
}
