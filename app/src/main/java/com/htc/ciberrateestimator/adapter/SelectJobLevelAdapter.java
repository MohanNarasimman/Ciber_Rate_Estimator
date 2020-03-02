package com.htc.ciberrateestimator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.htc.ciberrateestimator.R;
import com.htc.ciberrateestimator.listeners.SelectJobLevelListeners;
import com.htc.ciberrateestimator.model.JobLevelModel;

import java.util.ArrayList;
import java.util.List;

public class SelectJobLevelAdapter extends RecyclerView.Adapter<SelectJobLevelAdapter.SelectJobViewHolder> {

    private Context mCtx;
    private List<JobLevelModel> jobLevelModelList = new ArrayList<>();
    private SelectJobLevelListeners selectJobLevelListeners;
    private long selectedId = -1;

    public SelectJobLevelAdapter(Context mCtx, List<JobLevelModel> jobLevelModelList, long selectedId) {
        this.mCtx = mCtx;
        this.jobLevelModelList = jobLevelModelList;
        this.selectedId = selectedId;
    }

    @NonNull
    @Override
    public SelectJobLevelAdapter.SelectJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_job_level, parent, false);
        return new SelectJobViewHolder(view);
    }

    public void setSelectJobListener(SelectJobLevelListeners selectJobLevelListeners) {
        this.selectJobLevelListeners = selectJobLevelListeners;
    }


    @Override
    public void onBindViewHolder(@NonNull SelectJobLevelAdapter.SelectJobViewHolder holder, final int position) {
        holder.tvJob.setText(jobLevelModelList.get(position).getJobLevelName());

        if (jobLevelModelList.get(position).getJobId() == selectedId) {
            holder.ivSelectionJob.setBackgroundResource(R.drawable.check_selectedxhdpi);
        } else {
            holder.ivSelectionJob.setBackgroundResource(0);
        }

        holder.clJobLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectJobLevelListeners.onSelectJobLevelItemClick(jobLevelModelList.get(position),position);
                selectedId = jobLevelModelList.get(position).getJobId();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobLevelModelList.size();
    }

    class SelectJobViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clJobLevel;
        private ImageView ivSelectionJob;
        private TextView tvJob;

        SelectJobViewHolder(@NonNull View itemView) {
            super(itemView);
            clJobLevel = itemView.findViewById(R.id.clJobLevel);
            ivSelectionJob = itemView.findViewById(R.id.ivSelectionJob);
            tvJob = itemView.findViewById(R.id.tvJob);
        }
    }
}
