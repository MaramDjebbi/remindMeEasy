package com.example.remindmeeasy.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.remindmeeasy.model.reminder;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ReminderDao {

    @Query("SELECT * FROM reminders")
    List<reminder> getAllReminders();


    // Get a specific reminder by ID
    @Query("SELECT * FROM reminders WHERE id = :id")
    reminder getReminderById(long id);

    // Insert a new reminder
    @Insert(onConflict = OnConflictStrategy.REPLACE) // Replace existing if ID conflict
    void insertReminder(reminder reminder);

    // Update an existing reminder
    @Update
    void updateReminder(reminder reminder);

    // Delete a reminder
    @Delete
    void deleteReminder(reminder reminder);
}
