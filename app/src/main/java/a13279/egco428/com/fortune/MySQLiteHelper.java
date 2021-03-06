package a13279.egco428.com.fortune;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pam on 11/3/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_FORTUNE = "fortune"; // Table's Name
    public static final String COLUMN_ID = "_id"; // column's Name
    public static final String COLUMN_MSG = "comment"; // column's Name
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PIC = "pic";
    public static final String COLUMN_COLOR = "color";

    private static final String DATABASE_NAME = "fortunes.db"; // file Database Name
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FORTUNE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_MSG + " text not null, " + COLUMN_DATE + " text not null, "+ COLUMN_PIC + " text not null, "+ COLUMN_COLOR
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE); // execute SQL statement
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORTUNE);
        onCreate(db);
    }
}
