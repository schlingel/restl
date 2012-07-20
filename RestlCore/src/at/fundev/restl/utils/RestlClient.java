package at.fundev.restl.utils;

import android.content.Intent;
import android.os.Bundle;

/**
 * <p>A restl client object is used to store the IDs of all pending requests. Not stored are requests which were created but not send.</p>
 * <p>If this class is used the methods <strong>onPause</strong> and <strong>onResume</strong> from the activity
 * class must be overwritten and call the corresponding methods in the client object. This methods have no guarantee to be called so beware 
 * that this is thought as use for quick user induced activity change with the intent to return to the
 * the activity.</p>
 * <p>The object also handles registering a Broadcast Receiver for the Restl responses and provides an interface for registering handlers of the
 * request responses. The IDs of the requests and the IDs of the handler objects are stored as tuple within as preferences. The key is the given identifier</p>
 * <p>If you change the handlers of the RestlClient object and the client doesn't have the handler defined in the preferences, the entry is just deleted and ignored.</p>
 */
// TODO: Add interfaces for Error and Success handlers
// TODO: Add method for setting default handlers.
public class RestlClient {
	/**
	 * Identifier used for storing the open request IDs.
	 */
	private String identifier;
	
	/**
	 * <p>Initializes the RestlClient object with the given identifier. For persisting the open IDs, the 
	 * identifier is used to store the IDs as CSV in the preferences.</p>
	 * <p>If you change the context of your activity but stay at the same activity, e.g. when you're using
	 * a ViewPager, a GalleryWidget or similar for swiping through different request triggering views use
	 * {@see changeIdentifier} to seperate this views.</p>
	 * @param identifier Key used for storing the request IDs as CSV in the preferences
	 */
	public RestlClient(String identifier) {
		
	}
	
	/**
	 * <p>This method writes the current open request IDs to the preferences with the old identifier and sets the
	 * objects identifier to the new identifier.</p>
	 * @param identifier
	 */
	public void changeIdentifier(String identifier) {
		
	}
	
	/**
	 * Called before the activity view is destroyed. Saves the current open request IDs as CSV to the preferences.
	 * @param bundle
	 */
	public void onPause() {
		
	}
	
	/**
	 * Takes the current identifier and restores the open request IDs from the preferences.
	 */
	public void onResume() {
		
	}
	
	/**
	 * Sends the given request intent to the Restl HTTP/JSON service and registers the request ID as open request.
	 * @param request
	 */
	// TODO: Rewrite to fluid interface for adding addtional error and success handlers for the request.
	public void send(Intent request) {
		
	}
}
