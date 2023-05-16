package com.example.check_in_portal.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.check_in_portal.models.Employee;

import java.util.List;

/**
 EmployeeDAO is an interface that provides methods for accessing and
 manipulating Employee data in a database.
 */
@Dao
public interface EmployeeDAO {

    /**
     Retrieves an Employee record from the database associated with a given email address.
     @param email The email address for which to retrieve the record.
     @return The Employee object representing the record associated with the given email address, or null if no such record exists.
     */
    @Query("SELECT * FROM Employee WHERE Email = :email")
    Employee findByEmail(String email);

    /**
     Retrieves an Employee record from the database associated with a given EmployeeId.
     @param employeeId The EmployeeId for which to retrieve the record.
     @return The Employee object representing the record associated with the given EmployeeId, or null if no such record exists.
     */
    @Query("SELECT * FROM Employee WHERE EmployeeId = :employeeId")
    Employee findByEmployeeId(int employeeId);

    /**
     Retrieves all Employee records from the database as a LiveData object.
     @return A LiveData object containing a list of all Employee objects in the database.
     */
    @Query("SELECT * FROM Employee")
    LiveData<List<Employee>> getAllEmployeesLive();

    /**
     Retrieves an Employee record from the database associated with a given email address and password as a LiveData object.
     @param email The email address for which to retrieve the record.
     @param password The password for which to retrieve the record.
     @return A LiveData object containing a list of Employee objects representing the records associated with the given email address and password, or null if no such records exist.
     */
    @Query("SELECT * FROM Employee WHERE Email = :email AND Password = :password")
    LiveData<List<Employee>> findEmployeeByEmailAndPassword(String email, String password);

    /**
     Inserts a new Employee record into the database, or replaces an existing record if it already exists.
     @param data The Employee object representing the record to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDetails(Employee data);

    /**
     Deletes all Employee records from the database.
     */
    @Query("DELETE FROM Employee")
    void deleteLogins();
}
