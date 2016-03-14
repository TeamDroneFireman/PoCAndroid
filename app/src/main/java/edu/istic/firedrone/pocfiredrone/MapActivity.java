package edu.istic.firedrone.pocfiredrone;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
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
    public boolean longPressHelper(GeoPoint p) {
        PopupMenu popup = new PopupMenu(MapActivity.this, map, Gravity.CENTER);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MapActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popup.show();//showing popup menu

        //putTruck(p);
        //Toast.makeText(this, "Tap on (" + p.getLatitude() + "," + p.getLongitude() + ")", Toast.LENGTH_SHORT).show();
        return true;
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
