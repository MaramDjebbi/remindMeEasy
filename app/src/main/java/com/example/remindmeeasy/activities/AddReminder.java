package com.example.remindmeeasy.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.remindmeeasy.DB.RoomDB;
import com.example.remindmeeasy.R;
import com.example.remindmeeasy.model.reminder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddReminder extends AppCompatActivity {

    private  EditText reminderName, reminderDate,reminderTime, reminderDescription;
    private Switch btnNotif;
    private Button btnAdd, btnCancel;

    private RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        database = RoomDB.getInstance(this);

        RoomDB roomDB = Room.databaseBuilder(getApplicationContext(),
                        RoomDB.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();


        reminderName = findViewById(R.id.reminderName);
        reminderDate = findViewById(R.id.reminderDate);
        reminderTime = findViewById(R.id.reminderTime);

        reminderDescription = findViewById(R.id.reminderDescription);
        btnNotif = findViewById(R.id.btnNotif);
        btnAdd = findViewById(R.id.Add);
        btnCancel = findViewById(R.id.Cancel);

        // Set click listener on the reminderDateTime EditText to open the date picker dialog
        reminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(); // Open date picker dialog
            }
        });

        reminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker(); // Open date picker dialog
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReminder();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme , new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                // Create a Calendar instance and set it to the selected date
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                // Format the date using SimpleDateFormat
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                String formattedDate = sdf.format(calendar.getTime());

                // Showing the picked value in the EditText
                reminderDate.setText(formattedDate);
            }

        }, 2024, 01, 20);

        datePickerDialog.show();
    }


    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {


                //Showing the picked value in the textView
                reminderTime.setText(String.valueOf(hour)+ ":"+String.valueOf(minute));

            }
        }, 15, 30, false);

        timePickerDialog.show();
    }

    // Retrieving current user ID
    private int getCurrentUserId() {
        // Retrieve user ID from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        return preferences.getInt("user_id", -1); // Return -1 if user ID is not found
    }

    private void addReminder() {
        String name = reminderName.getText().toString().trim();
        String description = reminderDescription.getText().toString().trim();
        String dateTime = reminderDate.getText().toString().trim() + " " + reminderTime.getText().toString().trim(); // Concatenate date and time strings
        boolean repeat = btnNotif.isChecked();
        int priority = 0; // Set priority as needed
        String status = "active"; // Set status as needed
        String category = "general"; // Set category as needed
        int userId = getCurrentUserId(); // Get current user ID

        // Check if name, description, and dateTime are not empty
        if (!name.isEmpty() && !description.isEmpty() && !dateTime.isEmpty()) {
            // Execute database operation in AsyncTask
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    // Parse dateTime string to Date object
                    Date parsedDateTime = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
                    try {
                        parsedDateTime = dateFormat.parse(dateTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Create new reminder object
                    reminder newReminder = new reminder(0, name, description, parsedDateTime, repeat, userId);

                    // Insert reminder into database
                    database.reminderDao().insertReminder(newReminder);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(AddReminder.this, "Reminder added successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after adding reminder
                }
            }.execute();
        } else {
            Toast.makeText(AddReminder.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }




}
