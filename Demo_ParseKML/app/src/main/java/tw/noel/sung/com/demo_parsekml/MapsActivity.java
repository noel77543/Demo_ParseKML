package tw.noel.sung.com.demo_parsekml;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

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
    private LatLng latLng;
    private double targetLat = 25.051662;
    private double targetLng = 121.549572;

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
        latLng = new LatLng(targetLat, targetLng);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        KMLParser kmlParser = new KMLParser(this);
        ArrayList<LatLng> points = kmlParser.getPoints("test.kml");
        PolylineOptions polylineOptions = new PolylineOptions();

        for (int i = 0; i < points.size(); i++) {
            Log.e(""+i,points.get(i).latitude+"");
            polylineOptions.add(points.get(i));
        }

        mMap.addPolyline(polylineOptions);
    }
    //------------------


}
