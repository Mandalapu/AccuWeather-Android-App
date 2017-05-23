package com.example.manohar.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import com.hamweather.aeris.communication.AerisEngine;
import com.hamweather.aeris.communication.loaders.ObservationsTask;
import com.hamweather.aeris.communication.loaders.ObservationsTaskCallback;
import com.hamweather.aeris.communication.parameter.PlaceParameter;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.MapViewFragment;
import com.hamweather.aeris.maps.interfaces.OnAerisMapLongClickListener;
import com.hamweather.aeris.model.AerisError;
import com.hamweather.aeris.response.ObservationResponse;
import com.hamweather.aeris.tiles.AerisTile;
/**
 * Created by Manohar on 10-12-2015.
 */
public class AerisFragment extends MapViewFragment implements
        OnAerisMapLongClickListener {
    public AerisFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AerisEngine.initWithKeys(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_secret_key), " com.example.manohar.weatherapp");
        View view =  inflater.inflate(R.layout.fragment_aeris, container, false);
        mapView = (AerisMapView)view.findViewById(R.id.aerisfragment_map);
        mapView.init(savedInstanceState, AerisMapView.AerisMapType.GOOGLE);
        mapView.addLayer(AerisTile.RADSAT);
        Bundle bundle = getArguments();
       // TextView txtfiled = (TextView) findViewById(R.id.maptxtview);

        //Log.d("city", bundle.getString("city"));

        PlaceParameter place = new PlaceParameter(bundle.getString("city")+","+bundle.getString("state"));
        ObservationsTask task = new ObservationsTask(getActivity(),
                new ObservationsTaskCallback() {

                    @Override
                    public void onObservationsFailed(AerisError error) {
                        // handle fail here
                        Log.e("Failed", error.code);

                    }
                    @Override
                    public void onObservationsLoaded(List responses) {
                        ObservationResponse response = (ObservationResponse)responses.get(0);
                        Number tempF = response.getObservation().tempF;
                        mapView.moveToLocation(new LatLng(response.getLocation().lat, response.getLocation().lon), 9);
                        Log.i("Failed", String.valueOf(tempF));
                    }
                });
        task.requestClosest(place);
        return view;
    }
    @Override
    public void onMapLongClick(double lat, double longitude) {
        // code to handle map long press. i.e. Fetch current conditions?
    }
}
