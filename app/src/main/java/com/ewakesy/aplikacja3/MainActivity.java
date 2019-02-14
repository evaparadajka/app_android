package com.ewakesy.aplikacja3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1;
    TextView text1;

    public void OnButton1Click(View v){
        Toast.makeText(getApplicationContext(), "aaa!!! nie klikaj!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        text1 = (TextView) findViewById(R.id.textView1);
        button1.setText("kliknij mnie");
        text1.setTextColor(Color.BLUE);
        text1.setText("Niebieski napis");
    }
}
