package com.example.check_in_portal.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.check_in_portal.DAOs.EmployeeDAO;
import com.example.check_in_portal.databases.EmployeeDB;
import com.example.check_in_portal.models.Employee;

import java.util.List;

public class EmployeeRepository {

    // EmployeeDAO instance used to interact with the database
    private EmployeeDAO employeeDAO;

    // LiveData object that holds a list of all employees from the database
    private LiveData<List<Employee>> allEmployee;

    // Constructor that initializes the database and sets up the LiveData object
    public EmployeeRepository(Application application) {

        // Create a new instance of the database using the application context
        EmployeeDB db = EmployeeDB.getDatabase(application);

        // Set the employeeDAO instance to the DAO object obtained from the database
        employeeDAO = db.employeeDAO();

        // Set the LiveData object to hold all employees from the database
        allEmployee = employeeDAO.getAllEmployeesLive();
    }

    // Returns the LiveData object that holds a list of all employees from the database
    public LiveData<List<Employee>> getAllEmployee() {
        return allEmployee;
    }

    // Returns a LiveData object that holds a list of employees that match the email and password parameters
    public LiveData<List<Employee>> findEmployeeByEmailAndPassword(String email, String password) {
        return employeeDAO.findEmployeeByEmailAndPassword(email, password);
    }

    // Inserts employee data into the database using a background thread
    public void insertDta(Employee data) {
        // Execute the insertion on a background thread using the EmployeeInsertion class
        new EmployeeInsertion(employeeDAO).execute(data);
    }

    // AsyncTask that handles the insertion of employee data into the database on a background thread
    private static class EmployeeInsertion extends AsyncTask<Employee, Void, Void> {

        // EmployeeDAO instance used to interact with the database
        private EmployeeDAO employeeDAO;

        // Constructor that sets the employeeDAO instance to the DAO object obtained from the database
        private EmployeeInsertion(EmployeeDAO employeeDAO) {
            this.employeeDAO = employeeDAO;
        }

        // Override the doInBackground method to handle the insertion of employee data into the database on a background thread
        @Override
        protected Void doInBackground(Employee... data) {

            // Delete any existing login information from the database
            employeeDAO.deleteLogins();

            // Insert the employee details into the database
            employeeDAO.insertDetails(data[0]);

            return null;
        }
    }
}
