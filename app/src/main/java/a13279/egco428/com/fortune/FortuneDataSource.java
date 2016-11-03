package a13279.egco428.com.fortune;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pam on 11/3/2016.
 */
public class FortuneDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allCoumns = {MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_MSG,MySQLiteHelper.COLUMN_DATE,MySQLiteHelper.COLUMN_PIC};

    public FortuneDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public FortuneMessage createFortune(String msg,String date,String pic){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_MSG,msg);
        values.put(MySQLiteHelper.COLUMN_DATE,date);
        values.put(MySQLiteHelper.COLUMN_PIC,pic);

        long insertID = database.insert(MySQLiteHelper.TABLE_FORTUNE,null,values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FORTUNE,allCoumns,MySQLiteHelper.COLUMN_ID+" = "+insertID,null,null,null,null);
        cursor.moveToFirst();
        FortuneMessage newComment = cursorToComment(cursor);
        cursor.close();

        return newComment;

    }

    // delete first item
    public void deleteComment(FortuneMessage comment){
        long id = comment.getId();
        System.out.println("Comment deleted with id: "+id);
        database.delete(MySQLiteHelper.TABLE_FORTUNE,MySQLiteHelper.COLUMN_ID+" = "+id,null); // (comment,_id = id,null)
    }

    // open program and load all comment
    public List<FortuneMessage> getAllComments(){
        List<FortuneMessage> comments = new ArrayList<FortuneMessage>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_FORTUNE,allCoumns,null,null,null,null,null);
        cursor.moveToFirst();
        // get record and save in comment
        while(!cursor.isAfterLast()){
            FortuneMessage comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }

        cursor.close();
        return comments;

    }

    public FortuneMessage cursorToComment(Cursor cursor){
        FortuneMessage comment = new FortuneMessage();
        comment.setId(cursor.getLong(0)); // 0 = first column
        comment.setComment(cursor.getString(1));
        comment.setDate(cursor.getString(2));
        comment.setPic(cursor.getString(3));
        return comment;
    }
}
