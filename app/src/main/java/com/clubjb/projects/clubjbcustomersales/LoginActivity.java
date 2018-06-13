package com.clubjb.projects.clubjbcustomersales;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clubjb.projects.clubjbcustomersales.common.AppController;
import com.clubjb.projects.clubjbcustomersales.databinding.ActivityLoginBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

        private ActivityLoginBinding binding;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            initUi();
        }

        private void initUi() {
            binding.forgotTxt.setOnClickListener(this);
            binding.signInBtn.setOnClickListener(this);
            binding.signUpBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signUp_Btn:
                    startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                    break;
                case R.id.signIn_Btn:
                    validation();
                    break;
                case R.id.forgotTxt:
                    startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
                    break;
            }
        }

        public void validation() {
            if (!Utils.isNetworkAvailable(LoginActivity.this)) {
                Toast.makeText(LoginActivity.this, "Internet Connection", Toast.LENGTH_SHORT).show();
            } else if (binding.editTextEmail.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Please enter your Email Id", Toast.LENGTH_SHORT).show();
            } else if (!Utils.isValidEmail(binding.editTextEmail.getText().toString())) {
                Toast.makeText(LoginActivity.this, "Please enter valid Email Id", Toast.LENGTH_SHORT).show();
            } else if (binding.editTextPassword.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            } else {
              //  signUpApi();
                Toast.makeText(LoginActivity.this, "Good", Toast.LENGTH_SHORT).show();
            }


        }

        private void signUpApi() {


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    "", null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
//                params.put("param1", num1);
//                params.put("param2", num2);
                    return params;
                }
            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq);
        }

    }

