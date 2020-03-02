package com.htc.ciberrateestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htc.ciberrateestimator.adapter.SelectJobLevelAdapter;
import com.htc.ciberrateestimator.listeners.SelectJobLevelListeners;
import com.htc.ciberrateestimator.model.JobLevelModel;

import java.util.ArrayList;
import java.util.List;

public class JobLevelActivity extends AppCompatActivity implements SelectJobLevelListeners {

    private RecyclerView rvSelectJobLevel;
    private SelectJobLevelAdapter selectJobLevelAdapter;
    List<JobLevelModel> jobLevelModelList = new ArrayList<>();
    private TextView tvCancel;


    private JobLevelModel jobLevelModel;
    private long selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_level);

        tvCancel = findViewById(R.id.tvCancel);

        rvSelectJobLevel = findViewById(R.id.rvSelectJobLevel);
        rvSelectJobLevel.setHasFixedSize(true);
        rvSelectJobLevel.setLayoutManager(new LinearLayoutManager(this));
        rvSelectJobLevel.invalidate();
        rvSelectJobLevel.setMotionEventSplittingEnabled(false);


        if (getIntent().getStringExtra("jobLevelValue").equals("empty")) {
            selectedId = -1;
        } else {
            jobLevelModel = new Gson().fromJson(getIntent().getStringExtra("jobLevelValue"), JobLevelModel.class);
            selectedId = jobLevelModel.getJobId();
        }

        jobLevelModelList = new Gson().fromJson(getIntent().getStringExtra("jobLevelList"), new TypeToken<List<JobLevelModel>>() {
        }.getType());


        selectJobLevelAdapter = new SelectJobLevelAdapter(this, jobLevelModelList, selectedId);
        selectJobLevelAdapter.setSelectJobListener(this);
        rvSelectJobLevel.setAdapter(selectJobLevelAdapter);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("buttonClick", "cancel");
        returnIntent.putExtra("resultJobLevel", new Gson().toJson(jobLevelModel));
        returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onSelectJobLevelItemClick(JobLevelModel jobLevelModel, int position) {
        this.jobLevelModel = jobLevelModel;
        if (selectedId != jobLevelModel.getJobId()) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick", "done");
            returnIntent.putExtra("resultJobLevel", new Gson().toJson(jobLevelModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick", "cancel");
            returnIntent.putExtra("resultJobLevel", new Gson().toJson(jobLevelModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

    }
}
