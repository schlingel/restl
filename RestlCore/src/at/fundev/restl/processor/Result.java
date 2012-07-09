package at.fundev.restl.processor;

import android.os.Bundle;
import at.fundev.restl.administration.Status;

/**
 * Inner Type which is used to return a tripple of values.
 */
public class Result {
	/**
	 * The status of the call which should be either Error or Finished.
	 */
	private Status status;
	
	/**
	 * The path of the serialized result which may be written to disk. Can
	 * be null.
	 */
	private String path;
	
	/**
	 * Contains the content which should be broadcasted after finishing.
	 */
	private Bundle content;
	
	/**
	 * Sets the status.
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Gets the status.
	 * @return
	 */
	public Status getStatus() { return status; }
	
	/**
	 * Sets the path of the serialized result.
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Returns the path of the serialized content on disk.
	 * @return
	 */
	public String getPath() { return path; }
	
	/**
	 * Sets the content for broadcasting.
	 * @param content
	 */
	public void setContent(Bundle content) {
		this.content = content;
	}
	
	/**
	 * Returns the content for broadcast.
	 * @return
	 */
	public Bundle getContent() { return content; }
}