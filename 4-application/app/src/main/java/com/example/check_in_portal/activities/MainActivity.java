package com.example.check_in_portal.activities;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.check_in_portal.R;
import com.example.check_in_portal.databases.CheckInsDB;
import com.example.check_in_portal.databinding.ActivityMainBinding;
import com.example.check_in_portal.models.CheckIns;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ExecutorService executor;

    private static final int DAILY_REMINDER_REQUEST_CODE = 100;

    private static final String CHANNEL_ID = "reminder_channel";

    // Initialize the activity layout and database on creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();

        // Inflate the activity layout using view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create a Room database instance for check-ins
        CheckInsDB checkInsDB = Room.databaseBuilder(
                getApplicationContext(),
                CheckInsDB.class,
                "CheckIns Database"
        ).build();

        // Initialize a fixed thread pool executor service for database operations
        this.executor = Executors.newFixedThreadPool(1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_main:
                    // Current page, do nothing
                    return true;
                case R.id.action_login:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        bottomNavigationView.setSelectedItemId(R.id.action_main);

        // Add a click listener to the Submit button
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the rating value and current date
                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                int valueOfRating = (int) ratingBar.getRating();

                // Initializing the week day
                int dateTime = Calendar.DAY_OF_WEEK;

                // Get the employee ID from the intent and save the check-in to the database
                Intent intent = getIntent();
                int employeeId = Integer.parseInt(intent.getStringExtra("employee_id"));
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Check if a check-in already exists for this date and employee
                        CheckIns existingCheckIn = checkInsDB.checkInsDAO().getCheckInByDate(dateTime, employeeId);
                        if (existingCheckIn != null) {
                            // Show a toast message if a check-in already exists
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Check-in already exists for this date", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // Save the check-in to the database if it does not already exist
                            checkInsDB.checkInsDAO().saveCheckIn(
                                    new CheckIns(
                                            employeeId,
                                            valueOfRating,
                                            dateTime));

                            // Schedule the notification here
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    scheduleDailyReminder();
                                }
                            });
                        }
                    }
                });
            }
        });

        // Add a click listener to the Average button
        binding.buttonAvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the employee ID from the intent and calculate the average check-in score
                Intent intent = getIntent();
                int employeeId = Integer.parseInt(intent.getStringExtra("employee_id"));
                executor.execute(new Runnable() {
                public void run() {
                    // Query the database for the average check-in score
                    int avgScore = checkInsDB.checkInsDAO().AvgCheckInScore(employeeId);

                    // Display the average check-in score in a Toast message
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), String.valueOf(avgScore), Toast.LENGTH_SHORT).show();
                        }
                    });
                    }
                });
            }
        });

        // Set up a click listener for the "viewGraph" button
        binding.viewGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Execute a new Runnable on a separate thread using an Executor
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Get the employee ID from the Intent
                        Intent intentId = getIntent();
                        int employeeId = Integer.parseInt(intentId.getStringExtra("employee_id"));

                        // Retrieve data from the database for the employee ID
                        int[] dateList = checkInsDB.checkInsDAO().graphDataDate(employeeId);
                        int[] scoreList = checkInsDB.checkInsDAO().graphDataScore(employeeId);

                        // Create a new Intent to launch the GraphActivity
                        Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                        intent.putExtra("score_list", scoreList);
                        intent.putExtra("date_list", dateList);

                        // Start the GraphActivity with the Intent
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void scheduleDailyReminder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16); // 4 PM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1); // If it's past 4 PM, schedule for next day
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Daily Reminder")
                .setContentText("Time to check in for the day!")
                .setSmallIcon(R.drawable.notification_icon)
                .build();

        intent.putExtra(AlarmReceiver.NOTIFICATION_ID, DAILY_REMINDER_REQUEST_CODE);
        intent.putExtra(AlarmReceiver.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, DAILY_REMINDER_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}