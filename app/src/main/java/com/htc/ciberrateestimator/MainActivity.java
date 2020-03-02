package com.htc.ciberrateestimator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.htc.ciberrateestimator.adapter.RateEstimatorAdaptor;
import com.htc.ciberrateestimator.listeners.EditTextListeners;
import com.htc.ciberrateestimator.listeners.RateEstimateDeleteListeners;
import com.htc.ciberrateestimator.model.AreaModel;
import com.htc.ciberrateestimator.model.JobLevelModel;
import com.htc.ciberrateestimator.model.JobTitleModel;
import com.htc.ciberrateestimator.model.RateEstimatorModel;
import com.htc.ciberrateestimator.model.response.RateEstimateModel;
import com.htc.ciberrateestimator.retrofit.ApiClient;
import com.htc.ciberrateestimator.retrofit.ApiInterface;
import com.htc.ciberrateestimator.room.EstimateDatabase;
import com.htc.ciberrateestimator.room.model.AreaRoomModel;
import com.htc.ciberrateestimator.room.model.FixedPercentRoomModel;
import com.htc.ciberrateestimator.room.model.JobLevelRoomModel;
import com.htc.ciberrateestimator.room.model.JobTitleRoomModel;
import com.htc.ciberrateestimator.room.model.SalaryRoomModel;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements RateEstimateDeleteListeners, EditTextListeners {

    private RecyclerView rvRateCard;
    private RateEstimatorAdaptor rateEstimatorAdaptor;
    List<RateEstimatorModel> rateEstimatorModelList = new ArrayList<>();
    private ImageView ivAddItem, ivRateBack, ivReset;
    private TextView tvNoItemFound;
    int noOfResources;
    public static final int AREACODE = 100;
    public static final int JOBTITLECODE = 101;
    public static final int JOBLEVELCODE = 102;
    private TextView tvBlendedGP;
    private TextView tvEstimatedRevenue;
    ApiInterface apiInterface;
    private ConstraintLayout clRateEstimator;

    List<JobLevelModel> jobLevelModelList = new ArrayList<>();

    List<AreaModel> areaModelList = new ArrayList<>();
    List<JobTitleModel> jobTitleModelList = new ArrayList<>();

    private TextView tvNoOfResources;


    private int selectedEstimate = 0;
    private EstimateDatabase estimateDatabase;

    private double fixedPercentage;
    private double fixedGP;
    private long fixedEstimatedHours;
    private long fixedAnnualBillableHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estimateDatabase = EstimateDatabase.getInstance(this);

        clRateEstimator = findViewById(R.id.clRateEstimator);
        noOfResources = getIntent().getIntExtra("noOfResources", 0);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ivAddItem = findViewById(R.id.ivAddItem);
        tvNoItemFound = findViewById(R.id.tvNoItemFound);

        ivRateBack = findViewById(R.id.ivRateBack);
        ivReset = findViewById(R.id.ivReset);

        tvBlendedGP = findViewById(R.id.tvBlendedGP);
        tvEstimatedRevenue = findViewById(R.id.tvEstimatedRevenue);

        tvNoOfResources = findViewById(R.id.tvNoOfResources);

        rvRateCard = findViewById(R.id.rvRateCard);
        rvRateCard.setHasFixedSize(true);
        rvRateCard.setLayoutManager(new LinearLayoutManager(this));
        rvRateCard.invalidate();
        rvRateCard.setMotionEventSplittingEnabled(false);

        List<FixedPercentRoomModel> fixedPercentRoomModels = estimateDatabase.estimateDao().getAllFixedPercent();

        fixedPercentage = fixedPercentRoomModels.get(0).getFixedPercentage();
        fixedGP = fixedPercentRoomModels.get(0).getFixedGP();
        fixedEstimatedHours = fixedPercentRoomModels.get(0).getFixedEstimatedHours();
        fixedAnnualBillableHours = fixedPercentRoomModels.get(0).getFixedAnnualBillableHours();

        tvBlendedGP.setText(0 + "%");

        for (int i = 0; i < noOfResources; i++) {
            rateEstimatorModelList.add(new RateEstimatorModel(fixedGP, fixedEstimatedHours));
        }

        tvNoOfResources.setText("No. of Resources : " + 0);

        rateEstimatorAdaptor = new RateEstimatorAdaptor(this, rateEstimatorModelList, fixedGP, fixedEstimatedHours);
        rateEstimatorAdaptor.setSelectDeleteRateEstimatorListener(this);
        rateEstimatorAdaptor.setGpValueListener(this);
        rvRateCard.setAdapter(rateEstimatorAdaptor);


        rateVisibility();

        ivRateBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rateEstimatorModelList.size() != 0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Do you want to reset the values?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    tvBlendedGP.setText(0 + "%");
                                    for (int i = 0; i < rateEstimatorModelList.size(); i++) {
                                        rateEstimatorModelList.get(i).setAreaModel(null);
                                        rateEstimatorModelList.get(i).setJobLevelModel(null);
                                        rateEstimatorModelList.get(i).setJobTitleModel(null);
                                        rateEstimatorModelList.get(i).setMinBillRate(0.0);
                                        rateEstimatorModelList.get(i).setEstimatedRevenueBySalary(0.0);
                                        rateEstimatorModelList.get(i).setMinBillRateBySalary(0.0);
                                        rateEstimatorModelList.get(i).setEstimatedRevenueByRate(0.0);
                                        rateEstimatorModelList.get(i).setEstimatedHours(fixedEstimatedHours);
                                        rateEstimatorModelList.get(i).setgP(fixedGP);
                                    }
                                    estimatedRevenue();
                                    rateEstimatorAdaptor.notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }
            }
        });

        ivAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateEstimatorModelList = rateEstimatorAdaptor.getRateModelList();
                rateEstimatorModelList.add(new RateEstimatorModel(fixedGP, fixedEstimatedHours));
                rateEstimatorAdaptor.notifyDataSetChanged();
                rateVisibility();
                rvRateCard.scrollToPosition(rateEstimatorModelList.size() - 1);
