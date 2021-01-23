package com.nostromohq.serviciodemotos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nostromohq.serviciodemotos.R;
import com.nostromohq.serviciodemotos.listeners.ClickListener;
import com.nostromohq.serviciodemotos.models.SegmentTime;

import java.util.List;

public class SegmentTimeListAdapter extends RecyclerView.Adapter<SegmentTimeListAdapter.SegmentTimeViewHolder> {
    private List<SegmentTime> segmentTimeList;
    private ClickListener<SegmentTime> segmentTimeClickListener;

    public SegmentTimeListAdapter(List<SegmentTime> segmentTimeList) {
        this.segmentTimeList = segmentTimeList;
    }

    public void setData(List<SegmentTime> segmentTimeList) {
        this.segmentTimeList = segmentTimeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SegmentTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_adapter_layout, parent, false);
        return new SegmentTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SegmentTimeViewHolder holder, int position) {
        final SegmentTime segmentTime = segmentTimeList.get(position);
        holder.timeLabel.setText(segmentTime.getTime());
        holder.availableDrivers.setText(String.valueOf(segmentTime.getAvailableDrivers()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segmentTimeClickListener.onItemClick(segmentTime);
                if (!segmentTime.isEditable()) {
                    holder.cardView.setCardBackgroundColor(0xffE57373);
                } else if (segmentTime.isSelected()) {
                    if (segmentTime.getAvailableDrivers() == 0)
                        holder.cardView.setCardBackgroundColor(0xffE57373);
                    else
                        holder.cardView.setCardBackgroundColor(0xff81D4FA);
                } else {
                    holder.cardView.setCardBackgroundColor(0xFFFFFFFF);
                }
                holder.availableDrivers.setText(String.valueOf(segmentTime.getAvailableDrivers()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return segmentTimeList.size();
    }

    public void setOnItemClickListener(ClickListener<SegmentTime> segmentTimeClickListener) {
        this.segmentTimeClickListener = segmentTimeClickListener;
    }

    class SegmentTimeViewHolder extends RecyclerView.ViewHolder {
        private TextView timeLabel;
        private TextView availableDrivers;
        private CardView cardView;

        public SegmentTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            timeLabel = itemView.findViewById(R.id.horario_time_label);
            availableDrivers = itemView.findViewById(R.id.motociclista_disponible_count_label);
            cardView = itemView.findViewById(R.id.carView);
        }
    }

}


