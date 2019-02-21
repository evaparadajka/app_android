package com.ewakesy.aplikacja3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    /****** VIEW VARIABLES ******/
    Button light1;
    Button light1_state;
    Button light2;
    Button light2_state;
    Button heat;
    Button heat_state;
    TextView set_temp;
    TextView get_temp;

    /****** FLOW VARIABLES ******/
    boolean light1_state_b=false;
    boolean light2_state_b=false;
    boolean heat_state_b=false;
    float temp_d=(float)20.5;
    float temp_a=(float)22.1;

    /****** ON CREATE ******/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        light1=(Button)findViewById(R.id.btn_light1);
        light1_state=(Button)findViewById(R.id.btn_light1_state);
        light2=(Button)findViewById(R.id.btn_light2);
        light2_state=(Button)findViewById(R.id.btn_light2_state);
        heat=(Button)findViewById(R.id.btn_heat);
        heat_state=(Button)findViewById(R.id.btn_heat_state);
        set_temp=(TextView)findViewById(R.id.text_set_temp);
        get_temp=(TextView)findViewById(R.id.text_get_temp);
    }

    /****** MENU ******/
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch(id){
            case R.id.action_main:
                break;
            case R.id.action_connections:
                Intent ic = new Intent(this, Bluetooth.class);
                startActivity(ic);
                break;
            case R.id.action_settings:
                Intent is = new Intent(this, Settings.class);
                startActivity(is);
                break;
            case R.id.action_about:
                Intent ia = new Intent(this, About.class);
                startActivity(ia);
                break;
        }
        return true;
    }

    /****** ON CLICK METHODS ******/
    public void switchOnOff(View v){
        switch(v.getId()){
            case R.id.btn_light1:
                if(light1_state_b){
                    light1.setText(getString(R.string.but_light_on));
                    light1_state.setBackgroundColor(getColor(R.color.bad));
                    light1_state_b = false;
                }else{
                    light1.setText(getString(R.string.but_light_off));
                    light1_state.setBackgroundColor(getColor(R.color.good));
                    light1_state_b=true;
                }
                break;
            case R.id.btn_light2:
                if(light2_state_b){
                    light2.setText(getString(R.string.but_light_on));
                    light2_state.setBackgroundColor(getColor(R.color.bad));
                    light2_state_b = false;
                }else{
                    light2.setText(getString(R.string.but_light_off));
                    light2_state.setBackgroundColor(getColor(R.color.good));
                    light2_state_b=true;
                }
                break;
            case R.id.btn_heat:
                if(heat_state_b){
                    heat.setText(getString(R.string.but_heat_on));
                    heat_state.setBackgroundColor(getColor(R.color.bad));
                    heat_state_b = false;
                }else{
                    heat.setText(getString(R.string.but_heat_off));
                    heat_state.setBackgroundColor(getColor(R.color.good));
                    heat_state_b=true;
                }
                break;
        }
    }

    public void setTemperature(View v){
        switch(v.getId()){
            case R.id.arrow_up:
                temp_d = temp_d + (float)0.1;
                break;
            case R.id.arrow_down:
                temp_d = temp_d - (float)0.1;
                break;
        }
        if(temp_d > 28){
            temp_d = 28;
        }
        if(temp_d < 16){
            temp_d = 16;
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        nf.setMinimumFractionDigits(1);
        String str = nf.format(temp_d);
        set_temp.setText(str);
    }
}
