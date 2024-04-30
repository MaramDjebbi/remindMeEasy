package com.example.remindmeeasy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ArrayList<reminder> remindersList = new ArrayList<>();
    private OnItemClickListener clickListener; // Interface for click listener
    private Context context; // Add a context field

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public ReminderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReminderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, int position) {
        holder.reminderName.setText(remindersList.get(holder.getAdapterPosition()).getName());
        holder.reminderDescription.setText(remindersList.get(holder.getAdapterPosition()).getDescription());

        // Convert Date object to String using appropriate date formatting
        // Convert Date object to String using appropriate date formatting
        Date dateTime = remindersList.get(holder.getAdapterPosition()).getDateTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault());
        String dateTimeString = dateFormat.format(dateTime);
        holder.reminderTime.setText(dateTimeString);


        // Set click listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onItemClick(clickedPosition); // Call interface method with position
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public void setReminders(List<reminder> reminders) {
        this.remindersList = new ArrayList<>(reminders);
        notifyDataSetChanged();
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView reminderName, reminderDescription, reminderTime;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.Name);
            reminderDescription = itemView.findViewById(R.id.Description);
            reminderTime = itemView.findViewById(R.id.Time);
        }
    }

    // Interface for handling click events on reminder items
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
