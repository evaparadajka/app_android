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
import java.util.List;


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
//    boolean light1_state_b=false;
//    boolean light2_state_b=false;
//    boolean heat_state_b=false;
//    float temp_d=(float)20.5;
//    float temp_a=(float)22.1;

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

        checkDatabase();
    }

    /****** DATABASE? ******/
    public void checkDatabase(){
        Database db = new Database(this);
        List<State>  states = db.getByName("light1_state_b");
        if(states.isEmpty()){

            State state = new State();

            state.setName("light1_state_b");
            state.setValue(Boolean.toString(false));
            db.addVariable(state);

            state.setName("light2_state_b");
            state.setValue(Boolean.toString(false));
            db.addVariable(state);

            state.setName("heat_state_b");
            state.setValue(Boolean.toString(false));
            db.addVariable(state);

            state.setName("temp_d");
            state.setValue(Float.toString((float) 20.5));
            db.addVariable(state);

            state.setName("temp_a");
            state.setValue(Float.toString((float) 22.1));
            db.addVariable(state);
        } else {
            states.clear();
            states = db.getByName("light1_state_b");
            if(db.getByName("light1_state_b").get(0).getValue().contains("true")){
                light1.setText(getString(R.string.but_light_off));
                light1_state.setBackgroundColor(getColor(R.color.good));
            }

            states.clear();
            states = db.getByName("light2_state_b");
            if(states.get(0).getValue().contains("true")){
                light2.setText(getString(R.string.but_light_off));
                light2_state.setBackgroundColor(getColor(R.color.good));
            }

            states.clear();
            states = db.getByName("heat_state_b");
            if(states.get(0).getValue().contains("true")){
                heat.setText(getString(R.string.but_heat_off));
                heat_state.setBackgroundColor(getColor(R.color.good));
            }

            states.clear();
            states = db.getByName("temp_d");
            set_temp.setText(states.get(0).getValue());

            states.clear();
            states = db.getByName("temp_a");
            get_temp.setText(states.get(0).getValue());
        }
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
        Database db = new Database(this);
        List<State> states;
        State state;
        switch(v.getId()){
            case R.id.btn_light1:
                states = db.getByName("light1_state_b");
                if(states.get(0).getValue().contains("true")){
                    light1.setText(getString(R.string.but_light_on));
                    light1_state.setBackgroundColor(getColor(R.color.bad));
                    states.get(0).setValue(Boolean.toString(false));
                    db.modifyVariable(states.get(0));
                }else{
                    light1.setText(getString(R.string.but_light_off));
                    light1_state.setBackgroundColor(getColor(R.color.good));
                    states.get(0).setValue(Boolean.toString(true));
                    db.modifyVariable(states.get(0));
                }
                break;
            case R.id.btn_light2:
                states = db.getByName("light2_state_b");
                if(states.get(0).getValue().contains("true")){
                    light2.setText(getString(R.string.but_light_on));
                    light2_state.setBackgroundColor(getColor(R.color.bad));
                    states.get(0).setValue(Boolean.toString(false));
                    db.modifyVariable(states.get(0));
                }else{
                    light2.setText(getString(R.string.but_light_off));
                    light2_state.setBackgroundColor(getColor(R.color.good));
                    states.get(0).setValue(Boolean.toString(true));
                    db.modifyVariable(states.get(0));
                }
                break;
            case R.id.btn_heat:
                states = db.getByName("heat_state_b");
                if(states.get(0).getValue().contains("true")){
                    heat.setText(getString(R.string.but_heat_on));
                    heat_state.setBackgroundColor(getColor(R.color.bad));
                    states.get(0).setValue(Boolean.toString(false));
                    db.modifyVariable(states.get(0));
                }else{
                    heat.setText(getString(R.string.but_heat_off));
                    heat_state.setBackgroundColor(getColor(R.color.good));
                    states.get(0).setValue(Boolean.toString(true));
                    db.modifyVariable(states.get(0));
                }
                break;
        }
    }

    public void setTemperature(View v){
        Database db = new Database(this);
        List<State> states = db.getByName("temp_d");
        State state = states.get(0);
        float temp_d = Float.parseFloat(state.getValue());
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
        str = str.replace(",", ".");
        set_temp.setText(str);
        state.setValue(str);
        db.modifyVariable(state);
    }

}
