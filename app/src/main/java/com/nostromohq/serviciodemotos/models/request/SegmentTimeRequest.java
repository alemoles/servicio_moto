package com.nostromohq.serviciodemotos.models.request;

public class SegmentTimeRequest {
    private int userId;
    private int segmentTimeId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSegmentTimeId() {
        return segmentTimeId;
    }

    public void setSegmentTimeId(int segmentTimeId) {
        this.segmentTimeId = segmentTimeId;
    }
}
