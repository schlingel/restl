package at.fundev.restl.administration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBHelper is used within the RequestStatusProvider. A simple helper class to manage
 * changes in the DB.
 */
public class DBHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "restlstatustable.db";
	
	private static final int DATABASE_VERSION = 1;
	
	/**
	 * Initializes the DB with the given context.
	 */
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Calls the onCreate method of the status table helper class.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		StatusTable.onCreate(db);
	}

	/**
	 * Calls the onUpgrade method of the status table helper class.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		StatusTable.onUpgrade(db, oldVersion, newVersion);
	}
}
