package com.ewakesy.aplikacja3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch(id){
            case R.id.action_main:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.action_main), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_connections:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.action_connections), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.action_about), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
