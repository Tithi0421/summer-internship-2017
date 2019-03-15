package com.elitewaresolutions.krte.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.elitewaresolutions.krte.model.UserAccount;
import com.elitewaresolutions.krte.model.UserForms;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tithi on 08-06-2017.
 */
public class DbQueries {
    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper mDbHelper;

    public DbQueries(Context context) {
        this.context = context;
    }


    public long addUserAccout(UserAccount userAccount) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.First_Name, userAccount.getFname());
        values.put(DatabaseHelper.Last_Name, userAccount.getLname());
        values.put(DatabaseHelper.Email_ID, userAccount.getEmail());
        values.put(DatabaseHelper.Password, userAccount.getPass());
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getWritableDatabase();
        long id = db.insert(DatabaseHelper.DATABASE_TABLE, null, values);
        db.close();
        return id;

    }
    public long addUserForms(UserForms userForms) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Equip, userForms.getEquip());
        values.put(DatabaseHelper.Equip_Sr, userForms.getEquipsr());
        values.put(DatabaseHelper.Make, userForms.getMake());
        values.put(DatabaseHelper.Date_Mfg, userForms.getDateMfg());
        values.put(DatabaseHelper.Capacity, userForms.getCap());
        values.put(DatabaseHelper.Location, userForms.getLoc());
        values.put(DatabaseHelper.Shell_Check, userForms.getShell());
        values.put(DatabaseHelper.Smooth_Check, userForms.getSmooth());
        values.put(DatabaseHelper.Drain_Check, userForms.getDrain());
        values.put(DatabaseHelper.Tight_Check, userForms.getTight());
        values.put(DatabaseHelper.Thick_Check, userForms.getThick());
        values.put(DatabaseHelper.Lamination_Check, userForms.getLam());
        values.put(DatabaseHelper.Crack_Check, userForms.getCrack());
        values.put(DatabaseHelper.Hydro_Check, userForms.getHydro());
        values.put(DatabaseHelper.Range_Check, userForms.getRange());
        values.put(DatabaseHelper.Valve_Check, userForms.getValve());
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getWritableDatabase();
        long id = db.insert(DatabaseHelper.DATABASE_TABLE1, null, values);
        db.close();
        return id;

    }



    public void addUserAccout1(UserAccount userAccount) {
        ContentValues values = new ContentValues();
        SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
        Long id = sharedPreferenceUtils.getLong(context, "key");
        values.put(DatabaseHelper.Civility, userAccount.getCiv());
        values.put(DatabaseHelper.Birthday, userAccount.getBirth());
        values.put(DatabaseHelper.Phone, userAccount.getPh());
        values.put(DatabaseHelper.Country, userAccount.getCoun());
        values.put(DatabaseHelper.Hobbies, userAccount.getHob());
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getWritableDatabase();
        String where = DatabaseHelper.Key_ID + "="+id;
        long row =  db.update(DatabaseHelper.DATABASE_TABLE, values, where, null);
        db.close();


    }

    public long validateUser(String name, String password) {
        long id = 0;
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, null, DatabaseHelper.Email_ID + " = '" + name + "'" + " AND " + DatabaseHelper.Password + " = '" + password + "'", null, null, null, null);
        if (cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Key_ID));
            }
            return id;
        } else {
            return -1;
        }
    }


    UserAccount getUserAccount(int id) {
        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, new String[]{DatabaseHelper.Key_ID, DatabaseHelper.First_Name, DatabaseHelper.Last_Name, DatabaseHelper.Email_ID, DatabaseHelper.Password, DatabaseHelper.Civility, DatabaseHelper.Birthday, DatabaseHelper.Phone, DatabaseHelper.Country, DatabaseHelper.Hobbies}, DatabaseHelper.Key_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        UserAccount userAccount = new UserAccount(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
        return userAccount;
    }

    public List<UserAccount> getAllUserAccount() {
        List<UserAccount> userAccountList = new ArrayList<UserAccount>();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.DATABASE_TABLE;

        db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserAccount userAccount = new UserAccount();
                userAccount.setId(Integer.parseInt(cursor.getString(0)));
                userAccount.setFname(cursor.getString(1));
                userAccount.setLname(cursor.getString(2));
                userAccount.setEmail(cursor.getString(3));
                userAccount.setPass(cursor.getString(4));

                userAccountList.add(userAccount);
            } while (cursor.moveToNext());
        }

        return userAccountList;
    }


    public List<UserForms> getAllUserForms() {
        List<UserForms> userFormsesList = new ArrayList<UserForms>();
       // String selectQuery = "SELECT  * FROM " + DatabaseHelper.DATABASE_TABLE1;
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE1, null,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                UserForms userForms = new UserForms();
                userForms.setId1(Integer.parseInt(cursor.getString(0)));
                userForms.setEquip(cursor.getString(1));
                userForms.setEquipsr(cursor.getString(2));
                userForms.setMake(cursor.getString(3));
                userForms.setDateMfg(cursor.getString(4));
                userForms.setCap(cursor.getString(5));
                userForms.setLoc(cursor.getString(6));
                userForms.setShell(cursor.getString(7));
                userForms.setSmooth(cursor.getString(8));
                userForms.setDrain(cursor.getString(9));
                userForms.setTight(cursor.getString(10));
                userForms.setThick(cursor.getString(11));
                userForms.setLam(cursor.getString(12));
                userForms.setCrack(cursor.getString(13));
                userForms.setHydro(cursor.getString(14));
                userForms.setRange(cursor.getString(15));
                userForms.setValve(cursor.getString(16));

                userFormsesList.add(userForms);
            } while (cursor.moveToNext());
        }

        return userFormsesList;
    }



    public int updateUserAccount(UserAccount userAccount) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.First_Name, userAccount.getFname());
        values.put(DatabaseHelper.Last_Name, userAccount.getLname());
        values.put(DatabaseHelper.Email_ID, userAccount.getEmail());
        values.put(DatabaseHelper.Password, userAccount.getPass());
        db = mDbHelper.getWritableDatabase();

        return db.update(DatabaseHelper.DATABASE_TABLE, values, DatabaseHelper.Key_ID + " = ?", new String[]{String.valueOf(userAccount.getId())});

    }

    public void deleteUserAccount(UserAccount userAccount) {
        db = mDbHelper.getWritableDatabase();
        mDbHelper = DatabaseHelper.getInstance(context);
        db.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.Key_ID + " = ?", new String[]{String.valueOf(userAccount.getId())});
        db.close();
    }

    public void deleteUserForms(Long id) {
        mDbHelper = DatabaseHelper.getInstance(context);
        db = mDbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.DATABASE_TABLE1, DatabaseHelper.Key_ID1 + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getUserAccountCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHelper.DATABASE_TABLE;
        db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


}
