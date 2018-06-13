package com.clubjb.projects.clubjbcustomersales;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.clubjb.projects.clubjbcustomersales.databinding.ActivitySignUpBinding;
import com.clubjb.projects.clubjbcustomersales.model.CityData;
import com.clubjb.projects.clubjbcustomersales.model.CountryData;
import com.clubjb.projects.clubjbcustomersales.model.StateData;

import java.util.ArrayList;
import java.util.List;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<CountryData>listCountry;
    private ArrayList<CityData> listCities;
    private ArrayList<StateData> listStates;
    private CountryData selectedCountryData;
    private StateData selectedStateData ;
    private CityData selectedCityData;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        initUi();
        //loadStateData();
    }

    private void initUi(){
        //Utils.hideKeyboard(this);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        binding.btnSignup.setOnClickListener(this);
        binding.tvState.setOnClickListener(this);
        binding.tvCity.setOnClickListener(this);
        binding.tvCountry.setOnClickListener(this);
        binding.llayoutCountry.setOnClickListener(this);
        binding.llayoutState.setOnClickListener(this);
        listCities = new ArrayList<>();
        listStates = new ArrayList<>();
        listCountry = new ArrayList<>();

        selectedStateData = new StateData();
        selectedCityData = new CityData();
        selectedCountryData = new CountryData();

        getCountryList();
       // getStateList();
        CountryData countryData = new CountryData();
        countryData.setId("1");
        countryData.setName("India");
        listCountry.add(countryData);

        CountryData countryData1 = new CountryData();
        countryData1.setId("2");
        countryData1.setName("USA");
        listCountry.add(countryData1);

        StateData stateData1=new StateData();
        stateData1.setId("1");
        stateData1.setName("Orissa");
        listStates.add(stateData1);

        StateData stateData=new StateData();
        stateData.setId("2");
        stateData.setName("Punjab");
        listStates.add(stateData);

        CityData stateCity1=new CityData();
        stateCity1.setId("1");
        stateCity1.setName("Jalandhar");
        listCities.add(stateCity1);

        CityData stateCity=new CityData();
        stateCity.setId("2");
        stateCity.setName("Bhubneshwar");
        listCities.add(stateCity);
    }

    private void getCountryList(){

    }
    private void  getStateList(){
    }

    private void  getCityList(){
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_signup:
                startActivity(new Intent(this,HomeActivity.class));
              //  validation();
                break;
            case R.id.tv_country:
                showCountryDialog();
                break;
            case R.id.tv_state:
                showStateDialog();
                break;
            case R.id.llayout_state:
                //showDialog();
                break;
            case R.id.tv_city:
                showCityDialog();
                break;
        }
    }

  private void showCountryDialog(){
        final Dialog dialogCountry = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialogsimplelist, null);
        ListView lv =  view.findViewById(R.id.list);
        CountryAdapter countryAdapter = new CountryAdapter(this, listCountry);
        lv.setAdapter(countryAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedCountryData  = listCountry.get(i);
                binding.tvCountry.setText(selectedCountryData.getName());
                dialogCountry.dismiss();
            }
        });
        dialogCountry.setContentView(view);
        dialogCountry.show();
    }

    private void showCityDialog(){
        final Dialog dialogCity = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialogsimplelist, null);
        ListView lv = view.findViewById(R.id.list);
        CityAdapter cityAdapter = new CityAdapter(this, listCities);
        lv.setAdapter(cityAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedCityData  = listCities.get(i);
                binding.tvCity.setText(selectedCityData.getName());
                dialogCity.dismiss();
            }
        });
        dialogCity.setContentView(view);
        dialogCity.show();
    }

    private void showStateDialog()
    {
        final Dialog dialog = new Dialog(this);

        View view = getLayoutInflater().inflate(R.layout.dialogsimplelist, null);
        ListView lv =  view.findViewById(R.id.list);
        StateAdapter stateAdapter = new StateAdapter(this, listStates);
        lv.setAdapter(stateAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                StateData data;
                data  = listStates.get(i);
                if(!data.getName().equals(selectedStateData.getName()))
                {
                    selectedStateData = data;
                    binding.tvState.setText(selectedStateData.getName());
                    binding.tvCity.setText("");
                }
                binding.tvCity.setEnabled(true);
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    private void validation(){
        if(!Utils.isNetworkAvailable(this)){
            Toast.makeText(this,"Internet Connection",Toast.LENGTH_SHORT).show();
        }
        else if(binding.tvState.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"State not selected",Toast.LENGTH_SHORT).show();
        }
        else if(binding.tvCity.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"City not selected",Toast.LENGTH_SHORT).show();
        }
        else if(binding.etName.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"UserName can't be blank",Toast.LENGTH_SHORT).show();
        }
        else if(binding.etEmail.getText().toString().equalsIgnoreCase("")){

            Toast.makeText(this,"Email can't be empty",Toast.LENGTH_SHORT).show();
        }
        else if(!Utils.isValidEmail(binding.etEmail.getText().toString())){
            Toast.makeText(this,"Please enter valid Email Id",Toast.LENGTH_SHORT).show();
        }
        else if(binding.etMobile.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"Mobile no. can't be empty",Toast.LENGTH_SHORT).show();
        }
        else if(binding.etPassword.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"Password cant't be empty",Toast.LENGTH_SHORT).show();
        }
        else if(binding.etConfirmPassword.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this,"Confirm Password can't be empty",Toast.LENGTH_SHORT).show();
        }
        else if(!binding.etPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString()))
        {
            Toast.makeText(this,"Password and Confirm Password does not match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            startActivity(new Intent(this,HomeActivity.class));
        }
    }


    class CountryAdapter extends BaseAdapter{

        LayoutInflater inflater;
        Context myContext;
        List<CountryData> newList;

        public CountryAdapter(Context context, ArrayList<CountryData> list)
        {
            myContext = context;
            newList = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return newList.size();
        }

        @Override
        public Object getItem(int position) {
            return newList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
            {
                convertView = LayoutInflater.from(myContext).inflate(R.layout.listitem_city_state, parent, false);
            }
            CountryData currentItem =  newList.get(position);
            TextView textViewItemName = (TextView) convertView.findViewById(R.id.tv_name);
            textViewItemName.setText(currentItem.getName());
            return convertView;


        }
    }

    class StateAdapter extends BaseAdapter
    {
        LayoutInflater inflater;
        Context myContext;
        List<StateData> newList;

        public StateAdapter(Context context, ArrayList<StateData> list)
        {
            myContext = context;
            newList = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return newList.size();
        }

        @Override
        public Object getItem(int position) {
            return newList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = LayoutInflater.from(myContext).inflate(R.layout.listitem_city_state, parent, false);
            }
            StateData currentItem =  newList.get(position);
            TextView textViewItemName = (TextView) convertView.findViewById(R.id.tv_name);
            textViewItemName.setText(currentItem.getName());
            return convertView;
        }
    }

    class CityAdapter extends BaseAdapter
    {

        LayoutInflater inflater;
        Context myContext;
        List<CityData> newList;

        public CityAdapter(Context context, ArrayList<CityData> list)
        {
            myContext = context;
            newList = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return newList.size();
        }

        @Override
        public Object getItem(int position) {
            return newList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = LayoutInflater.from(myContext).inflate(R.layout.listitem_city_state, parent, false);
            }
            CityData currentItem =  newList.get(position);
            TextView textViewItemName = (TextView) convertView.findViewById(R.id.tv_name);
            textViewItemName.setText(currentItem.getName());
            return convertView;
        }
    }
}
