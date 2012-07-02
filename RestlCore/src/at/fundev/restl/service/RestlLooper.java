package at.fundev.restl.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Handler.Callback;

/**
 * The looping thread for the http service.
 */
public class RestlLooper extends Thread {
	private Callback target;
	private Handler handler;
	
	public RestlLooper(Callback target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		Looper.prepare();
		handler = new Handler(target);
		Looper.loop();
	}
	
	public void quit() {
		handler.getLooper().quit();
	}
}
