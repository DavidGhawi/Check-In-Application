package com.example.check_in_portal.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.check_in_portal.models.CheckIns;

import java.util.List;

/**
 CheckInsDAO is an interface that provides methods for accessing and
 manipulating CheckIns data in a database.
 */
@Dao
public interface CheckInsDAO {

    /**
     Retrieves all CheckIns records from the database.
     @return A list of CheckIns objects representing all records in the database.
     */
    @Query("SELECT * FROM CheckIns")
    List<CheckIns> getAllCheckIns();

    /**
     Retrieves all CheckIns records from the database associated with a given EmployeeId.
     @param employeeId The EmployeeId for which to retrieve records.
     @return A list of CheckIns objects representing all records in the database associated with the given EmployeeId.
     */
    @Query("SELECT * FROM CheckIns WHERE EmployeeId = :employeeId")
    List<CheckIns> getAllCheckInsByEmployeeId(int employeeId);

    /**
     Inserts a new CheckIns record into the database, or replaces an existing record if it already exists.
     @param checkIns The CheckIns object representing the record to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCheckIn(CheckIns checkIns);

    /**
     Retrieves a CheckIns record from the database associated with a given date and EmployeeId.
     @param date The date for which to retrieve the record.
     @param employeeId The EmployeeId for which to retrieve the record.
     @return The CheckIns object representing the record associated with the given date and EmployeeId, or null if no such record exists.
     */
    @Query("SELECT * FROM CheckIns WHERE Date = :date AND employeeId =:employeeId")
    CheckIns getCheckInByDate(int date, int employeeId);

    /**
     Calculates and retrieves the average Score for all CheckIns records associated with a given EmployeeId.
     @param employeeId The EmployeeId for which to calculate the average Score.
     @return The average Score for all CheckIns records associated with the given EmployeeId.
     */
    @Query("SELECT AVG(Score) AS avgScore FROM checkins WHERE employeeId = :employeeId GROUP BY employeeId")
    int AvgCheckInScore(int employeeId);

    /**
     Retrieves the dates for all CheckIns records associated with a given EmployeeId, for use in graphing data.
     @param employeeId The EmployeeId for which to retrieve dates.
     @return An array of Strings representing the dates for all CheckIns records associated with the given EmployeeId.
     */
    @Query("SELECT Date FROM checkins WHERE employeeId =:employeeId")
    int[] graphDataDate(int employeeId);

    /**
     Retrieves the Scores for all CheckIns records associated with a given EmployeeId, for use in graphing data.
     @param employeeId The EmployeeId for which to retrieve Scores.
     @return An array of ints representing the Scores for all CheckIns records associated with the given EmployeeId.
     */
    @Query("SELECT Score FROM checkins WHERE employeeId =:employeeId")
    int[] graphDataScore(int employeeId);

    /**
     Deletes the specified CheckIns records from the database.
     @param checkIns A list of CheckIns objects representing the records to be deleted.
     */
    @Delete
    void deleteCheckIns(List<CheckIns> checkIns);
}
