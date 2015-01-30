package com.magicpaint.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Iterator;
import com.magicpaint.module.*;
import  com.magicpaint.services.*;
public class discoverMonitor extends BroadcastReceiver {
    public discoverMonitor() {
    }
private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
     this.context=context;
    if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(intent.getAction()))
    {
        Log.i("----------------->","Search Started!!!!!");
    }
        else
    {
        Log.i("----------------->","Search Finished!!!!!");
        if(!findtarget())
        {
            Log.i("------------>","we have no  target!!");
            BluetoothStart.blutooth.startDiscovery();
            Log.i("------------>","Discover again");
            if(BluetoothStart.blutooth.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)//超过120s 重新可见
            {
                reinitBlueTooth();
            }
        }
        else
        {
           Log.i("------------>","we got target!!");
           Intent connect=new Intent(context,bluetoothConnect.class);
            connect.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(connect);
        }
    }
    }
    public boolean findtarget()
    {
        boolean result=false;
        int flag=0;
        Iterator<bluetoothDevice> it=SearchedDevices.Devices.iterator();
        while(it.hasNext()&&flag==0)
        {
            bluetoothDevice device=it.next();
            if(device.getDeviceName().equals("magicPaint"))//找到目标机
            {
                flag=1;
                TargetDevice.targetDevice=device;
                result=true;
            }
        }
        return result;
    }

    public  void reinitBlueTooth()
    {
        while(!BluetoothStart.blutooth.isEnabled())
        {
            BluetoothStart.blutooth.enable();
        }

        while(BluetoothStart.blutooth.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
        {
            //已经开启可被查找模式
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
