package edu.istic.firedrone.pocfiredrone;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements MapEventsReceiver {

    MapView map;
    int touchX, touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initializeMap();
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        Toast.makeText(MapActivity.this, "X " + touchX + "  Y " + touchY, Toast.LENGTH_SHORT).show();


        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        showAddPointPopup();

        //putTruck(p);
        //Toast.makeText(this, "Tap on (" + p.getLatitude() + "," + p.getLongitude() + ")", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void showAddPointPopup() {
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupWindow pw = new PopupWindow(
                inflater.inflate(R.layout.popup_window, null, false),
                400,
                400,
                true);
        // The code below assumes that the root container has an id called 'main'
        pw.showAtLocation(this.findViewById(R.id.map_layout), Gravity.CENTER, touchX, touchY);
    }

    protected void putTruck(GeoPoint p) {
        Marker truckMarker = new Marker(map);

        truckMarker.setPosition(p);
        truckMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        truckMarker.setIcon(ContextCompat.getDrawable(this, R.drawable.firefighter));
        truckMarker.setTitle("Toto");

        map.getOverlays().add(truckMarker);
        map.invalidate(); // We need that to make OSMDroid to reload the map

    }


    protected void initializeMap() {

        // Init OSM Map
        map = (MapView) findViewById(R.id.map); // Get view
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);    // Set source (OSM, GMaps, MapBox...)
        map.setTilesScaledToDpi(true);                  // Scale to DPI, for large screens like tablets

        map.setBuiltInZoomControls(true);               // Enable zoom buttons
        map.setMultiTouchControls(true);                // Enable pinch to zoom, rotate...

        IMapController mapController = map.getController(); // Create a map controller
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);// Create a point
        mapController.setCenter(startPoint);                // Put the map center on the point

        mapController.setZoom(18);                          // Set an initial amount of zoom, else we will see an "earth mosaic"

        // Overlay
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);

        map.invalidate(); // We need that to make OSMDroid to reload the map

    }
}
