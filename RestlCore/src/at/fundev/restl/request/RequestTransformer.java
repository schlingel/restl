package at.fundev.restl.request;

/**
 * This interface is for so called request transformations. These transformations are
 * applied when the request is created. Such transformations
 * are useful for generating signatures and other post created requests.
 */
public interface RequestTransformer {
	/**
	 * Called when the request is created. 
	 */
	public Request transform(Request target);
}
