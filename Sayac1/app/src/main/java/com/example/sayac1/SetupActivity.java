package com.example.sayac1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.chip.Chip;

public class SetupActivity extends AppCompatActivity {

    Button btnUpMinus;
    Button btnUpPlus;
    EditText etUpValue;
    Switch swUpVib;
    Switch swUpSound;
    int upValue=10;



    Button btnDownMinus;
    Button btnDownPlus;
    EditText etDownValue;
    Switch swVib;
    Switch swSound;
    int downValue=0;


    ConfigClass configClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        btnUpMinus =findViewById(R.id.btn_up_minus);
        btnUpPlus= findViewById(R.id.btn_up_plus);
        etUpValue=findViewById(R.id.et_up_value);
        swUpVib=findViewById(R.id.sw_up_vib);
        swUpSound=findViewById(R.id.sw_up_sound);


        btnDownMinus =findViewById(R.id.btn_down_minus);
        btnDownPlus= findViewById(R.id.btn_down_plus);
        etDownValue=findViewById(R.id.et_down_value);
        swVib=findViewById(R.id.sw_vib);
        swSound=findViewById(R.id.sw_sound);

        configClass=ConfigClass.getInstance(getApplicationContext());


        btnUpPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpValue(1);
            }
        });

        btnUpMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpValue(-1);
            }
        });

        btnDownPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDownValue(1);
            }
        });

        btnDownMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDownValue(-1);
            }
        });
    }
    public void setUpValue(int step){
        upValue+=step;
        etUpValue.setText(String.valueOf(upValue));
    }

    public void setDownValue(int step){
        downValue+=step;
        etDownValue.setText(String.valueOf(downValue));
    }

    @Override
    protected void onPause() {
        super.onPause();
        configClass.setData(upValue,downValue,configClass.currentValue,swUpVib.isChecked(),
                swVib.isChecked(),swUpSound.isChecked(),swSound.isChecked());
        configClass.saveData();
        Log.d("SETUP","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        configClass.loadData();
        upValue=configClass.upperLimit;
        downValue=configClass.lowerLimit;
        etUpValue.setText(String.valueOf(configClass.upperLimit));
        etDownValue.setText(String.valueOf(configClass.lowerLimit));
        swUpVib.setChecked(configClass.upperVib);
        swVib.setChecked(configClass.lowerVib);
        swUpSound.setChecked(configClass.upperSound);
        swSound.setChecked(configClass.lowerSound);
    }
}