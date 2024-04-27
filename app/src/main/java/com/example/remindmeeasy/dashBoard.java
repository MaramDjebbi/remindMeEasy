package com.example.remindmeeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindmeeasy.adapter.ReminderAdapter;
import com.example.remindmeeasy.model.reminder;
import com.example.remindmeeasy.DAO.ReminderDao;
import com.example.remindmeeasy.DB.RoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class dashBoard extends AppCompatActivity {

    private ReminderDao reminderDao;
    private RecyclerView reminderList;
    private ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        // Initialize views
        reminderList = findViewById(R.id.recycler);
        reminderList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReminderAdapter(this);
        reminderList.setAdapter(adapter);

        // Get reminderDao instance from RoomDB (using Singleton)
        RoomDB database = RoomDB.getInstance(getApplicationContext());
        reminderDao = database.reminderDao();

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

        // FloatingActionButton click listener for adding reminders
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

    // Handle click on a reminder item (optional)
    public void onReminderClicked(int position) {
        // Implement logic to handle click on a specific reminder
        // You can open an activity to edit or view details of the clicked reminder
    }
}
