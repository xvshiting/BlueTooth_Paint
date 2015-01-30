package com.magicpaint.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.magicpaint.module.*;
public class discoverResult extends BroadcastReceiver {
    public discoverResult() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        bluetoothDevice device=new bluetoothDevice();
        String deviceName=intent.getStringExtra(BluetoothDevice.EXTRA_NAME);//app自我设定应为magicPaint
        BluetoothDevice device1=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        device.setDevice(device1);
        device.setDeviceName(deviceName);
        SearchedDevices.Devices.add(device);
        Log.i("---------------->","find one Device name: "+deviceName);
    }
}
