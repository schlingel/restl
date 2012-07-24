package at.fundev.restl.utils;

import android.os.Bundle;

/**
 * This interface is used to implement handlers for the REST responses. Every
 * handler class should have an unique ID.
 * It's not guaranteed that the Bundle contains any more data than the ID and
 * the status. 
 */
public interface ResponseHandler {
	/**
	 * Returns the ID of the response handler class. This ID should be unique for
	 * every handler class.
	 * @return
	 */
	public long id();
	
	/**
	 * Handles the given data.
	 * @param data
	 */
	public void handle(Bundle data);
}
