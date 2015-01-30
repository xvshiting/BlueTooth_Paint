package com.magicpaint.module;

/**
 * Created by apple on 15/1/27.
 * this class just declaration device info
 * DeviceName is important  ,this let,s have an able to  fingure out
 * which devices(running our app) we want to attach
 */
import android.bluetooth.BluetoothDevice;
public class bluetoothDevice {
    private BluetoothDevice Device;//连接所用
    private String DeviceName;//分辨目标机标志
    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }
    public BluetoothDevice getDevice() {
        return Device;
    }

    public void setDevice(BluetoothDevice device) {
        Device = device;
    }




}
