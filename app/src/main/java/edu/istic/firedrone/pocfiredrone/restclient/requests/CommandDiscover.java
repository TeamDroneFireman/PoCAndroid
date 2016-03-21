package edu.istic.firedrone.pocfiredrone.restclient.requests;

/**
 * Created by maxime on 15/03/2016.
 */
public class CommandDiscover extends Command{
    private final String commandkey = "DISCOVER";

    public CommandDiscover() {
        this.key = commandkey;
    }
}
