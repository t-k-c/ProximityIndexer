package tk.tkctechnologies.pindexer.proximityindexer;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.zzp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import java.io.IOException;
import tk.tkctechnologies.pindexer.proximityindexer.MapDirections.RequestManager;
import tk.tkctechnologies.pindexer.proximityindexer.MapDirections.ResponseManager;
import tk.tkctechnologies.pindexer.proximityindexer.dbman.DataBaseResponse;

public class SitePresentation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_presentation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        TextView title = (TextView) findViewById(R.id.titleTv);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf"));


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
//        Avenue Mgr Vogt, Yaoundé
//        3.862595, 11.520384
        // Add a marker in Sydney and move the camera
        LatLng l1 = new LatLng(3.860866, 11.515944);
        LatLng l2 = new LatLng(3.862595, 11.520384);
//        Marker marker = new Marker();
        mMap.addMarker(new MarkerOptions().position(l1).title("Marker in museum"));
        mMap.addMarker(new MarkerOptions().position(l2).title("Marker in museum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l1,15f));

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = RequestManager.createUrlFromSrcDesLatLng(3.860866,11.515944,3.862595,11.520384);
                try {
                    final String resp  = DataBaseResponse.getPostResponseData(url,null,SitePresentation.this);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ResponseManager.drawOnMap(resp,mMap);
                            } catch (JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SitePresentation.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                } catch (IOException e ) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SitePresentation.this, "IO Exception", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();


    }
    public void backButtonClicked(View v){
        super.onBackPressed();
    }
}
