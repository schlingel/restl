package at.fundev.restl.administration;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * This class is used for creating the status table.
 */
public class StatusTable {
	public static final String TABLE_STATUS = "status";
	
	public static final String COLUMN_ID = "_id";
	
	public static final String COLUMN_STATUS = "status";
	
	public static final String COLUMN_TIMESTAMP ="timestamp";
	
	public static final String COLUMN_PATH = "path";
	
	private static final String DATABASE_CREATE = "create table " +
			TABLE_STATUS +
			"( " + COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_STATUS + " integer not null, " +
			COLUMN_TIMESTAMP + " string not null, " + 
			COLUMN_PATH + " text ); ";
	
	/**
	 * Called when the DB is created for the first time. Creates the DB table.
	 * @param database
	 */
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	/**
	 * Drops the current table, if it exists, and calls the onCreate method.
	 * @param database
	 * @param oldVersion
	 * @param newVersion
	 */
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(StatusTable.class.getName(), String.format("Upgrading databse from version %d to %d, which will destroy all old data!", oldVersion, newVersion));
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_STATUS);
		onCreate(database);
	}	
}
