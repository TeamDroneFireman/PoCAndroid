package edu.istic.firedrone.pocfiredrone.push.handler;

import android.os.Bundle;

import edu.istic.firedrone.pocfiredrone.CommandPattern.ICommand;

/**
 * Created by maxime on 18/03/2016.
 */
public class DroneTopicHandler implements PushTopicHandler {
    public String MATCHING_TOPIC = "/topic/drone";

    public static ICommand commandToDo;

    /**
     * @inheritDoc
     */
    @Override
    public void handle(String topic, Bundle data) {
        // HANDLE DRONE HERE
        /*switch (topic) {
            case "drone/refresh/" :*/
                if (commandToDo != null) {
                    commandToDo.execute();
                }
                //break;
        //}
    }
}
