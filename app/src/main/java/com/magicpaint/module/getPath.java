package com.magicpaint.module;

import android.graphics.Path;

/**
 * Created by apple on 15/1/27.
 * parase the data received to path
 * only action=ACTION_UP WE give path a path
 */
public class getPath {
    public static Path path=null;//view can use
    public  Path localPath=null;
    public static int actionFlag=0;
    static {
        path=new Path();
    }
    public void setPath(pathData pathData) {
        if (pathData.getAction() == com.magicpaint.module.pathData.ACTION_DOWN) {
            actionFlag = com.magicpaint.module.pathData.ACTION_DOWN;
            path.moveTo(pathData.getX()*TargetDevice.TransferscaleX, pathData.getY()*TargetDevice.TransferscaleY);

        } else if (pathData.getAction() == com.magicpaint.module.pathData.ACTION_MOVE) {
            actionFlag = com.magicpaint.module.pathData.ACTION_MOVE;
            path.quadTo(pathData.getPreX()*TargetDevice.TransferscaleX, pathData.getPreY()*TargetDevice.TransferscaleY, pathData.getX()*TargetDevice.TransferscaleX, pathData.getY()*TargetDevice.TransferscaleY);
        } else if (pathData.getAction() == com.magicpaint.module.pathData.ACTION_UP) {
            actionFlag = com.magicpaint.module.pathData.ACTION_UP;
        }
        //通知重绘UI
    }
}
