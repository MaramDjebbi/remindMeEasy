package com.example.remindmeeasy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindmeeasy.R;
import com.example.remindmeeasy.adapter.ReminderAdapter;
import com.example.remindmeeasy.model.reminder;
import com.example.remindmeeasy.DAO.ReminderDao;
import com.example.remindmeeasy.DB.RoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class dashBoard extends AppCompatActivity {

    private ReminderDao reminderDao;
    private RecyclerView reminderRecyclerView;
    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Initialize views
        reminderRecyclerView = findViewById(R.id.recycler);
        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReminderAdapter(this);
        reminderRecyclerView.setAdapter(adapter);

        // Get reminderDao instance from RoomDB (using Singleton)
        RoomDB database = RoomDB.getInstance(getApplicationContext());
        reminderDao = database.reminderDao();

        // Set up FloatingActionButton click listener for adding reminders
        FloatingActionButton addButton = findViewById(R.id.add_plan_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open activity to create a new reminder
                Intent intent = new Intent(dashBoard.this, AddReminder.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the reminders when the activity is resumed
        loadRemindersFromDatabase();
    }

    private void loadRemindersFromDatabase() {
        // Fetch reminders in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<reminder> reminders = reminderDao.getAllReminders();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setReminders(reminders); // Update adapter with reminders
                    }
                });
            }
        }).start();
    }
}