//                tvNoOfResources.setText("No. of Resources : " + rateEstimatorModelList.size());
            }
        });

        estimatedRevenue();

        selectedEstimate = 1;
        rateEstimatorAdaptor.setSelectedRate(1);

        List<AreaRoomModel> areas = estimateDatabase.estimateDao().getAllArea();
        for (int i = 0; i < areas.size(); i++) {
            areaModelList.add(new AreaModel(areas.get(i).getAreaID(), areas.get(i).getAreaName()));
        }

        List<JobTitleRoomModel> jobTitleModels = estimateDatabase.estimateDao().getAllJobTitle();
        for (int i = 0; i < jobTitleModels.size(); i++) {
            jobTitleModelList.add(new JobTitleModel(jobTitleModels.get(i).getJobTitleID(), jobTitleModels.get(i).getJobTitleName()));
        }
    }

    private void calculation(double salary, long estimatedHours, double gp, double fixedPercentage, long fixedAnnualBillableHours, final int position) {
        showProgress();
        apiInterface.getRateEstimate(salary, estimatedHours, gp, fixedPercentage, fixedAnnualBillableHours).enqueue(new Callback<RateEstimateModel>() {
            @Override
            public void onResponse(@NotNull Call<RateEstimateModel> call, @NotNull Response<RateEstimateModel> response) {
                hideProgress();
                if (response.body() != null) {
                    rateEstimatorModelList.get(position).setMinBillRateBySalary(response.body().getData().getMinBillRateBySalary());
                    rateEstimatorModelList.get(position).setEstimatedRevenueBySalary(response.body().getData().getEstimatedRevenueBySalary());
                    rateEstimatorAdaptor.notifyDataSetChanged();
                    estimatedRevenue();
                }
            }

            @Override
            public void onFailure(@NotNull Call<RateEstimateModel> call, @NotNull Throwable t) {
                hideProgress();
                Toast.makeText(MainActivity.this, "Server error. Please try again after sometime", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void internetAlertPopup() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("No internet connection");
        alertDialogBuilder
                .setMessage("Please check your internet connection and try again")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void estimatedRevenue() {
        gpCalculation();
        if (rateEstimatorModelList.size() > 0) {
            Double estimatedRevenue = 0.0;
            if (selectedEstimate == 0) {
                for (int i = 0; i < rateEstimatorModelList.size(); i++) {
                    estimatedRevenue = estimatedRevenue + rateEstimatorModelList.get(i).getEstimatedRevenueByRate();
                }
                tvEstimatedRevenue.setText("$" + new DecimalFormat("###,###,###,##0").format(estimatedRevenue));
            } else if (selectedEstimate == 1) {
                for (int i = 0; i < rateEstimatorModelList.size(); i++) {
                    estimatedRevenue = estimatedRevenue + rateEstimatorModelList.get(i).getEstimatedRevenueBySalary();
                }
                tvEstimatedRevenue.setText("$" + new DecimalFormat("###,###,###,##0").format(estimatedRevenue));
            }
        } else {
            tvEstimatedRevenue.setText("$0");
        }
    }

    @Override
    public void onBackPressed() {
        if (rateEstimatorModelList.size() == 0) {
            startActivity(new Intent(MainActivity.this, NumberOfResourcesActivity.class));
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Values will be cleared. Do you want to proceed?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, NumberOfResourcesActivity.class));
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
        }
    }

    private void rateVisibility() {
        if (rateEstimatorModelList.size() > 0) {
            tvNoItemFound.setVisibility(View.GONE);
            rvRateCard.setVisibility(View.VISIBLE);
        } else {
            rateEstimatorModelList = new ArrayList<>();
            tvNoItemFound.setVisibility(View.VISIBLE);
            rvRateCard.setVisibility(View.GONE);
        }
    }


    @Override
    public void onSelectRateEstimatedItemClick(View view, final RateEstimatorModel rateEstimatorModel, final int position) {
        if (rateEstimatorModelList.get(position).getgP() > 99.99) {
            rateEstimatorModelList.get(position).setgP(fixedGP);
            gpCalculation();
            rateEstimatorMethodCalculation(position);
            estimatedRevenue();
            rateEstimatorAdaptor.notifyDataSetChanged();
            hideKeyboard();
            Snackbar snackbar = Snackbar.make(clRateEstimator, "GP value can't be greater than 99.99%", Snackbar.LENGTH_LONG);
            snackbar.show();

        } else {
            hideKeyboard();
            switch (view.getId()) {
                case R.id.llArea: {
                    if (rateEstimatorModel.getAreaModel() != null) {
                        Intent intent = new Intent(this, SelectAreaActivity.class);
                        intent.putExtra("areaList", new Gson().toJson(areaModelList));
                        intent.putExtra("areaValue", new Gson().toJson(rateEstimatorModel.getAreaModel()));
                        intent.putExtra("listPosition", position);
                        startActivityForResult(intent, AREACODE);
                    } else {
                        Intent intent = new Intent(this, SelectAreaActivity.class);
                        intent.putExtra("areaList", new Gson().toJson(areaModelList));
                        intent.putExtra("areaValue", "empty");
                        intent.putExtra("listPosition", position);
                        startActivityForResult(intent, AREACODE);
                    }
                    break;
                }

                case R.id.llJobTitle: {
                    if ((rateEstimatorModel.getAreaModel() != null))
                        if (rateEstimatorModel.getJobTitleModel() != null) {
                            Intent intent = new Intent(this, JobTitleActivity.class);
                            intent.putExtra("jobTitleList", new Gson().toJson(jobTitleModelList));
                            intent.putExtra("jobTitleValue", new Gson().toJson(rateEstimatorModel.getJobTitleModel()));
                            intent.putExtra("listPosition", position);
                            startActivityForResult(intent, JOBTITLECODE);
                        } else {
                            Intent intent = new Intent(this, JobTitleActivity.class);
                            intent.putExtra("jobTitleList", new Gson().toJson(jobTitleModelList));
                            intent.putExtra("jobTitleValue", "empty");
                            intent.putExtra("listPosition", position);
                            startActivityForResult(intent, JOBTITLECODE);
                        }
                    break;
                }

                case R.id.llJobLevel: {
                    if (rateEstimatorModel.getAreaModel() != null && rateEstimatorModel.getJobTitleModel() != null) {

                        jobLevelModelList.clear();
                        List<JobLevelRoomModel> jobLevelRoomModels = estimateDatabase.estimateDao().getAllJobLevel(rateEstimatorModel.getJobTitleModel().getJobTitleId());
                        for (int i = 0; i < jobLevelRoomModels.size(); i++) {
                            jobLevelModelList.add(new JobLevelModel(jobLevelRoomModels.get(i).getRoleID(), jobLevelRoomModels.get(i).getRoleName()));
                        }

                        if (rateEstimatorModel.getJobLevelModel() != null) {
                            Intent intent = new Intent(MainActivity.this, JobLevelActivity.class);
                            intent.putExtra("jobLevelList", new Gson().toJson(jobLevelModelList));
                            intent.putExtra("jobLevelValue", new Gson().toJson(rateEstimatorModel.getJobLevelModel()));
                            intent.putExtra("listPosition", position);
                            startActivityForResult(intent, JOBLEVELCODE);
                        } else {
                            Intent intent = new Intent(MainActivity.this, JobLevelActivity.class);
                            intent.putExtra("jobLevelList", new Gson().toJson(jobLevelModelList));
                            intent.putExtra("jobLevelValue", "empty");
                            intent.putExtra("listPosition", position);
                            startActivityForResult(intent, JOBLEVELCODE);
                        }

                        if (isNetworkConnected()) {
                            rateEstimatorMethodCalculation(position);
                        }


                    }
                    break;
                }

                case R.id.ivDeleteCard: {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Do you want to delete this record?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    rateEstimatorAdaptor.removeRateElement(position);
                                    if (rateEstimatorModelList.size() >= 1) {
                                        tvNoItemFound.setVisibility(View.GONE);
                                        rvRateCard.setVisibility(View.VISIBLE);
                                    } else {
                                        tvNoOfResources.setText("No. of Resources : " + 0);
                                        tvBlendedGP.setText(0 + "%");
                                        rateEstimatorAdaptor.notifyDataSetChanged();
                                        tvNoItemFound.setVisibility(View.VISIBLE);
                                        rvRateCard.setVisibility(View.GONE);
                                    }
                                    estimatedRevenue();
//                                    tvNoOfResources.setText("No. of Resources : " + rateEstimatorModelList.size());
                                }
                            })

                            .setNegativeButton("NO", null)
                            .show();

                    break;
                }
                default:
                    break;
            }

            gpCalculation();
            estimatedRevenue();
            rateEstimatorAdaptor.notifyDataSetChanged();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AREACODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (data.getStringExtra("buttonClick").equals("done")) {
                    AreaModel areaModel = new Gson().fromJson(data.getStringExtra("resultArea"), AreaModel.class);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setAreaModel(areaModel);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setJobLevelModel(null);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setJobTitleModel(null);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setMinBillRate(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setEstimatedRevenueBySalary(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setMinBillRateBySalary(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setEstimatedRevenueByRate(0.0);
                    rateEstimatorAdaptor.notifyDataSetChanged();
                    estimatedRevenue();
                }
            }
        }

        if (requestCode == JOBTITLECODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (data.getStringExtra("buttonClick").equals("done")) {
                    JobTitleModel jobTitleModel = new Gson().fromJson(data.getStringExtra("resultJobTitle"), JobTitleModel.class);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setJobTitleModel(jobTitleModel);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setJobLevelModel(null);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setMinBillRate(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setEstimatedRevenueBySalary(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setMinBillRateBySalary(0.0);
                    rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setEstimatedRevenueByRate(0.0);
                    rateEstimatorAdaptor.notifyDataSetChanged();
                    estimatedRevenue();

                }

            }
        }

        if (requestCode == JOBLEVELCODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (data.getStringExtra("buttonClick").equals("done")) {
                    if (isNetworkConnected()) {
                        JobLevelModel jobLevelModel = new Gson().fromJson(data.getStringExtra("resultJobLevel"), JobLevelModel.class);
                        rateEstimatorModelList.get(data.getIntExtra("listPosition", 0)).setJobLevelModel(jobLevelModel);
                        rateEstimatorAdaptor.notifyDataSetChanged();
                        final int position = data.getIntExtra("listPosition", 0);
                        rateEstimatorMethodCalculation(position);
                    } else {
                        final int position = data.getIntExtra("listPosition", 0);
                        resetValues(position);
                        internetAlertPopup();
                    }

                }
            }
        }
    }

    private void resetValues(int position) {
        rateEstimatorModelList.get(position).setAreaModel(null);
        rateEstimatorModelList.get(position).setJobLevelModel(null);
        rateEstimatorModelList.get(position).setJobTitleModel(null);
        rateEstimatorModelList.get(position).setMinBillRate(0.0);
        rateEstimatorModelList.get(position).setEstimatedRevenueBySalary(0.0);
        rateEstimatorModelList.get(position).setMinBillRateBySalary(0.0);
        rateEstimatorModelList.get(position).setEstimatedRevenueByRate(0.0);
        rateEstimatorModelList.get(position).setgP(fixedGP);
        rateEstimatorModelList.get(position).setEstimatedHours(fixedEstimatedHours);
        rateEstimatorAdaptor.notifyDataSetChanged();
        estimatedRevenue();
    }

    private void rateEstimatorMethodCalculation(final int position) {
        if (rateEstimatorModelList.get(position).getJobLevelModel() != null) {
            List<SalaryRoomModel> salaryRoomModels = estimateDatabase.estimateDao().getAllSalary(rateEstimatorModelList.get(position).getAreaModel().getId(), rateEstimatorModelList.get(position).getJobTitleModel().getJobTitleId(), rateEstimatorModelList.get(position).getJobLevelModel().getJobId());
            calculation(salaryRoomModels.get(0).getMaxSalary(), rateEstimatorModelList.get(position).getEstimatedHours(), rateEstimatorModelList.get(position).getgP(), fixedPercentage, fixedAnnualBillableHours, position);
            gpCalculation();
        }
    }


    @Override
    public void editTextValueChanged(View v, int position) {
        switch (v.getId()) {
            case R.id.edGp: {
                if (isNetworkConnected()) {
                    if (rateEstimatorModelList.get(position).getgP() > 99.99) {
                        hideKeyboard();
                        rateEstimatorModelList.get(position).setgP(fixedGP);
                        gpCalculation();
                        rateEstimatorMethodCalculation(position);
                        estimatedRevenue();
                        rateEstimatorAdaptor.notifyDataSetChanged();
                        Snackbar snackbar = Snackbar.make(clRateEstimator, "GP value can't be greater than 99.99%", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        hideKeyboard();
                        gpCalculation();
                        rateEstimatorMethodCalculation(position);
                        estimatedRevenue();
                        rateEstimatorAdaptor.notifyDataSetChanged();
                    }
                } else {
                    if (rateEstimatorModelList.get(position).getJobLevelModel() != null) {
                        resetValues(position);
                        internetAlertPopup();
                    } else {
                        resetValues(position);
                    }
                }
                break;
            }
            case R.id.edEstimatedHours: {
                if (isNetworkConnected()) {
                    if (rateEstimatorModelList.get(position).getEstimatedHours() <= 0) {
                        hideKeyboard();
                        rateEstimatorModelList.get(position).setEstimatedHours(fixedEstimatedHours);
                        rateEstimatorMethodCalculation(position);
                        estimatedRevenue();
                        rateEstimatorAdaptor.notifyDataSetChanged();
                        Snackbar snackbar = Snackbar.make(clRateEstimator, "Estimated Hours should be greater than ZERO", Snackbar.LENGTH_LONG);
                        snackbar.show();

                    } else {
                        hideKeyboard();
                        rateEstimatorMethodCalculation(position);
                        estimatedRevenue();
                        rateEstimatorAdaptor.notifyDataSetChanged();
                    }
                } else {
                    if (rateEstimatorModelList.get(position).getJobLevelModel() != null) {
                        resetValues(position);
                        internetAlertPopup();
                    } else {
                        resetValues(position);
                    }
                }
                break;
            }
            default:
                break;
        }
    }


    private void gpCalculation() {
        if (rateEstimatorModelList.size() > 0) {
            Double gpValue = 0.00;
            int count = 0;
            for (int i = 0; i < rateEstimatorModelList.size(); i++) {
                if (rateEstimatorModelList.get(i).getJobLevelModel() != null) {
                    gpValue = gpValue + rateEstimatorModelList.get(i).getgP();
                    count++;
                }
            }
            if (count >= 1) {
                double resultGP = gpValue / count;
                tvNoOfResources.setText("No. of Resources : " + count);
//                tvBlendedGP.setText(new DecimalFormat("#0.00").format(resultGP) + "%");
                tvBlendedGP.setText(new DecimalFormat("#0.00").format(resultGP) + "%");

            } else {
                tvBlendedGP.setText(0 + "%");
                tvNoOfResources.setText("No. of Resources : " + count);
            }
        }
    }

}


