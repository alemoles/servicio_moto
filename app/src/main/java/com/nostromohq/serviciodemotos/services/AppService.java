package com.nostromohq.serviciodemotos.services;

import com.nostromohq.serviciodemotos.models.SegmentTime;
import com.nostromohq.serviciodemotos.models.request.SegmentTimeRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppService {

    @GET("segment-time")
    Call<List<SegmentTime>> getAllSegmentTimes();

    @GET("{userId}/segment-time")
    Call<List<SegmentTime>> getAllSegmentTimesByUserId(@Path("userId") int userId);

    @POST("/segment-time")
    Call<Void> updateMotorcycleCount(@Body SegmentTimeRequest segmentTimeRequest);

    @HTTP(method = "DELETE", path = "/segment-time", hasBody = true)
    Call<Void> removeMotorcycleCount(@Body SegmentTimeRequest model);

}
