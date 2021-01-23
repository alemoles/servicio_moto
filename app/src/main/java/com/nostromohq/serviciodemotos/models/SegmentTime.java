package com.nostromohq.serviciodemotos.models;

public class SegmentTime {
    private int id;
    private String time;
    private int availableDrivers;
    private boolean isSelected;
    private boolean isEditable;

    public SegmentTime(int id, String time, int availableDrivers, boolean isSelected, boolean isEditable) {
        this.id = id;
        this.time = time;
        this.availableDrivers = availableDrivers;
        this.isSelected = isSelected;
        this.isEditable = isEditable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAvailableDrivers() {
        return availableDrivers;
    }

    public void setAvailableDrivers(int availableDrivers) {
        this.availableDrivers = availableDrivers;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }
}
