package at.fundev.restl.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

/**
 * The main 
 */
public class Request {
	public static final String MIXED_CONTENT_NAME = "MIXED_CONTENT_NAME";
	
	public static final String MIXED_CONTENT_NAME_IDENTIFIER = "MIXED_CONTENT_NAME_IDENTIFIER";
	
	public static final String HTTP_TYPE = "HTTP_TYPE";
	/**
	 * The default address for a request. This can be set app wide.
	 */
	private static String defaultUrl;
	
	/**
	 * The target URL address of the Request instance.
	 */
	private URL destAddress;
	
	/**
	 * The http method for the request. if it's not set it's defaulted to GET.
	 */
	private HttpMethod method;
	
	/**
	 * Contains the parameters of the request in String representation.
	 */
	private HashMap<String, String> params;
	
	/**
	 * The list of the request transformers.
	 */
	private List<RequestTransformer> requestTransformers;
	
	/**
	 * Creates a request from the given URL object. Can create invalid objects if there
	 * are problems with the defaultURL.
	 */
	private Request(URL url) {
		this.params = new HashMap<String, String>();
		this.destAddress = url;
		this.requestTransformers = new ArrayList<RequestTransformer>();
	}
	
	/**
	 * Creates a request to the default URL object.
	 */
	public static Request get() {
		return Request.toUrl(defaultUrl);
	}
	
	/**
	 * Creates a request object from the given URL address.
	 */
	private Request(String url) throws MalformedURLException {
		this(new URL(url));
	}
	
	/**
	 * Creates a new request object from the given URL address. Can have null as
	 * set URL if there are problems with the given address.
	 */
	public static Request toUrl(String url) {
		Request request = null; 
		
		try {
			request = new Request(url);
		} catch(MalformedURLException e) {
			request = new Request((URL)null);
		}
		
		return request;
	}
	
	/**
	 * Creates a new request object to teh given URL.
	 * @param url
	 * @return
	 */
	public static Request toUrl(URL url) {
		return new Request(url);
	}
	
	/**
	 * Returns true if the intern URL object is not null, otherwise false.
	 * @return
	 */
	public boolean hasURL() {
		return destAddress != null;
	}
	
	/**
	 * Sets the parameter with the given value.
	 */
	public void addParam(String name, String value) {
		params.put(name, value);
	}
	
	/**
	 * Returns all parameter for the current request object.
	 * @return
	 */
	public HashMap<String, String> getParams() {
		return params;
	}
	
	/**
	 * Adds an request transformer to the transformers list. Request transformer are executed in the
	 * order they are added to the request object.
	 */
	public Request addRequestTransformer(RequestTransformer trans) {
		requestTransformers.add(trans);
		return this;
	}
	
	public Request setType(HttpMethod method) {
		this.method = method;
		return this;
	}
	
	/**
	 * Adds an parameter with the given name to the request object. Use the setter of the returned
	 * helper object to add the value.
	 */
	public RequestParamHelper addParam(String name) {
		return new RequestParamHelper(this, name);
	}
	
	/**
	 * Creates an Intent for sending the request to the http intent service. Attention: RequestTransformer could alter the data so executing this method twice could bare to different results!
	 * @return
	 */
	public Intent create() {
		Intent i = new Intent();
		Bundle extras = new Bundle();
		Request req = this;
		
		for(RequestTransformer trans : requestTransformers) {
			req = trans.transform(req);
		}
		
		for(String key : params.keySet()) {
			extras.putString(key, params.get(key));
		}
		
		extras.putInt(HTTP_TYPE, HttpMethod.asNumeric(method));
		i.putExtras(extras);
		
		return i;
	}
	
	/**
	 * Sets the default URL for the factory method get().
	 */
	public static void setDefaultUrl(String url) {
		Request.defaultUrl = url;
	}
}
