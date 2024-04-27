//package com.example.remindmeeasy.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.remindmeeasy.R;
//import com.example.remindmeeasy.model.reminder;
//
//import java.util.List;
//
//public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {
//
//    private List<reminder> reminderList;
//
//    public ReminderAdapter(List<reminder> reminderList) {
//        this.reminderList = reminderList;
//    }
//
//    @NonNull
//    @Override
//    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
//        return new ReminderViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
//        reminder reminder = reminderList.get(position);
//        holder.titleTextView.setText(reminder.getTitle());
//        holder.descriptionTextView.setText(reminder.getDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        return reminderList.size();
//    }
//
//    public class ReminderViewHolder extends RecyclerView.ViewHolder {
//        public TextView titleTextView;
//        public TextView descriptionTextView;
//
//        public ReminderViewHolder(View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.title_textview);
//            descriptionTextView = itemView.findViewById(R.id.description_textview);
//        }
//    }
//}
//
