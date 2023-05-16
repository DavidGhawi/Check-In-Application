package com.example.check_in_portal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.check_in_portal.R;
import com.example.check_in_portal.databinding.RegisterPageBinding;
import com.example.check_in_portal.listeners.Listener;
import com.example.check_in_portal.models.Employee;
import com.example.check_in_portal.models.LoginViewModel;

import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements Listener {

    private LoginViewModel loginViewModel;

    private RegisterPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Inflate layout and set click listener
        binding = DataBindingUtil.setContentView(RegisterActivity.this, R.layout.register_page);
        binding.setClickListener((RegisterActivity) this);

        // Get instance of LoginViewModel
        loginViewModel = ViewModelProviders.of(RegisterActivity.this).get(LoginViewModel.class);

        // Observe changes in employee data
        loginViewModel.getGetAllData().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> data) {

                try {
                    // Display message
                    binding.message.setText("If registration is successful, you will be automatically redirected to the login page");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {

        String strEmail = binding.LoginEmailAddress.getText().toString().trim().toLowerCase(Locale.ROOT);
        String strPassword = binding.LoginPassword.getText().toString().trim().toLowerCase(Locale.ROOT);

        Employee employee = new Employee();

        if (TextUtils.isEmpty(strEmail)) {
            // Set error message if email field is empty
            binding.LoginEmailAddress.setError("Please Enter Your E-mail Address");
        }
        else if (TextUtils.isEmpty(strPassword)) {
            // Set error message if password field is empty
            binding.LoginPassword.setError("Please Enter Your Password");
        }
        else {

            // Save employee data
            employee.setEmail(strEmail);
            employee.setPassword(strPassword);
            loginViewModel.insert(employee);
            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();

            // Delay activity transition by 2 seconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }

    }
}
