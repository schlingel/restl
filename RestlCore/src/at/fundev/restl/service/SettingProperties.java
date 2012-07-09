package at.fundev.restl.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import at.fundev.restl.utils.Converter;

/**
 * Handles the properties loading for the service.
 */
public class SettingProperties extends Properties {
	private static final String TAG = SettingProperties.class.getName();
	
	private static final long serialVersionUID = 6856828796766704061L;
	
	/**
	 * The name of the properties file which is used for configurations.
	 */
	public static final String PROPERTIES_NAME = "restl.config.properties";
	
	/**
	 * Contains the name of the key for setting the maximal allowed worker threads.
	 */
	public static final String MAX_WORKER_THREADS_KEY = "worker.threads.max";

	/**
	 * The default value for the maximum of the concurrent running worker threads.
	 */
	private static final int MAX_WORKER_THREADS = 5;
	
	private int maxWorkerThreads;
	
	/**
	 * Private constructor to guarantee that only valid values are inside a properties object.
	 */
	private SettingProperties() {
		super();
		
		this.maxWorkerThreads = MAX_WORKER_THREADS;
	}
	
	/**
	 * Loads the properties from the given input stream and if the properties file didn't
	 * contained the needed files, resets to the default values.
	 */
	@Override
	public synchronized void load(InputStream in) throws IOException {
		super.load(in);
		
		maxWorkerThreads = getProperty(MAX_WORKER_THREADS_KEY) == null ? MAX_WORKER_THREADS : Converter.parseInt(getProperty(MAX_WORKER_THREADS_KEY));
	}
	
	/**
	 * Gets the count of the maximal running worker threads.
	 * @return
	 */
	public int getMaxWorkerThreads() {
		return maxWorkerThreads;
	}
	
	/**
	 * <p>Creates a new properties object. If there's a properties file the values are taken, if there isn't
	 * one default values are taken.</p>
	 * <h2>Default Values</h2>
	 * <table border="1">
	 * <tr>
	 * <td><center><b>Name</b></center></td>
	 * <td><center><b>Value<b></center></td>
	 * </tr>
	 * <tr>
	 * <td>Maximal concurrent running worker threads.</td>
	 * <td>5</td>
	 * </tr>
	 * </table>
	 * @param context
	 * @return
	 */
	public static SettingProperties from(Context context) {
		SettingProperties props = new SettingProperties();
		Resources res = context.getResources();
		AssetManager assetManager = res.getAssets();
		
		try {
			InputStream input = assetManager.open(PROPERTIES_NAME);
			props.load(input);
			Log.i(TAG, "Loaded configuration from " + PROPERTIES_NAME);
		} catch(IOException e) {
			Log.e(TAG, "Couldn't load configuration file");
		}
		
		return props;
	}
}
