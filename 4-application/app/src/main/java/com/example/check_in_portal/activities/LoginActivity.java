package com.example.check_in_portal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.check_in_portal.databinding.LoginPageBinding;
import com.example.check_in_portal.models.Employee;
import com.example.check_in_portal.models.LoginViewModel;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity{

    private LoginPageBinding binding;

    private ExecutorService executor;

    private LoginViewModel loginViewModel;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Inflate the login page layout and set it as the content view
        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the LoginViewModel
        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);

        // Get the context of this activity
        context = this;

        // Initialize the executor service with a fixed thread pool of 1 thread
        this.executor = Executors.newFixedThreadPool(1);

        // Set the click listener for the login button
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the email and password entered by the user
                String strEmail = binding.LoginEmailAddress.getText().toString().trim().toLowerCase(Locale.ROOT);
                String strPassword = binding.LoginPassword.getText().toString().trim().toLowerCase(Locale.ROOT);

                // Check if both email and password are filled in
                if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Find the employee with the given email and password in the database
                LiveData<List<Employee>> employees = loginViewModel.findEmployeeByEmailAndPassword(strEmail, strPassword);

                // Observe the LiveData and start the MainActivity if an employee was found
                employees.observe(LoginActivity.this, new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(List<Employee> employeeList) {
                        if (employeeList.size() != 0){

                            // Create an intent to start the MainActivity and pass the employee ID as an extra
                            Intent intentMain = new Intent(context, MainActivity.class);
                            intentMain.putExtra("employee_id", String.valueOf(employeeList.get(0).getEmployeeId()));

                            // Start the MainActivity
                            context.startActivity(intentMain);
                        } else {
                            // Show a toast message if the email and password combination was not found in the database
                            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Set the click listener for the register button
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
