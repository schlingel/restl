package at.fundev.restl.utils;

import android.content.Intent;
import android.os.Bundle;

/**
 * <p>A restl client object is used to store the IDs of all pending requests. Not stored are requests which were created but not send.</p>
 * <p>If this class is used the methods <strong>onSaveInstanceState</strong> and <strong>onRestoreInstanceState</strong> from the activity
 * class must be overwritten and call the corresponding methods in the client object. This methods have no guarantee to be called so beware 
 * that this is thought as use for quick user induced activity change with the intent to return to the
 * the activity.</p>
 */
public class RestlClient {
	/**
	 * Called before the activity view is destroyed.
	 * @param bundle
	 */
	public void onSaveInstanceState(Bundle bundle) {
		
	}
	
	public void onRestoreInstanceState() {
		
	}
	
	public void send(Intent request) {
		
	}
}
