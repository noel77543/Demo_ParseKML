package tw.noel.sung.com.demo_parsekml;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import tw.noel.sung.com.demo_parsekml.util.KMLParser;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private KMLParser kmlParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //---------------

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        kmlParser = new KMLParser(this);
        drawKMLRoute();
    }
    //------------------

    /***
     *  取出點位置 繪製路線
     */
    private void drawKMLRoute() {
        ArrayList<LatLng> points = kmlParser.getPoints("test.kml");
        PolylineOptions polylineOptions = new PolylineOptions();

        for (int i = 0; i < points.size(); i++) {
            polylineOptions.add(points.get(i));
        }
        mMap.addPolyline(polylineOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get(0), 16));
    }
}
