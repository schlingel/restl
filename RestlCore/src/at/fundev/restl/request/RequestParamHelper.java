package at.fundev.restl.request;

/**
 * This class is used for creating a fluid way of setting parameter of one request.
 */
public class RequestParamHelper {
	/**
	 * The request object for which the param should be set.
	 */
	private Request host;
	
	/**
	 * The param name.
	 */
	private String paramName;
	
	/**
	 * Creates the param helper object.
	 */
	RequestParamHelper(Request host, String name) {
		this.host = host;
		this.paramName = name;
	}
	
	/**
	 * Sets the given double value as parameter. Converts it to String.
	 * @param val
	 * @return
	 */
	public Request setDouble(double val) {
		return setString(Double.toString(val));
	}
	
	/**
	 * Sets the given long value as parameter. Converts it to String.
	 * @param val
	 * @return
	 */
	
	public Request setLong(long val) {
		return setString(Long.toString(val));
	}
	
	/**
	 * Sets the given float value as parameter. Converts it to String.
	 * @param val
	 * @return
	 */
	public Request setFloat(float val) {
		return setString(Float.toString(val));
	}
	
	/**
	 * Sets the given int value as parameter. Converts it to string.
	 */
	public Request setInt(int val) {
		return setString(Integer.toString(val));
	}
	
	/**
	 * Adds the parameter to the request object and returns the request object.
	 * @param val
	 * @return
	 */
	public Request setString(String val) {
		if(val != null) {
			host.addParam(paramName, val);			
		}
		
		return host;
	}
}
