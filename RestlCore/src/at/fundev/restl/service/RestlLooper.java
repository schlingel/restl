package at.fundev.restl.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Handler.Callback;

/**
 * The looping thread for the http service.
 */
public class RestlLooper extends Thread {
	/**
	 * The callback which should be executed when a message is executed.
	 */
	private Callback target;
	
	/**
	 * The message handler object. In this implementation this must be the service!
	 */
	private Handler handler;
	
	/**
	 * Initializes the looper thread.
	 * @param target
	 */
	public RestlLooper(Callback target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		Looper.prepare();
		handler = new Handler(target);
		Looper.loop();
	}
	
	/**
	 * Exits the thread.
	 */
	public void quit() {
		handler.getLooper().quit();
	}
	
	/**
	 * Gets the handler.
	 * @return
	 */
	public Handler getHandler() {
		return handler;
	}
}
