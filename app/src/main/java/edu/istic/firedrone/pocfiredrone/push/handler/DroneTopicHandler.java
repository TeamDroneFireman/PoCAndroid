package edu.istic.firedrone.pocfiredrone.push.handler;

import android.os.Bundle;

/**
 * Created by maxime on 18/03/2016.
 */
public class DroneTopicHandler implements PushTopicHandler {
    public static String MATCHING_TOPIC = "/topic/drone";

    /**
     * @inheritDoc
     */
    @Override
    public void handle(String topic, Bundle data) {
        // HANDLE DRONE HERE
    }
}
