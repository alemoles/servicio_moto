package com.nostromohq.serviciodemotos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nostromohq.serviciodemotos.adapters.SegmentTimeListAdapter;
import com.nostromohq.serviciodemotos.database.AppDatabase;
import com.nostromohq.serviciodemotos.listeners.ClickListener;
import com.nostromohq.serviciodemotos.models.SegmentTime;
import com.nostromohq.serviciodemotos.models.User;
import com.nostromohq.serviciodemotos.models.request.SegmentTimeRequest;
import com.nostromohq.serviciodemotos.repository.AppApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SegmentTimeListAdapter segmentTimeListAdapter;
    private List<SegmentTime> segmentTimeList;
    private int userId;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getInstance(this);
        segmentTimeList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fetchData();
        segmentTimeListAdapter = new SegmentTimeListAdapter(segmentTimeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        segmentTimeListAdapter.setOnItemClickListener(new ClickListener<SegmentTime>() {
            @Override
            public void onItemClick(SegmentTime data) {
                Toast.makeText(MainActivity.this, data.getTime(), Toast.LENGTH_SHORT).show();
                if (data.isSelected()) {
                    data.setSelected(false);
                    data.setAvailableDrivers(data.getAvailableDrivers() + 1);
                    removeSegmentTimeRequest(createRequest(data.getId()));
                } else {
                    data.setSelected(true);
                    data.setAvailableDrivers(data.getAvailableDrivers() - 1);
                    addSegmentTimeRequest(createRequest(data.getId()));
                }
            }
        });

        recyclerView.setAdapter(segmentTimeListAdapter);
    }

    private void fetchData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<User> users = db.appDao().getAll();
            if (users.size() > 0) {
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                userId = users.get(0).generateId;
                editor.putInt("userId", userId);
                editor.apply();
                Call<List<SegmentTime>> segmentTypesByUserId = AppApiClient.getAppService().getAllSegmentTimesByUserId(userId);
                segmentTypesByUserId.enqueue(new Callback<List<SegmentTime>>() {
                    @Override
                    public void onResponse(Call<List<SegmentTime>> call, Response<List<SegmentTime>> response) {
                        if (response.isSuccessful()) {
                            segmentTimeList = response.body();
                            segmentTimeListAdapter.setData(segmentTimeList);
                            recyclerView.setAdapter(segmentTimeListAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SegmentTime>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private SegmentTimeRequest createRequest(int segmentId) {
        SegmentTimeRequest segmentTimeRequest = new SegmentTimeRequest();
        segmentTimeRequest.setUserId(userId);
        segmentTimeRequest.setSegmentTimeId(segmentId);
        return segmentTimeRequest;
    }

    public void addSegmentTimeRequest(SegmentTimeRequest segmentTimeRequest) {
        Call<Void> addSegmentTimeResponseCall = AppApiClient.getAppService().updateMotorcycleCount(segmentTimeRequest);
        addSegmentTimeResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Motociclista reservado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void removeSegmentTimeRequest(SegmentTimeRequest segmentTimeRequest) {
        Call<Void> removeSegmentTimeResponseCall = AppApiClient.getAppService().removeMotorcycleCount(segmentTimeRequest);
        removeSegmentTimeResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Cancelación existosa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}