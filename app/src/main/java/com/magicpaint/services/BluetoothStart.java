package com.magicpaint.services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import com.magicpaint.module.*;
import com.magicpaint.module.TargetDevice;
import com.magicpaint.receiver.*;

import java.io.IOException;
import java.util.UUID;

public class BluetoothStart extends Service {
    public BluetoothStart() {
    }
    public static BluetoothAdapter blutooth;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("--------------->","oncreat()");
        BluetoothAdapter blutooth=BluetoothAdapter.getDefaultAdapter();
        this.blutooth=blutooth;
        initBlueTooth();//打开蓝牙，并启动可检查模式
        this.blutooth.setName("magicPaint");//分辨目标机标志
        registerBR();
        if(this.blutooth.isEnabled())
        {
            this.blutooth.startDiscovery();//开始搜索
        }
      //be server
       runAsserver();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("--------------->","onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("--------------->","onBind");
        throw new UnsupportedOperationException("Not yet implemented");

    }
    public  void initBlueTooth()
    {
        while(!this.blutooth.isEnabled())
        {
            this.blutooth.enable();
        }

        while(this.blutooth.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
        {
            //已经开启可被查找模式
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
    public void registerBR()
    {
        registerReceiver(new discoverMonitor(),new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED));
        registerReceiver(new discoverMonitor(),new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
        registerReceiver(new discoverResult(),new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }
    public void runAsserver()
    {
        UUID uuid=UUID.fromString("a60f35f0-b93a-11de-8a39-08002009c666");
        String name="magicPaint";
        final communication communication=new communication();
        try{
            final BluetoothServerSocket btserver=blutooth.listenUsingRfcommWithServiceRecord(name, uuid);
            Thread acceptTread=new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    BluetoothSocket serverSocket;
                    try {
                        Log.i("------------>", "waitforconnect  now be Server");
                        serverSocket = btserver.accept();
                        TargetDevice.isconnected=true;//重要 这个值的改变 结束了本身成为客户端请求
                        Log.i("connectToServerSocket------------>", "OK connected");
                        TargetDevice.TransferSocket=serverSocket;
                        communication.listenforPixel();
                        communication.sendforPixel();
                        //去做屏幕缩放
                        while(true)
                        {
                            communication.listenMessage();
                        }

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });
            acceptTread.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
