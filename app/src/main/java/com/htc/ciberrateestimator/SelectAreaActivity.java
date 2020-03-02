package com.htc.ciberrateestimator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htc.ciberrateestimator.adapter.SelectAreaAdapter;
import com.htc.ciberrateestimator.listeners.SelectAreaListeners;
import com.htc.ciberrateestimator.model.AreaModel;

import java.util.ArrayList;
import java.util.List;

public class SelectAreaActivity extends AppCompatActivity implements SelectAreaListeners {

    private RecyclerView rvSelectArea;
    private SelectAreaAdapter selectAreaAdapter;
    List<AreaModel> areaModelList = new ArrayList<>();
    private TextView tvCancel;

    private AreaModel areaModel;
    private long selectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);

        tvCancel = findViewById(R.id.tvCancel);

        rvSelectArea = findViewById(R.id.rvSelectArea);
        rvSelectArea.setHasFixedSize(true);
        rvSelectArea.setLayoutManager(new LinearLayoutManager(this));
        rvSelectArea.invalidate();
        rvSelectArea.setMotionEventSplittingEnabled(false);

        if(getIntent().getStringExtra("areaValue").equals("empty")){
            selectedId = -1;
        } else{
            areaModel = new Gson().fromJson(getIntent().getStringExtra("areaValue"),AreaModel.class);
            selectedId = areaModel.getId();
        }

        areaModelList = new Gson().fromJson(getIntent().getStringExtra("areaList"), new TypeToken<List<AreaModel>>() {
        }.getType());

        selectAreaAdapter = new SelectAreaAdapter(this, areaModelList, selectedId);
        selectAreaAdapter.setSelectAreaListener(this);
        rvSelectArea.setAdapter(selectAreaAdapter);


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
        returnIntent.putExtra("buttonClick","cancel");
        returnIntent.putExtra("resultArea", new Gson().toJson(areaModel));
        returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onSelectAreaItemClick(AreaModel areaModel, int position) {
        this.areaModel = areaModel;
        if(selectedId!=areaModel.getId()) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick","done");
            returnIntent.putExtra("resultArea", new Gson().toJson(areaModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("buttonClick","cancel");
            returnIntent.putExtra("resultArea", new Gson().toJson(areaModel));
            returnIntent.putExtra("listPosition", getIntent().getIntExtra("listPosition", 0));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
