package com.example.check_in_portal.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.check_in_portal.repo.EmployeeRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private EmployeeRepository repository;
    private LiveData<List<Employee>> getAllData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = new EmployeeRepository(application);
        getAllData = repository.getAllEmployee();
    }

    public void insert(Employee employee) {
        repository.insertDta(employee);
    }

    public LiveData<List<Employee>> getGetAllData() {
        return getAllData;
    }

    public LiveData<List<Employee>> findEmployeeByEmailAndPassword (String email, String password) {
        return repository.findEmployeeByEmailAndPassword(email, password);
    }
}
