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

    private EditText reminderName, reminderDate, reminderTime, reminderDescription;
    private Switch btnNotif;
    private Button btnAdd, btnCancel;

    private RoomDB database;
    private reminder existingReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);

        database = RoomDB.getInstance(this);

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

        // Check if an existing reminder object is passed via intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("existing_reminder")) {
            // Retrieve the existing reminder object from intent extras
            existingReminder = (reminder) extras.getSerializable("existing_reminder");
            // Populate the EditText fields with the values of the existing reminder
            populateFieldsWithExistingReminder(existingReminder);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrUpdateReminder();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openDatePicker() {
        // Date picker logic...
    }

    private void openTimePicker() {
        // Time picker logic...
    }

    private void populateFieldsWithExistingReminder(reminder existingReminder) {
        reminderName.setText(existingReminder.getName());
        reminderDescription.setText(existingReminder.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String date = dateFormat.format(existingReminder.getDateTime());
        String time = timeFormat.format(existingReminder.getDateTime());
        reminderDate.setText(date);
        reminderTime.setText(time);
        btnNotif.setChecked(existingReminder.isRepeat());
    }

    private void addOrUpdateReminder() {
        // Logic for adding or updating reminder...
    }
}



