package com.example.check_in_portal.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.check_in_portal.DAOs.EmployeeDAO;
import com.example.check_in_portal.models.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeDB extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();


    private static EmployeeDB INSTANCE;

    public static EmployeeDB getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (EmployeeDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context, EmployeeDB.class, "EMPLOYEES")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
