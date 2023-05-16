package com.example.check_in_portal.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.check_in_portal.DAOs.CheckInsDAO;
import com.example.check_in_portal.models.CheckIns;

@Database(entities = {CheckIns.class}, version = 1, exportSchema = false)
public abstract class CheckInsDB extends RoomDatabase {
    public abstract CheckInsDAO checkInsDAO();
}
