package com.htc.ciberrateestimator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.ciberrateestimator.model.response.GetEstimateResponse;
import com.htc.ciberrateestimator.model.response.VersionResponse;
import com.htc.ciberrateestimator.retrofit.ApiClient;
import com.htc.ciberrateestimator.retrofit.ApiInterface;
import com.htc.ciberrateestimator.room.EstimateDatabase;
import com.htc.ciberrateestimator.room.model.AreaRoomModel;
import com.htc.ciberrateestimator.room.model.FixedPercentRoomModel;
import com.htc.ciberrateestimator.room.model.JobLevelRoomModel;
import com.htc.ciberrateestimator.room.model.JobTitleRoomModel;
import com.htc.ciberrateestimator.room.model.SalaryRoomModel;
import com.htc.ciberrateestimator.room.model.VersionRoomModel;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.htc.ciberrateestimator.constants.constants.SHAREDPREF;

public class NumberOfResourcesActivity extends BaseActivity {

    int noOfResources;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    EstimateDatabase estimateDatabase;
    String newDate;
    TextView tvUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_resources);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        estimateDatabase = EstimateDatabase.getInstance(this);

        tvUsername = findViewById(R.id.tvUsername);
        tvUsername.setText(getString(R.string.welcome,getIntent().getStringExtra(getString(R.string.USERNAME).trim())));

        sharedPreferences = getApplicationContext().getSharedPreferences(SHAREDPREF, 0);
        if (sharedPreferences.getString("openingValue", "").equals("opened")) {
            //call the version service and check
            getVersionCheck();
        }

        final TextView noOfCount = findViewById(R.id.no_of_res_count_txt);
        noOfCount.setText("1");
        Button nextBtn = findViewById(R.id.no_of_res_next_btn);
        ImageView reduceResourceCount = findViewById(R.id.no_of_res_reduce_count_btn);
        ImageView increaseResourceCount = findViewById(R.id.no_of_res_increase_count_btn);

        noOfResources = Integer.parseInt(noOfCount.getText().toString());
        reduceResourceCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (noOfResources > 1) {
                    noOfResources = noOfResources - 1;
                    noOfCount.setText("" + noOfResources);
                }
            }
        });

        increaseResourceCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (noOfResources < 100) {
                    noOfResources = noOfResources + 1;
                    noOfCount.setText("" + noOfResources);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOfResources > 0) {
                    if (!sharedPreferences.getString("openingValue", "").equals("opened")) {
                        if (isNetworkConnected()) {
                            new AlertDialog.Builder(NumberOfResourcesActivity.this)
                                    .setMessage("Master Data will be downloaded for offline use. Depending on your connection speed, this may take a while")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            showProgress();
                                            getAllData();
                                        }
                                    })
                                    .setCancelable(false)
                                    .show();
                        } else {
                            internetAlertPopup();
                        }
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
                        Date date1 = null;
                        Date date2 = null;

                        try {
                            date1 = sdf.parse(sharedPreferences.getString("oldDate", ""));
                            date2 = sdf.parse(newDate);

                            if (date1 != null && date2 != null) {
                                if (date1.compareTo(date2) < 0) {
                                    if (isNetworkConnected()) {
                                        new AlertDialog.Builder(NumberOfResourcesActivity.this)
                                                .setMessage("New updates found. Please click OK to download the latest Master Data")
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        showProgress();
                                                        getAllData();
                                                    }
                                                })
                                                .setCancelable(false)
                                                .show();
                                    } else {
                                        internetAlertPopup();
                                    }
                                } else if (date1.compareTo(date2) == 0) {
                                    Intent intent = new Intent(NumberOfResourcesActivity.this, MainActivity.class);
                                    intent.putExtra("noOfResources", noOfResources);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    if (isNetworkConnected()) {
                                        new AlertDialog.Builder(NumberOfResourcesActivity.this)
                                                .setMessage("New updates found. Please click OK to download the latest Master Data.")
                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        showProgress();
                                                        getAllData();
                                                    }
                                                })
                                                .setCancelable(false)
                                                .show();
                                    } else {
                                        internetAlertPopup();
                                    }
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

    }

    private void internetAlertPopup() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NumberOfResourcesActivity.this);
        alertDialogBuilder.setTitle("No internet connection");
        alertDialogBuilder
                .setMessage("No internet connection. Please check your internet connection")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void getVersionCheck() {
        if (isNetworkConnected()) {
            apiInterface.getVersion().enqueue(new Callback<VersionResponse>() {
                @Override
                public void onResponse(@NotNull Call<VersionResponse> call, @NotNull Response<VersionResponse> response) {
                    if (response.body() != null) {
                        VersionResponse versionResponse = response.body();
                        newDate = versionResponse.getData().get(0).getLastUpdatedDateTime();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<VersionResponse> call, @NotNull Throwable t) {
                }
            });
        } else {
            newDate = sharedPreferences.getString("oldDate", "");
        }
    }

    private void getAllData() {
        apiInterface.getEstimateData().enqueue(new Callback<GetEstimateResponse>() {
            @Override
            public void onResponse(@NotNull Call<GetEstimateResponse> call, @NotNull Response<GetEstimateResponse> response) {
                if (response.body() != null) {
                    GetEstimateResponse getEstimateResponse = response.body();
                    sharedPreferences.edit().putString("openingValue", "opened").apply();
                    savingDataToLocal(getEstimateResponse);
                }
            }

            @Override
            public void onFailure(@NotNull Call<GetEstimateResponse> call, @NotNull Throwable t) {
                hideProgress();
                Toast.makeText(NumberOfResourcesActivity.this, "Server error. Please try again after sometime", Toast.LENGTH_SHORT).show();
            }
        });






    }

    private void savingDataToLocal(GetEstimateResponse getEstimateResponse) {
        List<VersionRoomModel> versionRoomModels = new ArrayList<>();
        List<AreaRoomModel> areaRoomModels = new ArrayList<>();
        List<JobTitleRoomModel> jobTitleRoomModels = new ArrayList<>();
        List<JobLevelRoomModel> jobLevelRoomModels = new ArrayList<>();
        List<SalaryRoomModel> salaryRoomModels = new ArrayList<>();
        List<FixedPercentRoomModel> fixedPercentRoomModels = new ArrayList<>();


        for (int i = 0; i < getEstimateResponse.getVersion().size(); i++) {
            versionRoomModels.add(new VersionRoomModel(getEstimateResponse.getVersion().get(i).getVersionID(), getEstimateResponse.getVersion().get(i).getLastUpdatedDateTime()));
        }

        for (int i = 0; i < getEstimateResponse.getArea().size(); i++) {
            areaRoomModels.add(new AreaRoomModel(getEstimateResponse.getArea().get(i).getAreaID(), getEstimateResponse.getArea().get(i).getAreaName()));
        }


        for (int i = 0; i < getEstimateResponse.getJobTitle().size(); i++) {
            jobTitleRoomModels.add(new JobTitleRoomModel(getEstimateResponse.getJobTitle().get(i).getJobTitleID(), getEstimateResponse.getJobTitle().get(i).getJobTitleName()));
        }

        for (int i = 0; i < getEstimateResponse.getJobRole().size(); i++) {
            jobLevelRoomModels.add(new JobLevelRoomModel(getEstimateResponse.getJobRole().get(i).getRoleID(), getEstimateResponse.getJobRole().get(i).getJobTitleID(), getEstimateResponse.getJobRole().get(i).getRoleName()));
        }

        for (int i = 0; i < getEstimateResponse.getSalary().size(); i++) {
            salaryRoomModels.add(new SalaryRoomModel(getEstimateResponse.getSalary().get(i).getAreaID(), getEstimateResponse.getSalary().get(i).getJobTitleID(), getEstimateResponse.getSalary().get(i).getRoleID(), getEstimateResponse.getSalary().get(i).getMinSalary(), getEstimateResponse.getSalary().get(i).getMaxSalary(), getEstimateResponse.getSalary().get(i).getAverageSalary()));
        }

        for (int i = 0; i < getEstimateResponse.getSalaryFixedPercent().size(); i++) {
            fixedPercentRoomModels.add(new FixedPercentRoomModel(getEstimateResponse.getSalaryFixedPercent().get(i).getFixedPercentageID(), getEstimateResponse.getSalaryFixedPercent().get(i).getFixedPercentage(), getEstimateResponse.getSalaryFixedPercent().get(i).getFixedGP(), getEstimateResponse.getSalaryFixedPercent().get(i).getFixedEstimatedHours(),getEstimateResponse.getSalaryFixedPercent().get(i).getFixedAnnualBillableHours()));
        }

        estimateDatabase.estimateDao().deleteVersion();
        estimateDatabase.estimateDao().deleteArea();
        estimateDatabase.estimateDao().deleteJobLevel();
        estimateDatabase.estimateDao().deleteJobTitle();
        estimateDatabase.estimateDao().deleteSalary();
        estimateDatabase.estimateDao().deleteFixedPercent();

        estimateDatabase.estimateDao().insertAllVersion(versionRoomModels);
        estimateDatabase.estimateDao().insertAllArea(areaRoomModels);
        estimateDatabase.estimateDao().insertAllJobTitle(jobTitleRoomModels);
        estimateDatabase.estimateDao().insertAllJobLevel(jobLevelRoomModels);
        estimateDatabase.estimateDao().insertAllSalary(salaryRoomModels);
        estimateDatabase.estimateDao().insertAllFixedPercent(fixedPercentRoomModels);

        sharedPreferences.edit().putString("oldDate", estimateDatabase.estimateDao().getAllVersion().get(0).getLastUpdatedDateTime()).apply();

        hideProgress();

        Intent intent = new Intent(NumberOfResourcesActivity.this, MainActivity.class);
        intent.putExtra("noOfResources", noOfResources);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
//        super.onBackPressed();
    }
}
