package edu.istic.firedrone.pocfiredrone.push;

import android.os.Bundle;
import android.util.Log;

import edu.istic.firedrone.pocfiredrone.domain.Drone;
import edu.istic.firedrone.pocfiredrone.exception.PushHandlingException;
import edu.istic.firedrone.pocfiredrone.push.handler.DroneTopicHandler;
import edu.istic.firedrone.pocfiredrone.push.handler.PushTopicHandler;

/**
 * Created by mlebastard on 15/03/16.
 *
 * Receives and treats push messages
 */
public class PushRouter {

    private static final String TAG = "PUSH_RECEIVER";

    public static void onMessageReceived(String from, Bundle data) throws PushHandlingException {

        // Log data
        Log.d(TAG, "From: " + from);

        // Initialize handler
        PushTopicHandler handler = null;

        // Route handling
        if (from.startsWith(DroneTopicHandler.MATCHING_TOPIC)) {
            handler = new DroneTopicHandler();
        } else {
            throw new PushHandlingException(from);
        }

        // Pass to handler
        handler.handle(from, data);
    }

}