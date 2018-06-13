package com.clubjb.projects.clubjbcustomersales;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.clubjb.projects.clubjbcustomersales.databinding.ActivityHomeBinding;
import com.clubjb.projects.clubjbcustomersales.fragment.FilterFragment;
import com.clubjb.projects.clubjbcustomersales.fragment.HomeFragment;
import com.clubjb.projects.clubjbcustomersales.fragment.MontlySaleFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    FragmentTransaction fragmentManager;
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        initUi();
    }
    private void initUi(){
        binding.leyFilter.setOnClickListener(this);
        binding.leyMonth.setOnClickListener(this);
        binding.leyCustomer.setOnClickListener(this);
        fragmentManager=getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.framecontainer,new HomeFragment()).commit();
        binding.leyCustomer.setBackgroundColor(Color.parseColor("#DF5124"));
        binding.leyFilter.setBackgroundColor(Color.parseColor("#09A6EB"));
        binding.leyMonth.setBackgroundColor(Color.parseColor("#09A6EB"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leyCustomer:
                binding.leyCustomer.setBackgroundColor(Color.parseColor("#DF5124"));
                binding.leyFilter.setBackgroundColor(Color.parseColor("#09A6EB"));
                binding.leyMonth.setBackgroundColor(Color.parseColor("#09A6EB"));
                fragmentManager=getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.framecontainer,new HomeFragment()).commit();
                break;
            case R.id.leyMonth:
                binding.leyMonth.setBackgroundColor(Color.parseColor("#DF5124"));
                binding.leyFilter.setBackgroundColor(Color.parseColor("#09A6EB"));
                binding.leyCustomer.setBackgroundColor(Color.parseColor("#09A6EB"));
                fragmentManager=getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.framecontainer,new MontlySaleFragment()).commit();
                break;
            case R.id.leyFilter:
                binding.leyMonth.setBackgroundColor(Color.parseColor("#09A6EB"));
                binding.leyFilter.setBackgroundColor(Color.parseColor("#DF5124"));
                binding.leyCustomer.setBackgroundColor(Color.parseColor("#09A6EB"));
                fragmentManager=getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.framecontainer,new FilterFragment()).commit();
                break;
        }
    }
}
