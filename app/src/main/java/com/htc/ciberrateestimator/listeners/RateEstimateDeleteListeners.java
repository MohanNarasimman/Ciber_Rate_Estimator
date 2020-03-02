package com.htc.ciberrateestimator.listeners;

import android.view.View;

import com.htc.ciberrateestimator.model.RateEstimatorModel;

public interface RateEstimateDeleteListeners {
    void onSelectRateEstimatedItemClick(View view, RateEstimatorModel rateEstimatorModel, int position);
}
