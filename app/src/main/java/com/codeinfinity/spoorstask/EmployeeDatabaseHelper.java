package com.codeinfinity.spoorstask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {

     static final String DATABASE_NAME = "employee.db";
     static final int DATABASE_VERSION = 1;

     static final String TABLE_NAME = "employees";
     static final String COLUMN_ID = "id";
     static final String COLUMN_NAME = "name";
     static final String COLUMN_ADDRESS = "address";
     static final String COLUMN_DEPARTMENT = "department";
     static final String COLUMN_GENDER = "gender";
     static final String COLUMN_IS_FRESHER = "is_fresher";

    public EmployeeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_DEPARTMENT + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_IS_FRESHER + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertEmployee(String name, String address, String department, String gender, boolean isFresher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_DEPARTMENT, department);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_IS_FRESHER, isFresher ? 1 : 0);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
            String department = cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT));
            String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
            int isFresherInt = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FRESHER));
            boolean isFresher = (isFresherInt == 1);
            Employee employee = new Employee(id, name, address, department, gender, isFresher);
            employeesList.add(employee);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return employeesList;
    }


}

