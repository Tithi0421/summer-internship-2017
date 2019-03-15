package com.elitewaresolutions.krte.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tithi on 08-06-2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper mInstance = null;

    public static final String DATABASE_NAME = "UserDatabase";
    public static final String DATABASE_TABLE = "UserAccount";
    public static final String DATABASE_TABLE1 = "UserForms";
    private static final int DATABASE_VERSION = 1;
    private Context mCxt;

    public static final String Key_ID = "_id";
    public static final String First_Name = "FirstName";
    public static final String Last_Name = "LastName";
    public static final String Email_ID = "EmailID";
    public static final String Password = "Password";
    public static final String Civility = "Civility";
    public static final String Birthday = "Birthday";
    public static final String Phone = "Phone";
    public static final String Country = "Country";
    public static final String Hobbies = "Hobbies";

    public static final String Key_ID1 = "id";
    public static final String Equip = "Equip";
    public static final String Equip_Sr = "EquipSr";
    public static final String Make = "Make";
    public static final String Date_Mfg = "DateMfg";
    public static final String Capacity = "Capacity";
    public static final String Location = "Location";
    public static final String Shell_Check = "ShellCheck";
    public static final String Smooth_Check = "SmoothCheck";
    public static final String Drain_Check = "DrainCheck";
    public static final String Tight_Check = "TightCheck";
    public static final String Thick_Check = "ThickCheck";
    public static final String Lamination_Check = "LaminationCheck";
    public static final String Crack_Check = "CrackCheck";
    public static final String Hydro_Check = "HydroCheck";
    public static final String Range_Check = "RangeCheck";
    public static final String Valve_Check = "ValveCheck";





    public static DatabaseHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private DatabaseHelper(Context ctx) {

        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCxt = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
                + Key_ID + " INTEGER PRIMARY KEY," + First_Name + " TEXT," + Last_Name + " TEXT," + Email_ID + " TEXT," + Password + " TEXT," + Civility + " TEXT," + Birthday + " TEXT," + Phone + " TEXT," + Country + " TEXT," + Hobbies + " TEXT" +  ")";
        Log.d("Sql create",""+CREATE_CONTENT_TABLE);
        db.execSQL(CREATE_CONTENT_TABLE);

        String CREATE_CONTENT_TABLE1 = "CREATE TABLE " + DATABASE_TABLE1 + "("
                + Key_ID1 + " INTEGER PRIMARY KEY," + Equip + " TEXT," + Equip_Sr + " TEXT," + Make + " TEXT," + Date_Mfg + " TEXT," + Capacity + " TEXT," + Location + " TEXT," + Shell_Check + " TEXT," + Smooth_Check + " TEXT," + Drain_Check + " TEXT," + Tight_Check + " TEXT," + Thick_Check + " TEXT," + Lamination_Check + " TEXT," + Crack_Check + " TEXT," + Hydro_Check + " TEXT," + Range_Check + " TEXT," + Valve_Check + " TEXT" + ")";
        Log.d("Sql create",""+CREATE_CONTENT_TABLE1);
        db.execSQL(CREATE_CONTENT_TABLE1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);

        // Create tables again
        onCreate(db);
    }
}


