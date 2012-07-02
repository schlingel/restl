package at.fundev.restl.request;

public enum HttpMethod {
	Post,
	Get,
	Delete,
	Put;
	
	public static final int POST = 0;
	
	public static final int GET = 1;
	
	public static final int DELETE = 2;
	
	public static final int PUT = 4;
	
	public static int asNumeric(HttpMethod type) {
		if(type == HttpMethod.Post) {
			return POST;
		} else if(type == HttpMethod.Delete) {
			return DELETE;
		} else if(type == HttpMethod.Put) {
			return PUT;
		} else {
			return GET;
		}
	}
}
