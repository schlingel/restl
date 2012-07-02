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
	
	/**
	 * Returns a integer representation from the given HttpMethod type. It if it's null, it's defaulted to GET.
	 */
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
	
	/**
	 * Creates an enumeration representation from a given integer value. if the integer value does not match a HttpMethod representation it's defaulted to GET.
	 * @param type
	 * @return
	 */
	public static HttpMethod fromNumeric(int type) {
		if(type == 0) {
			return HttpMethod.Post;
		} else if(type == 2) {
			return HttpMethod.Delete;
		} else if(type == 4) {
			return HttpMethod.Put;
		} else {
			return HttpMethod.Get;
		}
	}
}
