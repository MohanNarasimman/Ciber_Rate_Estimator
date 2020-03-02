package com.htc.ciberrateestimator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htc.ciberrateestimator.adapter.SelectJobTitleAdapter;
import com.htc.ciberrateestimator.listeners.SelectJobTitleListeners;
import com.htc.ciberrateestimator.model.JobTitleModel;

import java.util.ArrayList;
import java.util.List;

public class JobTitleActivity extends BaseActivity implements SelectJobTitleListeners {

    private RecyclerView rvSelectJobTitle;
    private SelectJobTitleAdapter selectJobTitleAdapter;
    List<JobTitleModel> jobTitleModelList = new ArrayList<>();
    private TextView tvCancel;

    List<JobTitleModel> filteredJobTitleModelList = new ArrayList<>();
    EditText edSearchJobTitle;
    private TextView tvNoResultFound;


    private JobTitleModel jobTitleModel;
    private long selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_title);

        tvCancel = findViewById(R.id.tvCancel);

        edSearchJobTitle = findViewById(R.id.edSearchJobTitle);
        rvSelectJobTitle = findViewById(R.id.rvSelectJobTitle);
        tvNoResultFound = findViewById(R.id.tvNoResultFound);
        rvSelectJobTitle.setHasFixedSize(true);
        rvSelectJobTitle.setLayoutManager(new LinearLayoutManager(this));
        rvSelectJobTitle.invalidate();
        rvSelectJobTitle.setMotionEventSplittingEnabled(false);


        if (getIntent().getStringExtra("jobTitleValue").equals("empty")) {
            selectedId = -1;
        } else {
            jobTitleModel = new Gson().fromJson(getIntent().getStringExtra("jobTitleValue"), JobTitleModel.class);
            selectedId = jobTitleModel.getJobTitleId();
        }


        jobTitleModelList = new Gson().fromJson(getIntent().getStringExtra("jobTitleList"), new TypeToken<List<JobTitleModel>>() {
        }.getType());


        selectJobTitleAdapter = new SelectJobTitleAdapter(this, jobTitleModelList, selectedId);
        selectJobTitleAdapter.setSelectJobListener(this);
        rvSelectJobTitle.setAdapter(selectJobTitleAdapter);


        edSearchJobTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


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
        returnIntent.putExtra("resultJobTitle", new Gson().toJson(jobTitleModel));
        returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void filter(String searchString) {
        filteredJobTitleModelList = new ArrayList<>();

        for (int i = 0; i < jobTitleModelList.size(); i++) {
            if (jobTitleModelList.get(i).getJobTitleName().toLowerCase().trim().contains(searchString.toLowerCase())) {
                filteredJobTitleModelList.add(jobTitleModelList.get(i));
            }
        }

        selectJobTitleAdapter.filteredList(filteredJobTitleModelList);

        if (filteredJobTitleModelList.size() > 0) {
            tvNoResultFound.setVisibility(View.GONE);
        } else {
            tvNoResultFound.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        hideKeyboard();
    }

    @Override
    public void onSelectJobTitleItemClick(JobTitleModel jobTitleModel, int position) {
        this.jobTitleModel = jobTitleModel;
        if (selectedId != jobTitleModel.getJobTitleId()) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick", "done");
            returnIntent.putExtra("resultJobTitle", new Gson().toJson(jobTitleModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick", "cancel");
            returnIntent.putExtra("resultJobTitle", new Gson().toJson(jobTitleModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
