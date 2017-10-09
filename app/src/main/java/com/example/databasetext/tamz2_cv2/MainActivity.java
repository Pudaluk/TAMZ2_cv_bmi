package com.example.databasetext.tamz2_cv2;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.example.databasetext.tamz2_cv2";
    double h, w, res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.bmi_calc_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText height_input = (EditText)findViewById(R.id.height_input);
        EditText weight_input = (EditText)findViewById(R.id.weight_input);

        if (!TextUtils.isEmpty(height_input.getText().toString().trim()) && !TextUtils.isEmpty(weight_input.getText().toString().trim()) ) {
            h = Double.valueOf(height_input.getText().toString());
            w = Double.valueOf(weight_input.getText().toString());
        }else {
            h=0;w=0;
        }


        h = h/100;
        res = w / Math.pow(h, 2);
        TextView bmi_res = (TextView)findViewById(R.id.bmi_res);

        if (!String.valueOf(res).equals("NaN")){
            bmi_res.setText(String.valueOf(String.format("%.2f", res)));
        } else {
            bmi_res.setText("0");
        }

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra(EXTRA_MESSAGE, String.valueOf(String.format("%.2f", res)));
        startActivity(i);
/*
        ImageView img_ok = (ImageView)findViewById(R.id.img_ok);
        ImageView img_a = (ImageView)findViewById(R.id.img_a);
        ImageView img_bad = (ImageView)findViewById(R.id.img_bad);
        ImageView img_bad_m = (ImageView)findViewById(R.id.img_bad_m);

        if( res < 18.4 ){
            img_bad_m.setVisibility(View.VISIBLE);
            img_ok.setVisibility(View.GONE);
            img_a.setVisibility(View.GONE);
            img_bad.setVisibility(View.GONE);

        }else if (res > 18.5 && res < 24.9){
            img_ok.setVisibility(View.VISIBLE);
            img_a.setVisibility(View.GONE);
            img_bad.setVisibility(View.GONE);
            img_bad_m.setVisibility(View.GONE);

        }else if (res > 25 && res < 35 ){
            img_a.setVisibility(View.VISIBLE);
            img_bad.setVisibility(View.GONE);
            img_bad_m.setVisibility(View.GONE);
            img_ok.setVisibility(View.GONE);

        }else if (res > 35.1 ){
            img_bad.setVisibility(View.VISIBLE);
            img_ok.setVisibility(View.GONE);
            img_a.setVisibility(View.GONE);
            img_bad_m.setVisibility(View.GONE);

        }else {
            img_ok.setVisibility(View.GONE);
            img_a.setVisibility(View.GONE);
            img_bad.setVisibility(View.GONE);
            img_bad_m.setVisibility(View.GONE);
        }*/

    }

    @Override
    public void onResume () {
        super.onResume();
        Log.wtf("wtf", "otoceno");
    }
}
