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

    Button button1;
    TextView text1;
    int btn_cnt=0;

    public void OnButton1Click(View v){
//        Toast.makeText(getApplicationContext(), getString(R.string.button_say), Toast.LENGTH_SHORT).show();
        btn_cnt = btn_cnt + 1;
        text1.setTextColor(getColor(R.color.good));
        text1.setText(getString(R.string.after_action)+btn_cnt);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch(id){
            case R.id.action_setttings:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_autor:
                Toast.makeText(getApplicationContext(), "Kliknięto: "+getString(R.string.autor), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        text1 = (TextView) findViewById(R.id.textView1);
    }
}
