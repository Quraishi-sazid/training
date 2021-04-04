package com.example.mvvmexamplecif.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexamplecif.R;
import com.example.mvvmexamplecif.model.Note;

import java.util.ArrayList;
import java.util.List;
/*
we used ListAdapter instead of RecyclerView.Adapter.
we won't use notifyDataSetChanged method.
but the insert or delete methods needs position.
we don't get position in the live data observe method. what we get is the Note Object.
so DiffUtil method is needed to calculate the position.
for that we use List Adapter. submitList is used to set the list.
no data set changed method is needed to be called.DiffUtil will calculate the position and
data set changed method is called internally.
NB: diffCallback is static field at here. as NoteAdapter object is not still created while
sending to super,we can't use diffCallback as a member variable. static variables are loaded
when the class loaded.so it is available from when the class is loaded by jvm
*/
public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteViewHolder> {


    public NoteAdapter() {
        super(diffCallback);
    }

     static DiffUtil.ItemCallback<Note>diffCallback=new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority().equals(newItem.getPriority())
                    );
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.titleTextView.setText(getItem(position).getTitle());
        holder.priorityTextView.setText(getItem(position).getPriority());
        holder.descriptionTextview.setText(getItem(position).getDescription());
    }
    public Note getItemAtPosition(int position)
    {
        return getItem(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextview;
        public TextView priorityTextView;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.tv_title);
            descriptionTextview=itemView.findViewById(R.id.tv_description);
            priorityTextView=itemView.findViewById(R.id.tv_priority);
        }

    }
}
