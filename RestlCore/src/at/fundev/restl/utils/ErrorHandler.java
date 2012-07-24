package at.fundev.restl.utils;

import android.os.Bundle;

/**
 * <p>The interface for error handlers. This objects are called when the RestlClient
 * receives an error as status. Than the RestlClient looks in its table which error handler
 * has to be called. For that every ErrorHandler should return an unique ID.</p>
 * <p>If two handlers have the same ID the handler which were added first is called.</p>
 *
 */
public interface ErrorHandler {
	/**
	 * Returns the ID of the current object. Instances of the same class should return the same ID.
	 * @return
	 */
	public long id();
	
	/**
	 * The result of the data.
	 * @param data
	 */
	public void handle(Bundle data);
}
