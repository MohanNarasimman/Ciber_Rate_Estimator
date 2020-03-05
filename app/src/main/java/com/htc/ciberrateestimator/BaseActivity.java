package com.htc.ciberrateestimator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.htc.ciberrateestimator.widgets.ProgressView;

public class BaseActivity extends AppCompatActivity {

    ProgressView mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void showProgress() {
        if (getApplicationContext() != null) {
            if (mProgressView != null) {
                hideProgress();
            }
            mProgressView = new ProgressView(getApplicationContext());
            ViewGroup decor = (ViewGroup) getWindow().getDecorView();
            decor.addView(mProgressView);
        }
    }

    public void hideProgress() {
        if (mProgressView == null)
            return;
        ((ViewGroup) (mProgressView.getParent())).removeView(mProgressView);
        mProgressView = null;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }
}
