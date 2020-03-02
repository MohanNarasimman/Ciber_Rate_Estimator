package com.htc.ciberrateestimator.retrofit;

import com.htc.ciberrateestimator.model.response.AreaModelResponse;
import com.htc.ciberrateestimator.model.response.GetEstimateResponse;
import com.htc.ciberrateestimator.model.response.JobLevelModelResponse;
import com.htc.ciberrateestimator.model.response.JobTitleModelResponse;
import com.htc.ciberrateestimator.model.response.RateEstimateModel;
import com.htc.ciberrateestimator.model.response.VersionResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiInterface {

    //version API Call
    @Headers("api-key: 123")
    @GET("calculator/getversion")
    Call<VersionResponse> getVersion();

    //get all data API Call
    @Headers("api-key: 123")
    @GET("calculator/getalldata")
    Call<GetEstimateResponse> getEstimateData();

    @Headers("api-key: 123")
    @GET("calculator/getbillrateandestimatedrevenue/salary/{salary}/estimatedHours/{estimatedHours}/gp/{gp}/fixedPercentage/{fixedPercentage}/fixedAnnualBillableHours/{fixedAnnualBillableHours}")
    Call<RateEstimateModel> getRateEstimate(@Path("salary") double salary,
                                       @Path("estimatedHours") long estimatedHours,
                                       @Path("gp") double gp,
                                       @Path("fixedPercentage") double fixedPercentage,
                                       @Path("fixedAnnualBillableHours") double fixedAnnualBillableHours);


}
