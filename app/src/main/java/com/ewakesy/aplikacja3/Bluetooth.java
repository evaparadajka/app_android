package com.ewakesy.aplikacja3;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;




public class Bluetooth extends AppCompatActivity {

    /****** VIEW VARIABLES ******/
    Button search;
    Button show;
    ListView devices;

    private Set<BluetoothDevice> pairedDevices;

    /****** ON CREATE ******/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        search = (Button)findViewById(R.id.btn_search);
        show = (Button)findViewById(R.id.btn_show);
        devices = (ListView)findViewById(R.id.list_devices);

        BluetoothAdapter blue = BluetoothAdapter.getDefaultAdapter();
        if(!blue.isEnabled()){
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, 1);
        }
    }

    /****** OTHER METHODS ******/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i){
        if(resultCode == Activity.RESULT_OK){
            BluetoothAdapter blue = BluetoothAdapter.getDefaultAdapter();
        }
    }

    private final BroadcastReceiver receiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent i) {
            Log.d("AAA", "searchDevices: ");
            String action = i.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String status="";
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    status="nie sparowane";
                }else{
                    status="sparowane";
                }
                Log.d("INFO","znaleziono urządzenie: "+device.getName()+" - "+device.getAddress()+" - "+status);
            }
        }
    };

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
    public void searchDevices(View v){
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(receiver, filter);
        BluetoothAdapter blue = BluetoothAdapter.getDefaultAdapter();

        if (blue.isDiscovering()) {
            blue.cancelDiscovery();
        }

        blue.startDiscovery();
        Log.d("AAA", "bluetooth" + blue);
    }

    public void showKnownDevices(View v){
        BluetoothAdapter blue = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = blue.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + ": " + bt.getAddress());
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.war_dev_not_found), Toast.LENGTH_SHORT).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devices.setAdapter(adapter);
        devices.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Make an intent to start next activity.
//            Intent i = new Intent(Bluetooth.this, MainActivity.class);
//
//            //Change the activity.
//            i.putExtra("BLUE_ADDRESS", address); //this will be received at ledControl (class) Activity
//            startActivity(i);
            Toast.makeText(getApplicationContext(), address, Toast.LENGTH_SHORT).show();
        }
    };
}
