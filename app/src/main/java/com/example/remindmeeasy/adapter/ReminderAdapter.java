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

import java.util.ArrayList;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private ArrayList<reminder> remindersList = new ArrayList<>();
    private OnItemClickListener clickListener; // Interface for click listener

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    private Context context; // Add a context field

    public ReminderAdapter(Context context) { // Constructor with Context argument
        this.context = context;
    }
    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReminderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.reminderName.setText(remindersList.get(position).getName());
        holder.reminderDescription.setText(remindersList.get(position).getDescription());
        holder.reminderTime.setText((CharSequence) remindersList.get(position).getDateTime());

        // Set click listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(position); // Call interface method with position
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public void setReminders(List<reminder> reminders) {
        this.remindersList.clear();
        this.remindersList.addAll(reminders);
        notifyDataSetChanged();
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView reminderName, reminderDescription, reminderTime;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.reminderName);
            reminderDescription = itemView.findViewById(R.id.reminderDescription);
            reminderTime = itemView.findViewById(R.id.reminerTime);
        }
    }

    // Interface for handling click events on reminder items
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
