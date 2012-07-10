package at.fundev.restl.processor;

import java.io.InputStream;

/**
 * <p>The producer interface is used in the {@see RestlHttpService} to process the result of the request. Classes implementing this interfaces are instanced using reflection so you <b>must</b> provide a constructor if there is a state which must be set before produce use a parameterless constructor.</p>
 * <p>Every producer should have at least one Consumer which is able to process the content of the returned result.</p>
 */
public interface Producer {
	/**
	 * Called when the network connection succeeded and there is an input stream. The Result is used to update the DB and send a broadcast.
	 * @param input
	 * @return
	 */
	public Result produce(InputStream input);
}
