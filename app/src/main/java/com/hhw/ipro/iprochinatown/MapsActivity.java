package com.hhw.ipro.iprochinatown;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private static int numLocations = 11;

    private static int chineseAmericanMuseum = 0;
    private static int pingTomPark = 1;
    private static int chinatownLibrary = 2;
    private static int stThereseChurch = 3;
    private static int puiTakCententer = 4;
    private static int chinatownGate = 5;
    private static int nineDragonWall = 6;
    private static int chineseChristianCatholicChurch = 7;
    private static int chinatownSquare = 8;
    private static int kentCenter = 9;
    private static int kamLLiuBuilding = 10;


    LatLng[] latLngs = new LatLng[numLocations];
    String[] locationNames;
    int[] idIcons = new int[numLocations];
    Marker[] markers = new Marker[numLocations];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //LatLngBounds ChicagoBound = new LatLngBounds(
        //        new LatLng(40, -86), new LatLng(43, -88));
        //mMap.setLatLngBoundsForCameraTarget(ChicagoBound);

        LatLng chinatown = new LatLng(41.852726, -87.632948);
        mMap.setMinZoomPreference(15.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chinatown));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        initData();
        addLocations();

        mMap.setOnMarkerClickListener(this);
    }

    void initData(){
        LatLng[] tmpLatLng = {new LatLng(41.851330, -87.633529),//chineseAmericanMuseum
                            new LatLng(41.857297, -87.634385),//pingTomPark
                            new LatLng(41.853844, -87.632182),//chinatownLibrary
                            new LatLng(41.850311, -87.6328295),//stThereseChurch
                            new LatLng(41.852414, -87.632226),//puiTakCententer
                            new LatLng(41.8526050680,-87.6320278645),//chinatownGate
                            new LatLng(41.853026, -87.631420),//nineDragonWall
                            new LatLng(41.8505888, -87.6317184),//chineseChristianCatholicChurch
                            new LatLng(41.8542912566,-87.6335835457),//chinatownSquare
                            new LatLng(41.8490520000,-87.6320707000),//kenCenter//not sure
                            new LatLng(41.8544005000,-87.6355964000)//kamLLiuBuilding
        };

        int[] tmpIdIcon = {R.drawable.icon_chinese_american_museum,//chineseAmericanMuseum
                        R.drawable.icon_ping_tom_park,//pingTomPark
                        R.drawable.icon_library,//chinatownLibrary
                        R.drawable.icon_st_therese_church,//stThereseChurch
                        R.drawable.icon_pui_tak_center,//puiTakCententer
                        R.drawable.icon_gate,//chinatownGate
                        R.drawable.icon_nine_dragon_wall,//nineDragonWall
                        R.drawable.icon_chinese_christian_union_church,//chineseChristianCatholicChurch
                        R.drawable.icon_chinatown_square,//chinatownSquare       //need modify
                        R.drawable.icon_kent_center,//kenCenter             //need modify
                        R.drawable.icon_language//kamLLiuBuilding        //need modify
        };

        for(int i = 0; i < numLocations; i++){
            latLngs[i] = tmpLatLng[i];
            idIcons[i] = tmpIdIcon[i];
        }

        locationNames = getResources().getStringArray(R.array.locations_array);
    }

    void addLocations(){
        for(int i = 0; i < numLocations; i++){
            if(i == kamLLiuBuilding) {
                markers[i] = mMap.addMarker(new MarkerOptions()
                        .position(latLngs[i])
                        .title(locationNames[i])
                );
                markers[i].setTag(i);
                continue;
            }

            BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(idIcons[i]);
            Bitmap smallIcon = Bitmap.createScaledBitmap(bitmapdraw.getBitmap(), 100, 100, false);

            markers[i] = mMap.addMarker(new MarkerOptions()
                    .position(latLngs[i])
                    .title(locationNames[i])
                    .icon(BitmapDescriptorFactory.fromBitmap(smallIcon))
            );
            markers[i].setTag(i);
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer nameIndex = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (nameIndex != null) {
            Intent intent = new Intent(MapsActivity.this, LocationDisplayActivity.class);
            intent.putExtra("nameIndex", nameIndex);
            startActivity(intent);

            return true;
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
