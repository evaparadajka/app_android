package com.ewakesy.aplikacja3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class About extends AppCompatActivity {

    /****** ON CREATE ******/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
                Intent im = new Intent(this, MainActivity.class);
                startActivity(im);
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
                break;
        }
        return true;
    }
}
