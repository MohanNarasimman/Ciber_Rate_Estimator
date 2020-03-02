package com.htc.ciberrateestimator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.htc.ciberrateestimator.R;
import com.htc.ciberrateestimator.listeners.SelectAreaListeners;
import com.htc.ciberrateestimator.model.AreaModel;

import java.util.ArrayList;
import java.util.List;

public class SelectAreaAdapter extends RecyclerView.Adapter<SelectAreaAdapter.SelectAreaViewHolder> {

    private Context mCtx;
    private List<AreaModel> areaModelList = new ArrayList<>();
    private SelectAreaListeners selectAreaListeners;
    private long selectedId = -1;


    public SelectAreaAdapter(Context mCtx, List<AreaModel> areaModelList, long selectedId) {
        this.mCtx = mCtx;
        this.areaModelList = areaModelList;
        this.selectedId = selectedId;
    }

    @NonNull
    @Override
    public SelectAreaAdapter.SelectAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_area, parent, false);
        return new SelectAreaViewHolder(view);
    }

    public void setSelectAreaListener(SelectAreaListeners selectAreaListeners) {
        this.selectAreaListeners = selectAreaListeners;
    }


    @Override
    public void onBindViewHolder(@NonNull SelectAreaAdapter.SelectAreaViewHolder holder, final int position) {
        holder.tvAreaName.setText("Area "+areaModelList.get(position).getId());
        holder.tvAreaLocation.setText(areaModelList.get(position).getAreaLocation());


        if (areaModelList.get(position).getId() == selectedId) {
            holder.ivSelectionArea.setBackgroundResource(R.drawable.check_selectedxhdpi);
        } else {
            holder.ivSelectionArea.setBackgroundResource(R.drawable.check_defaultxhdpi);
        }

        holder.cvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAreaListeners.onSelectAreaItemClick(areaModelList.get(position), position);
                selectedId = areaModelList.get(position).getId();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaModelList.size();
    }

    class SelectAreaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAreaName;
        private TextView tvAreaLocation;
        private CardView cvArea;
        private ImageView ivSelectionArea;

        SelectAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAreaName = itemView.findViewById(R.id.tvAreaName);
            tvAreaLocation = itemView.findViewById(R.id.tvAreaLocation);
            cvArea = itemView.findViewById(R.id.cvArea);
            ivSelectionArea = itemView.findViewById(R.id.ivSelectionArea);
        }


    }
}
