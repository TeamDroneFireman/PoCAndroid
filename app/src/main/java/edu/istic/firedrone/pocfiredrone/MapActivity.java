package edu.istic.firedrone.pocfiredrone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.wasp.Callback;
import com.orhanobut.wasp.Response;
import com.orhanobut.wasp.WaspError;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

import edu.istic.firedrone.pocfiredrone.CommandPattern.ICommand;
import edu.istic.firedrone.pocfiredrone.push.PushRouter;
import edu.istic.firedrone.pocfiredrone.push.handler.DroneTopicHandler;
import edu.istic.firedrone.pocfiredrone.restclient.RestService;
import edu.istic.firedrone.pocfiredrone.restclient.RestServiceBuilder;
import edu.istic.firedrone.pocfiredrone.restclient.requests.Command;
import edu.istic.firedrone.pocfiredrone.restclient.requests.CommandDiscover;
import edu.istic.firedrone.pocfiredrone.restclient.responses.DroneGetResponse;

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

        restService = RestServiceBuilder.build(this);
        btSimPush = (Button) findViewById(R.id.bt_sim_push);

        btSimPush.setOnClickListener(new BtSimPushOnClickListener());

        droneGetResponseList = new ArrayList<>();

        initializeMap();
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        sendDroneToPosition(p);
        return false;
    }

    protected void sendDroneToPosition(GeoPoint p) {
        Command command = new CommandDiscover();
        edu.istic.firedrone.pocfiredrone.domain.GeoPoint geoPoint = new edu.istic.firedrone.pocfiredrone.domain.GeoPoint();
        geoPoint.setLng(p.getLongitude());
        geoPoint.setLat(p.getLatitude());
        command.setGeoPoint(geoPoint);
        restService.sendDrone(command, new Callback<String>() {

            @Override
            public void onSuccess(Response response, String text) {
                if (response.getStatusCode() == 200) {
                    Toast.makeText(MapActivity.this, "Position sent to server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(WaspError error) {
                Toast.makeText(MapActivity.this, "Error when sending position to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void getDrones() {
        //TODO à débug
        /*
        List<DroneGetResponse> droneGetResponses = restService.getDrones(new Callback<String>() {
                 @Override
                 public void onSuccess(Response response, String text) {
                     if (response.getStatusCode() == 200) {
                         Toast.makeText(MapActivity.this, "Drones receive from server", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onError(WaspError error) {
                     Toast.makeText(MapActivity.this, "Error when geting drones from server", Toast.LENGTH_SHORT).show();
                 }
             }
        );

        if (droneGetResponses != null) {
            this.droneGetResponseList = droneGetResponses;
        }*/
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
            this.mapActivity.getDrones();
        }
    }
}
