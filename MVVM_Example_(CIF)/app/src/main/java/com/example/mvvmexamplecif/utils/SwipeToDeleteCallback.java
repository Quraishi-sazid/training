package com.example.mvvmexamplecif.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmexamplecif.R;
import com.example.mvvmexamplecif.ViewModel.NoteViewModel;
import com.example.mvvmexamplecif.adapter.NoteAdapter;
import com.example.mvvmexamplecif.model.Note;
/*
* This class is used to draw a delete background when swipe is done.
*
* */
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    NoteViewModel noteViewModel;
    Context context;
    NoteAdapter noteAdapter;

    private Drawable icon;
    private final ColorDrawable background;

    public SwipeToDeleteCallback(Context context, NoteViewModel noteViewModel,NoteAdapter noteAdapter) {
        super(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT );
        this.noteViewModel=noteViewModel;
        this.context=context;
        this.noteAdapter=noteAdapter;
        icon = ContextCompat.getDrawable(context,
                R.drawable.ic_baseline_delete_24);
        background = new ColorDrawable(Color.RED);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        noteViewModel.delete(noteAdapter.getItemAtPosition(viewHolder.getAdapterPosition()));
        noteViewModel.lastDeletePos=viewHolder.getAdapterPosition();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20; //so background is behind the rounded corners of itemView
        int h=icon.getIntrinsicHeight();
        int oh=icon.getMinimumHeight();
        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin+(int)(dX*.5);
            int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
        } else if (dX < 0) { // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }
        
        background.draw(c);
        icon.draw(c);
    }
}
