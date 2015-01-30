package com.magicpaint.module;

import android.util.Log;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by apple on 15/1/27.
 */
public class communication {

    public void sendMessage(pathData pathData)//在画图view中被调用
    {
      //发送path信息
        try
        {
            ObjectOutputStream outputStream=new ObjectOutputStream(TargetDevice.TransferSocket.getOutputStream());
            outputStream.writeObject(pathData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void listenMessage()//
    {
        pathData pathData=new pathData();
        Log.i("----listenMessage----------->","gotPath");
        //接收path信息
        try{
            ObjectInputStream inputStream=new ObjectInputStream(TargetDevice.TransferSocket.getInputStream());
            pathData=(pathData)inputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        getPath path=new getPath();
        if (pathData!=null) {
            path.setPath(pathData);
        }

    }
    public  void listenforPixel()
    {
         pixel pixel=new pixel();
        try{
            ObjectInputStream inputStream=new ObjectInputStream(TargetDevice.TransferSocket.getInputStream());
             pixel=(pixel)inputStream.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(pixel.getX()>=1&&pixel.getY()>=1) {
            TargetDevice.TransferscaleX = pixel.pixelx / pixel.getX();
            TargetDevice.TransferscaleY = pixel.pixely / pixel.getY();
        }
        Log.i("---------TargetDevice.TransferscaleX",""+TargetDevice.TransferscaleX);
    }
    public  void sendforPixel()
    {
        pixel pixel=new pixel();
        pixel.setX(pixel.pixelx);
        pixel.setY(pixel.pixely);
        try
        {
            ObjectOutputStream outputStream=new ObjectOutputStream(TargetDevice.TransferSocket.getOutputStream());
            outputStream.writeObject(pixel);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
