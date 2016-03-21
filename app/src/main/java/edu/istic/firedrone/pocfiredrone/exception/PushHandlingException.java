package edu.istic.firedrone.pocfiredrone.exception;

/**
 * Created by maxime on 21/03/2016.
 *
 * Exception thrown when an unexpected push happened
 */
public class PushHandlingException extends Exception {
    public PushHandlingException(String topic) {
        super("Unable to handle push topic \"" + topic + "\"");
    }
}
