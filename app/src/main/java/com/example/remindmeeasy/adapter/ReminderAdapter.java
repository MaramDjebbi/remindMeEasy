package com.example.remindmeeasy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindmeeasy.R;
import com.example.remindmeeasy.model.reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<reminder> remindersList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnDeleteClickListener deleteClickListener;
    private OnUpdateClickListener updateClickListener; // Interface for update click listener
    private Context context;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnUpdateClickListener {
        void onUpdateClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    public void setOnUpdateClickListener(OnUpdateClickListener listener) {
        this.updateClickListener = listener;
    }

    public ReminderAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReminderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        reminder currentReminder = remindersList.get(position);
        holder.reminderName.setText(currentReminder.getName());
        holder.reminderDescription.setText(currentReminder.getDescription());

        // Convert Date object to String using appropriate date formatting
        Date dateTime = currentReminder.getDateTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault());
        String dateTimeString = dateFormat.format(dateTime);
        holder.reminderTime.setText(dateTimeString);

        // Set click listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onItemClick(clickedPosition);
                }
            }
        });

        // Set click listener for delete button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION && deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(clickedPosition);
                }
            }
        });

        // Set click listener for update button
        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION && updateClickListener != null) {
                    updateClickListener.onUpdateClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public void setReminders(List<reminder> reminders) {
        this.remindersList = reminders;
        notifyDataSetChanged();
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView reminderName, reminderDescription, reminderTime;
        ImageButton deleteButton, updateButton;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.Name);
            reminderDescription = itemView.findViewById(R.id.Description);
            reminderTime = itemView.findViewById(R.id.Time);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            updateButton = itemView.findViewById(R.id.updateButton);
        }
    }

    // Interface for handling click events on reminder items
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public reminder getReminderAt(int position) {
        return remindersList.get(position);
    }

}




