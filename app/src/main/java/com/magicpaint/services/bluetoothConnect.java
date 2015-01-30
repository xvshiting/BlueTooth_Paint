package com.magicpaint.services;

import android.app.Service;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.magicpaint.*;
import com.magicpaint.module.TargetDevice;
import com.magicpaint.module.communication;

import java.io.IOException;
import java.util.UUID;

public class bluetoothConnect extends Service {
    public bluetoothConnect() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("bluetoothConnect------------>","oncreat()");
        if (!TargetDevice.isconnected) {//未作为服务器被连接上
            connectToServer();//作为客户端发出请求
        }

    }

    public  void connectToServer()
    {
        UUID uuid=UUID.fromString("a60f35f0-b93a-11de-8a39-08002009c666");
        final communication communication=new communication();
        try{
            final BluetoothSocket clientSocket= TargetDevice.targetDevice.getDevice().createRfcommSocketToServiceRecord(uuid);
            final Thread connectthread=new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        Log.i("------------>", "waitforconnect  now be client");
                        clientSocket.connect();
                        Log.i("connectToServerSocket------------>", "OK connected! so  now  we be client");
                        TargetDevice.TransferSocket=clientSocket;
                        communication.sendforPixel();
                        communication.listenforPixel();//去做屏幕缩放
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
            Thread judge=new Thread(new Runnable() {//何时结束作为客户端的请求
                @Override
                public void run() {
                    while(!TargetDevice.isconnected)
                    {

                    }

                    connectthread.stop();
                    Log.i("judge--------------->","client over  so now we be server");
                }
            });
            connectthread.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
