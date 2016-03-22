package edu.istic.firedrone.pocfiredrone.push.handler;

import android.os.Bundle;

/**
 * Created by maxime on 18/03/2016.
 *
 * Represents a push handler
 */
public interface IPushTopicHandler {

    /**
     * Handles a push
     * @param topic The push topic
     * @param data Data given in push
     */
    void handle(String topic, Bundle data);
}
