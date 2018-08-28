package tk.tkctechnologies.pindexer.proximityindexer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NewSite extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site);
        TextView title = (TextView) findViewById(R.id.titleTv);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf"));
    }

    public void backButtonClicked(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm exit")
                .setMessage("Are sure you want to abort this process?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewSite.super.onBackPressed();
                    }
                }).create().show();
    }

    public void localize(View v) {
        if (verifyFields()) {
            View view = getLayoutInflater().inflate(R.layout.location_search_dialog, null);
            View loadingGif = view.findViewById(R.id.loading);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.location_search_site);
            animation.setRepeatCount(Animation.INFINITE);
            loadingGif.setAnimation(animation);
//            SupportMapFragment mapFragment = getSupportFragmentManager().fin;
//            SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().
//                    .findFragmentById(R.id.map);
//            mapFragment.getMapAsync(this);
            final LocationManager locationManager = (LocationManager) NewSite.this.getSystemService(LOCATION_SERVICE);
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Localizing...")
                    .setView(view)
                    .setNegativeButton("Abort", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create();
            alertDialog.show();
//                    locationManager.requestLocationUpdates(0,0,nu);
                    if (ActivityCompat.checkSelfPermission(NewSite.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NewSite.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider cballing
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Toast.makeText(NewSite.this, "Please activate your gps", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    } else {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                Toast.makeText(NewSite.this, "Located you ...", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {

                            }

                            @Override
                            public void onProviderEnabled(String s) {

                            }

                            @Override
                            public void onProviderDisabled(String s) {
                                alertDialog.dismiss();
                                Toast.makeText(NewSite.this, "Please activate your gps", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                            }
                        });
                    }
                }

         else {

            Snackbar.make(v, "Some fields are empty", Snackbar.LENGTH_LONG);
        }
    }

    public boolean verifyFields() {
        return true;
    }
 GoogleMap gMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        Toast.makeText(this, "map ready", Toast.LENGTH_SHORT).show();
    }
}
