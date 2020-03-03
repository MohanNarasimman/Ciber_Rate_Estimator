package com.htc.ciberrateestimator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private Button btLogin;
    private EditText etUsername, etPassword;
    private Map<String, String> map;
    private LinearLayout llLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        llLogin = findViewById(R.id.llLogin);

        map = new HashMap<>();

        map.put("Kishore", "123@Welcome");
        map.put("Ramki", "123Welcome@");
        map.put("Vel", "123Welcome");
        map.put("Mohan", "12345@Welcome");
        map.put("Selvakumar", "12345Welcome@");
        map.put("HTCUSER", "12345Welcome");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateAllFields()) {
                    String username = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();

                    if (password.equals(map.get(username))) {
                        Intent intent = new Intent(LoginActivity.this, NumberOfResourcesActivity.class);
                        intent.putExtra(getString(R.string.USERNAME), "" + username);
                        startActivity(intent);
                    } else {
//                        Toast.makeText(LoginActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                        Snackbar snackbar = Snackbar.make(llLogin, "Username or Password is incorrect", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

            }
        });

    }

    private boolean validateAllFields() {
        boolean status = true;
        if (etUsername.getText().toString().isEmpty()) {
            etUsername.setError("Username is required");
            status = false;
        }
        if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Password is required");
            status = false;
        }
        return status;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
