package com.example.check_in_portal.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class CheckIns {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int checkInId;

    @ColumnInfo(name = "employeeId")
    private int employeeId;

    @ColumnInfo(name = "Score")
    private int score;

    @ColumnInfo(name = "Date")
    private int date;


    public CheckIns(int employeeId, int score, int date) {
        this.employeeId = employeeId;
        this.score = score;
        this.date = date;
    }


    public int getCheckInId() {
        return checkInId;
    }

    public void setCheckInId(int checkInId) {
        this.checkInId = checkInId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "CheckIns{" +
                "checkInId=" + checkInId +
                ", employeeId=" + employeeId +
                ", score=" + score +
                ", date='" + date + '\'' +
                '}';
    }
}
