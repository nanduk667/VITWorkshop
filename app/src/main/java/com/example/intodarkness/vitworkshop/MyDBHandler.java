package com.example.intodarkness.vitworkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "workshop.db";
    public static final String TABLE_WORKSHOP = "workshop";
    public static final String COLUMN_RNO = "_rollNo";
    public static final String COLUMN_NAME = "name";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + "\'" + TABLE_WORKSHOP + "\'" +  " (" +

                COLUMN_RNO + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT" + ");";

        db.execSQL(query);
        /*try {
            String sPackageName ="com.example.intodarkness.vitworkshop";
            String sDBName= "workshop.db";
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "data/"+ sPackageName +"/databases/"+sDBName;
                String backupDBPath = "/workshop/"+sDBName; //"{database name}";
                File dir = new File(sd,backupDBPath.replace(sDBName,""));
                if(dir.mkdir()) {

                }
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }

        } catch (Exception e) {
                System.out.println("File path change error: ");
                System.out.println(e);
        } */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKSHOP);
        onCreate(db);

    }
    public void addData(FormDatabase formDatabase)
    {
        ContentValues value = new ContentValues();
        value.put(COLUMN_RNO,formDatabase.get_rollNo());
        value.put(COLUMN_NAME,formDatabase.get_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORKSHOP, null, value);
        db.close();
    }
    public void deleteData(String rno)
    {
        SQLiteDatabase db = getWritableDatabase();
        System.out.println(rno);
        db.execSQL("DELETE FROM " + TABLE_WORKSHOP +  " WHERE 1"); // + COLUMN_RNO + "=\"" + rno + "\";");
    }
    public String databaseToString()
    {
        String dbString ="";
        SQLiteDatabase db = getWritableDatabase();


        String query = "SELECT * FROM "+ TABLE_WORKSHOP + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        try {
               c.moveToFirst();

            while (!c.isAfterLast()) {
                if (c.getString(0) != null) {
                    System.out.println("column index value: ");
                    System.out.print(c.getColumnIndex("COLUMN_RNO"));
                    dbString += c.getString(0);
                    dbString += "\n";
                    // c.moveToNext();
                }
                c.moveToNext();

            }

            db.close();
        }
        catch (Exception e) {
            // exception handling
        } finally {
            if(c != null)
                c.close();

        }
        return dbString;
    }

}
