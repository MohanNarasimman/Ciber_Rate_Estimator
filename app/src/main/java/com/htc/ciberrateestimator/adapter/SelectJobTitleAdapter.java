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
import com.htc.ciberrateestimator.listeners.SelectJobTitleListeners;
import com.htc.ciberrateestimator.model.JobTitleModel;

import java.util.ArrayList;
import java.util.List;

public class SelectJobTitleAdapter extends RecyclerView.Adapter<SelectJobTitleAdapter.SelectJobTitleViewHolder> {

    private Context mCtx;
    private List<JobTitleModel> jobTitleModelList = new ArrayList<>();
    private SelectJobTitleListeners selectJobTitleListeners;
    private long selectedId = -1;

    public SelectJobTitleAdapter(Context mCtx, List<JobTitleModel> jobLevelModelList,long selectedId) {
        this.mCtx = mCtx;
        this.jobTitleModelList = jobLevelModelList;
        this.selectedId = selectedId;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SelectJobTitleAdapter.SelectJobTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_job_title, parent, false);
        return new SelectJobTitleViewHolder(view);
    }

    public void setSelectJobListener(SelectJobTitleListeners selectJobTitleListeners) {
        this.selectJobTitleListeners = selectJobTitleListeners;
    }


    @Override
    public void onBindViewHolder(@NonNull SelectJobTitleAdapter.SelectJobTitleViewHolder holder, final int position) {
        holder.tvJob.setText(jobTitleModelList.get(position).getJobTitleName());

        if (jobTitleModelList.get(position).getJobTitleId()==selectedId) {
            holder.ivSelectionJob.setBackgroundResource(R.drawable.check_selectedxhdpi);
        } else {
            holder.ivSelectionJob.setBackgroundResource(0);
        }

        holder.clJobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectJobTitleListeners.onSelectJobTitleItemClick(jobTitleModelList.get(position), position);
                selectedId = jobTitleModelList.get(position).getJobTitleId();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobTitleModelList.size();
    }

    class SelectJobTitleViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout clJobTitle;
        private ImageView ivSelectionJob;
        private TextView tvJob;

        SelectJobTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            clJobTitle = itemView.findViewById(R.id.clJobTitle);
            ivSelectionJob = itemView.findViewById(R.id.ivSelectionJob);
            tvJob = itemView.findViewById(R.id.tvJob);
        }
    }


    public void filteredList(List<JobTitleModel> filteredJobTitleModelList) {
        jobTitleModelList = filteredJobTitleModelList;
        notifyDataSetChanged();
    }
}
