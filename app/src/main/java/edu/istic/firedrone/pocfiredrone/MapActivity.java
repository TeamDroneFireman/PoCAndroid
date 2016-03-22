package edu.istic.firedrone.pocfiredrone;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import edu.istic.firedrone.pocfiredrone.command.ICommand;
import edu.istic.firedrone.pocfiredrone.domain.Drone;
import edu.istic.firedrone.pocfiredrone.push.PushRouter;
import edu.istic.firedrone.pocfiredrone.push.handler.DroneTopicHandler;
import edu.istic.firedrone.pocfiredrone.restclient.RestManager;
import edu.istic.firedrone.pocfiredrone.restclient.RestService;
import edu.istic.firedrone.pocfiredrone.restclient.callbacks.RestCallback;
import edu.istic.firedrone.pocfiredrone.restclient.requests.command.Command;
import edu.istic.firedrone.pocfiredrone.restclient.requests.command.CommandDiscover;
import edu.istic.firedrone.pocfiredrone.restclient.responses.DroneGetResponse;
import retrofit2.Call;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements MapEventsReceiver {

    // UI Components
    MapView map;
    Button btSimPush;

    // Rest services
    RestService restService;

    // List des drones à afficher
    List<DroneGetResponse> droneGetResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Intit REST Service
        restService = RestManager.getRestService();

        // UI Elements
        btSimPush = (Button) findViewById(R.id.bt_sim_push);

        // Events
        btSimPush.setOnClickListener(new BtSimPushOnClickListener());

        // Load map
        initializeMap();

        // Load drones from webservice
        loadDronesAndDisplay();
    }

    /**
     * Triggers when the map have a single tap on it
     * @param p The point tapped
     * @return false
     */
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        sendDroneToPosition(p);
        return false;
    }

    /**
     * Sends a drone to the position
     * @param p
     */
    protected void sendDroneToPosition(GeoPoint p) {
        // Initialize request object (command)
        Command command = new CommandDiscover();

        // Create geopoint
        edu.istic.firedrone.pocfiredrone.domain.GeoPoint geoPoint = new edu.istic.firedrone.pocfiredrone.domain.GeoPoint();
        geoPoint.setLongitude(p.getLongitude());
        geoPoint.setLatitude(p.getLatitude());

        // Set this geopoint into command
        command.setGeoPoint(geoPoint);

        // Run the request
        restService.sendDrone(command).enqueue(new RestCallback<Void>() {
            @Override
            public void onError() {
                Toast.makeText(MapActivity.this, "An issue happened while sending position to server", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {
                Toast.makeText(MapActivity.this, "Position sent to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void loadDronesAndDisplay() {

        restService.getDrones().enqueue(new RestCallback<List<DroneGetResponse>>() {
            @Override
            public void onError() {
                Toast.makeText(MapActivity.this, "Error while loading drones", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess() {
                Toast.makeText(MapActivity.this, "Drones has been loaded", Toast.LENGTH_SHORT).show();

                // Display those drones
                List<Drone> dronesToDisplay = new ArrayList<Drone>();
                for (DroneGetResponse r : response.body()) {
                    dronesToDisplay.add(r.toDrone());
                }

                setDronesOnMap(dronesToDisplay);
            }
        });
    }

    protected void setDronesOnMap(List<Drone> drones) {
        for(Drone d : drones) {
            Marker droneMarker = new Marker(map);

            droneMarker.setPosition(new GeoPoint(d.getGeoPoint().getLatitude(), d.getGeoPoint().getLongitude()));
            droneMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            droneMarker.setIcon(ContextCompat.getDrawable(this, R.drawable.droneicon));
            droneMarker.setTitle(d.getId() + " : " + d.getStatus());

            map.getOverlays().add(droneMarker);
        }
        map.invalidate(); // We need that to make OSMDroid to reload the map
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    protected void initializeMap() {

        // Init OSM Map
        map = (MapView) findViewById(R.id.map); // Get view
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);    // Set source (OSM, GMaps, MapBox...)
        map.setTilesScaledToDpi(true);                  // Scale to DPI, for large screens like tablets

        map.setBuiltInZoomControls(true);               // Enable zoom buttons
        map.setMultiTouchControls(true);                // Enable pinch to zoom, rotate...

        IMapController mapController = map.getController(); // Create a map controller
        GeoPoint startPoint = new GeoPoint(48.115251, -1.638668);// Create a point
        mapController.setCenter(startPoint);                // Put the map center on the point

        mapController.setZoom(18);                          // Set an initial amount of zoom, else we will see an "earth mosaic"

        // Overlay
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        map.getOverlays().add(0, mapEventsOverlay);

        map.invalidate(); // We need that to make OSMDroid to reload the map

    }

    private class BtSimPushOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MapActivity.this, "Simulate push", Toast.LENGTH_SHORT).show();
            try {
                DroneTopicHandler.commandToDo = new CommandToDoImpl(MapActivity.this);
                PushRouter.onMessageReceived("/drone/refresh",null);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CommandToDoImpl implements ICommand {
        MapActivity mapActivity;

        private CommandToDoImpl(MapActivity mapActivity) {
            this.mapActivity = mapActivity;
        }

        @Override
        public void execute() {
            this.mapActivity.loadDronesAndDisplay();
        }
    }
}
