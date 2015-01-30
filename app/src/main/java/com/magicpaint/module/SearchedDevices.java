package com.magicpaint.module;

import java.util.ArrayList;

/**
 * Created by apple on 15/1/27.
 * this class just support to sava the searched devices info
 * Devices is a type of ArrayList  and Static
 * we can  access Devices via SearchedDevices.Devices
 */
public class SearchedDevices {
   public static ArrayList<bluetoothDevice>  Devices;
    static
    {
        Devices=new ArrayList<bluetoothDevice>();
    }
}
