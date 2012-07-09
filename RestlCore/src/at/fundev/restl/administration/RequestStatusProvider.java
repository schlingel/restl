package at.fundev.restl.administration;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * ContentProvider for the request status table.
 */
public class RequestStatusProvider extends ContentProvider {
	
	/**
	 * The core part of the content provider path.
	 */
	private static final String AUTHORITY = "at.fundev.restl.administration";
	
	/**
	 * Used for checking if the given URI is valid or not.
	 */
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	/**
	 * The ID of the status. Used for the uriMatcher
	 */
	private static final int STATUS = 10;
	
	private static final int STATUS_ID = 20;
	
	private static final String BASE_PATH = "restl";
	
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	
	static {
		uriMatcher.addURI(AUTHORITY, BASE_PATH, STATUS);
		uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", STATUS_ID);
	}
	
	/**
	 * Contains the helper class for creating and upgrading the DB.
	 */
	private DBHelper database;
	
	
	@Override
	public boolean onCreate() {
		database = new DBHelper(getContext());
		return false;
	}
	
	/**
	 * Deletes the given selection or if the STATUS_ID is provided the given element. Throws an IllegalArgumentException if the uri is not valid.
	 * @throws IllegalArgumentException
	 * @return count of deleted rows
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		int rowsDeleted = 0;
		
		switch(uriType) {
			case STATUS:
				rowsDeleted = db.delete(StatusTable.TABLE_STATUS, selection, selectionArgs);
				break;
			case STATUS_ID:
				String id = uri.getLastPathSegment();
				
				if(TextUtils.isEmpty(selection)) {
					rowsDeleted = db.delete(StatusTable.TABLE_STATUS, StatusTable.COLUMN_ID + " = " + id, null);
				} else {
					rowsDeleted = db.delete(StatusTable.TABLE_STATUS, StatusTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
				}
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	/**
	 * Returns null.
	 * @return null
	 */
	@Override
	public String getType(Uri uri) {
		return null;
	}

	/**
	 * Inserts the given row if the uri is valid. Throws an IllegalArgumentException if
	 * the uri is not valid.
	 * @throws IllegalArgumentException
	 * @return the URI of the inserted row. Has an appended -1 as ID if an error occurred.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		
		long id = 0;
		switch(uriType) {
		case STATUS:
			id = db.insert(StatusTable.TABLE_STATUS, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return Uri.parse(BASE_PATH + "/" + id);
	}

	/**
	 * The query method executes the query if possible. Therefore it checks if the
	 * uri is valid. If it isn't query throws a IllegalArgumentException. If the URI
	 * is for a specific ID it returns only this row.
	 * @throws IllegalArgumentException
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		checkColumns(projection);
		
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		builder.setTables(StatusTable.TABLE_STATUS);
		int uriType = uriMatcher.match(uri);
		
		switch(uriType) {
			case STATUS:
				break;
			case STATUS_ID:
				builder.appendWhere(StatusTable.COLUMN_ID + "=" + uri.getLastPathSegment());
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}
	
	/**
	 * Checks if the given projection contains only valid column names. If it doesn't a 
	 * illegal argument exception is throwen.
	 * @param projection
	 */
	private void checkColumns(String[] projection) {
		String[] available = {
			StatusTable.COLUMN_ID,
			StatusTable.COLUMN_PATH,
			StatusTable.COLUMN_STATUS,
			StatusTable.COLUMN_TIMESTAMP
		};
		
		if(projection != null) {
			for(String cur : projection) {
				boolean found = false;
				
				for(String av : available) {
					if(av.equalsIgnoreCase(cur)) {
						found = true;
						break;
					}
				}
				
				if(!found) {
					throw new IllegalArgumentException("Only existing column names are allowed! " + cur + " does not exist!");
				}
			}
		}
	}
	
	/**
	 * Updates the given entries. Throws an IllegalArgumentException if the uri is not valid.
	 * @throws IllegalArgumentException
	 * @return count of updated rows.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = uriMatcher.match(uri);
		SQLiteDatabase db = database.getWritableDatabase();
		int rowsUpdated = 0;
		
		switch(uriType) {
			case STATUS:
				rowsUpdated = db.update(StatusTable.TABLE_STATUS, values, selection, selectionArgs);
				break;
			case STATUS_ID:
				String id = uri.getLastPathSegment();

				if(TextUtils.isEmpty(selection)) {
					rowsUpdated = db.update(StatusTable.TABLE_STATUS, values, StatusTable.COLUMN_ID + "=" + id, null);
				} else {
					rowsUpdated = db.update(StatusTable.TABLE_STATUS, values, StatusTable.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);				
				}
				
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsUpdated;
	}

}
