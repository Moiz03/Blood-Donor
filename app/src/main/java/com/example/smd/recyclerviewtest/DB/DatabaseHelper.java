//package com.example.smd.recyclerviewtest.DB;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import com.example.smd.recyclerviewtest.Models.DonorsData;
//
//import java.util.ArrayList;
//
//public class DatabaseHelper extends SQLiteOpenHelper implements DB_Interface{
//
//    public static final String DONORS_TABLE = "DONORS_DATA";
//    public static final String ID = "ID";
//    public static final String NAME = "NAME";
//    public static final String CITY = "CITY";
//    public static final String STATUS = "STATUS";
//    public static final String AVAILABILITY = "AVAILABILITY";
//    public static final String BLOOD_GROUP = "BLOOD_GROUP";
//    public static final String PHONE_NO = "PHONE_NO";
//
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, "donorDB", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableStatement = " CREATE TABLE " + DONORS_TABLE + " " +
//                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                NAME + " TEXT," +
//                CITY + " TEXT," +
//                STATUS + " TEXT," +
//                AVAILABILITY + " TEXT," +
//                BLOOD_GROUP + " TEXT," +
//                PHONE_NO + " TEXT)";
//
//        db.execSQL(createTableStatement);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public boolean addDonor(DonorsData donor)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(NAME,donor.getName());
//        cv.put(CITY,donor.getCity());
//        cv.put(STATUS,donor.getStatus());
//        cv.put(AVAILABILITY,donor.getAvailability());
//        cv.put(BLOOD_GROUP,donor.getBlood_group());
//        cv.put(PHONE_NO,donor.getPhone_no());
//
//        final long insert = db.insert(DONORS_TABLE, null, cv);
//
//        return insert != -1;
//    }
//
//    public ArrayList<DonorsData> getDonors()
//    {
//        ArrayList<DonorsData> donors = new ArrayList<>();
//        String queryString = "SELECT * FROM " + DONORS_TABLE;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(queryString,null);
//
//        if(cursor.moveToFirst())
//        {
//
//            do{
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                String city = cursor.getString(2);
//                String status = cursor.getString(3);
//                String availability =cursor.getString(4);
//                String blood_group = cursor.getString(5);
//                String phone_no = cursor.getString(6);
//
//                DonorsData donor = new DonorsData(id,name,status,availability,city,blood_group,phone_no);
//                donors.add(donor);
//
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return donors;
//    }
//}
