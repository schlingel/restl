package at.fundev.restl.utils;

import android.content.Context;
import android.content.Intent;
import at.fundev.restl.request.Request;

/**
 * Simple helper object to enable fluid typing.
 */
public class RequestSender {
	private RestlClient rc;
	
	private Context context;
	
	private Intent request;
	
	/**
	 * Initializes the request sender object with the given host context, the host restlclient and
	 * the request which should be send.
	 */
	public RequestSender(Context context, RestlClient rc, Intent request) {
		this.rc = rc;
		this.request = request;
		this.context = context;
	}
	
	/**
	 * Returns the mapping if it exists, if it doesn't exist a new one is created.
	 * @return
	 */
	private RequestHandlerMapping getMapping() {
		RequestHandlerMapping mapping = rc.getRequestHandlerMapping(requestID());
		if(mapping == null) {
			mapping = new RequestHandlerMapping(requestID());
		}
	
		return mapping;
	}
	
	/**
	 * Returns the request ID from the request intent.
	 * @return The request id or 0L.
	 */
	private long requestID() {
		return request.getExtras().getLong(Request.REQUEST_ID);
	}
	
	/**
	 * Adds the response handler for the current request to the RestlClient.
	 * @param responseHandler
	 * @return
	 */
	public RequestSender addResponseHandler(ResponseHandler responseHandler) {
		RequestHandlerMapping mapping = getMapping();
		
		if(responseHandler != null) {
			mapping.setResponseHandlerID(responseHandler.id());
			rc.addResponseHandler(responseHandler);
			rc.addRequestHandlerMapping(mapping);
		}
		
		return this;
	}
	
	/**
	 * Adds the error handler to the RestlClient.
	 * @param errorHandler
	 * @return
	 */
	public RequestSender addErrorHandler(ErrorHandler errorHandler) {
		RequestHandlerMapping mapping = getMapping();
		
		if(errorHandler != null) {
			mapping.setErrorHandlerID(errorHandler.id());
			rc.addErrorHandler(errorHandler);
			rc.addRequestHandlerMapping(mapping);
		}
		
		return this;
	}
	
	/**
	 * Sends the request intent to the Restl HTTP service.
	 * @return
	 */
	public RestlClient send() {
		context.startService(request);
		return rc;
	}
} 
