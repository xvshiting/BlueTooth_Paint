package com.example.apple.magicpaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import  com.magicpaint.module.*;

/**
 * Created by apple on 15/1/27.
 */
public class DrawView extends View {

    float preX;
    float preY;
    private Path path=null;
    public Paint paint=null;
    final int VIEW_WIDTH=(int)pixel.pixelx;
    final int VIEW_HEIGHT= (int)pixel.pixely;
    public int number=0;
    // 定义一个内存中的图片，该图片将作为缓冲区
    Bitmap cacheBitmap=null;
    //定义cacheBitmap上的canvas对象
    Canvas cacheCanvas=null;






    public DrawView(Context context, AttributeSet set) {
        super(context, set);
        // TODO Auto-generated constructor stub
        // 创建一个与该view大小相同的缓冲区
        cacheBitmap=Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas=new Canvas();
        path=new Path();
        //设置cacheCanvas将会绘制到内存的cacheBitmap上
        cacheCanvas.setBitmap(cacheBitmap);
        //设置画笔颜色
        paint=new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        //获取拖动事件额发生位置
        float x=event.getX();
        float y=event.getY();
        int action=0;
        pathData pathData=new pathData();
        pathData.setX(x);
        pathData.setY(y);
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                preX=x;
                preY=y;
                action=1;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX, preY, x, y);
                preX=x;
                preY=y;
                action=2;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, paint);
                number++;
                Log.i("----------->", "" + number);
                path.reset();
                action=3;
                break;
        }
        pathData.setPreX(preX);
        pathData.setPreY(preY);
        pathData.setAction(action);
        communication communication=new communication();
        communication.sendMessage(pathData);//发送信息
        invalidate();
        //返回true方法，表明处理方法已经处理该事件
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        Paint bmpPaint=new Paint();
        //将cacheBitmap绘制到该View组件上
        canvas.drawBitmap(cacheBitmap, 0, 0,bmpPaint);
        //沿着path绘制
        canvas.drawPath(path, paint);
        if(getPath.path!=null) {
            if (getPath.actionFlag == pathData.ACTION_UP) {//绘制传过来的path
                cacheCanvas.drawPath(getPath.path, paint);//存入缓冲区
                getPath.actionFlag = 0;//重置
                getPath.path.reset();
            }
            else
            {
                canvas.drawPath(getPath.path, paint);
            }
        }


    }
}