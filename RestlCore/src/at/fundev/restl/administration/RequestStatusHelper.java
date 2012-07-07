package at.fundev.restl.administration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.net.Uri;
import at.fundev.restl.utils.Converter;

/**
 * <p>This class is used to operate on the request status DB.</p>
 */
public class RequestStatusHelper {
	private static final String SQLITE_DATE_FORMAT = "YYYY-MM-DD HH:MM:SS.SSS";
	
	/**
	 * The content provider used to operate on the DB.
	 */
	private RequestStatusProvider provider;
	
	/**
	 * The date format used for creating the timestamp string.
	 */
	private SimpleDateFormat dateFormat;
	
	public RequestStatusHelper() {
		dateFormat = new SimpleDateFormat(SQLITE_DATE_FORMAT);
		provider = new RequestStatusProvider();
	}
	
	/**
	 * Creates a new request in the status DB with the Status created.
	 * @return
	 */
	public long createNewRequest() {
		Long id = null;
		ContentValues params = new ContentValues();
		params.put(StatusTable.COLUMN_STATUS, Status.CREATED);
		params.put(StatusTable.COLUMN_TIMESTAMP, timestamp());
		
		Uri resultUri = provider.insert(RequestStatusProvider.CONTENT_URI, params);
		
		if((id = Converter.parseLong(resultUri.getLastPathSegment())) == null) {
			id = new Long(-1);
		}
		
		return id;
	}
	
	/**
	 * Current timestamp in String format.
	 * @return
	 */
	private String timestamp() {
		Calendar c = Calendar.getInstance();
		return dateFormat.format(c.getTime());
	}
}
