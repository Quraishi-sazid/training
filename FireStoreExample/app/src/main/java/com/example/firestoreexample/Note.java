package com.example.firestoreexample;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Note extends BaseObservable {
    private String id;

    private String title;
    private String description;
    private String priority;
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
        notifyPropertyChanged(BR.priority);
    }
}
