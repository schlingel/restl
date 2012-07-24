package at.fundev.restl.utils;

/**
 * <p>The request handler mapping maps a request to its error and response handler.</p>
 */
public class RequestHandlerMapping {
	private static final String PERSISTANCE_FORMAT="%d:%d:%d";
	
	/**
	 * The Request ID
	 */
	private long requestID;
	
	/**
	 * The ID of the error handler class.
	 */
	private long errorHandlerID;
	
	/**
	 * The ID of the response handler class.
	 */
	private long responseHandlerID;
	
	/**
	 * Initializeses the handler object for a given request ID.
	 */
	public RequestHandlerMapping(long requestID) {
		this.requestID = requestID;
		this.errorHandlerID = -1;
		this.responseHandlerID = -1;
	}

	public long getRequestID() {
		return requestID;
	}

	public void setRequestID(long requestID) {
		this.requestID = requestID;
	}

	public long getErrorHandlerID() {
		return errorHandlerID;
	}

	public void setErrorHandlerID(long errorHandlerID) {
		this.errorHandlerID = errorHandlerID;
	}

	public long getResponseHandlerID() {
		return responseHandlerID;
	}

	public void setResponseHandlerID(long responseHandlerID) {
		this.responseHandlerID = responseHandlerID;
	}
	
	/**
	 * <p>Returns a string representation of the object.</p>
	 * <p>It takes the request id, the response handler id and the error handler id and creates an CSV string. For example
	 * an object with the request ID <strong>12</strong>, the response handler id <strong>34</strong> and the error handler 
	 * id <strong>10</strong> would be representated as:</p>
	 * <pre>
	 *   12:34:10
	 * </pre>
	 */
	@Override
	public String toString() {
		return String.format(PERSISTANCE_FORMAT, requestID, responseHandlerID, errorHandlerID);
	}
	
	/**
	 * Parses an given string. If in the correct format ({@see toString} for Details) the corresponding
	 * object is returned, otherwise null.
	 * @param value
	 * @return
	 */
	public static RequestHandlerMapping parse(String value) {
		if(value != null) {
			String[] parts = value.split(":");
			
			if(parts.length == 3) {
				Long reqID = Converter.parseLong(parts[0]);
				Long respID = Converter.parseLong(parts[1]);
				Long errID = Converter.parseLong(parts[2]);
				
				if(reqID != null && respID != null && errID != null) {
					RequestHandlerMapping result = new RequestHandlerMapping(reqID);
					result.setErrorHandlerID(errID);
					result.setResponseHandlerID(respID);
					
					return result;
				}
			}
		}
		
		return null;
	}
}
