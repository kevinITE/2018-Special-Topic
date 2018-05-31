package com.vuforia.samples.VuforiaSamples.app.MapsActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.app.ImageTargets.ImageTargets;
import com.vuforia.samples.VuforiaSamples.ui.ActivityList.ActivitySplashScreen;
import com.vuforia.samples.VuforiaSamples.ui.ActivityList.indexActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    private ArrayList<LatLng> listPoints;
    private ArrayList<String> inst;
    private ArrayList<Integer> dist;
    private LatLng cur = null, target;
    private LocationManager locMgr;
    private String bestProv;
    private float zoom = 7f;
    private Button btn_go;
    private boolean gotdirection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MapsActivity", "onCreate");
        super.onCreate(savedInstanceState);

        // Get LatLng
        Bundle bundle = getIntent().getParcelableExtra("bundle");
        target = bundle.getParcelable("location");

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, ImageTargets.class);
                intent.putStringArrayListExtra("inst", inst);
                intent.putIntegerArrayListExtra("dist",dist);
                startActivity(intent);
            }
        });
        listPoints = new ArrayList<>();

        inst = new ArrayList<>();
        dist = new ArrayList<>();
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(cur == null)
        {
            Toast.makeText(this,"NULL", Toast.LENGTH_SHORT);
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.5, 121), zoom));



        // Zoom control
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {

                // Reset marker when already 1
                if (listPoints.size() == 1) {
                    listPoints.clear();
                    mMap.clear();
                }
                // Save first point select
                listPoints.add(latLng);
                //target = latLng;
                // Create marker
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                // Set the marker's color to red
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);
                gotdirection = false;
                if(!gotdirection && listPoints.size() == 1)
                {
                    String url = getRequestUrl(cur, latLng);
                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(url);
                    gotdirection = true;
                }

                // if (listPoints.size() == 2) {
                //Create the URL to get request from first marker to second marker
                  /*      String url = getRequestUrl(cur, listPoints.get(0));
                        TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                        taskRequestDirections.execute(url);
*/
                //    }
            }
        });

        // Construct marked item
      /*  if(!target.equals(new LatLng(0,0)))
        {
            MarkerOptions markerOpt = new MarkerOptions();
            markerOpt.position(target);
            markerOpt.title("Name");
            markerOpt.snippet("Description");
            markerOpt.draggable(false);
            markerOpt.visible(true);
            markerOpt.anchor(0.5f, 0.5f);
            mMap.addMarker(markerOpt);
        } else {

        }
*/
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                String url = getRequestUrl(cur, target);
                TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                taskRequestDirections.execute(url);
            }

        }, 1000);*/
        if(!target.equals(new LatLng(0,0)))
        {
            listPoints.add(target);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(target);
            mMap.addMarker(markerOptions);
        }
    }

    private String getRequestUrl(LatLng origin, LatLng dest) {
        //Value of origin
        String str_org = "origin=" + origin.latitude +","+origin.longitude;
        //Value of destination
        String str_dest = "destination=" + dest.latitude+","+dest.longitude;
        //Mode for transport
        String mode = "mode=walking";
        //Language from server side
        String language = "language=zh-TW";
        // Unit in metric or imperial
        String units = "units=metric";
        // Region preference
        String region = "region=tw";
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + mode + "&" + language + "&" + units + "&" + region + "&" + sensor;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }



    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
                Log.d("TaskParser", "routes is done");
                inst = directionsParser.getInstruction();
                dist = directionsParser.getDistance();

                // Intend to ImageTargets and bring direction instructions
                Log.d("Map Activity","Instruction size: " + inst.size());
            /*    Intent intent = new Intent(MapsActivity.this, ImageTargets.class);
                intent.putStringArrayListExtra("inst", inst);
                startActivity(intent);*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mMap.addPolyline(polylineOptions);
            } else {
                gotdirection = false;
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Get coordinate of the map: latitude, longitude
        Double lat = location.getLatitude(), lng = location.getLongitude();
        String x = "緯=" + Double.toString(lat);
        String y = "經=" + Double.toString(lng);
        cur = new LatLng(lat, lng);
        zoom = 15f; // Set zoom ratio: 1(Earth)~21(Street view)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur, zoom));
        Log.d("onLocationChanged", "Lat: " + x + ", Lng: " + y);
        //Toast.makeText(this, x + "\n" + y, Toast.LENGTH_SHORT).show();

        if(!gotdirection && listPoints.size() == 1)
        {
            String url = getRequestUrl(cur, target);
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);
            gotdirection = true;
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        locMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true); // get best locate way

        // if GPS or network locate is open then update location
        if(locMgr.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
           locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        } else {
            Toast.makeText(this, "請開啟定位服務", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        locMgr.removeUpdates(this);
    }
}
