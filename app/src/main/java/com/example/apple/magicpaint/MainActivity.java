package com.example.apple.magicpaint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.example.apple.magic.R;
import com.magicpaint.services.*;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class MainActivity extends Activity {

public static DrawView drawView;

    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            if(msg.arg1==0x31)
            {
                drawView.invalidate();
            }
        }

    };
    TimerTask task = new TimerTask() {
        public void run() {

            Message msg=new Message();
            msg.arg1=0x31;
            handler.sendMessage(msg);


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView=(DrawView)findViewById(R.id.draw);
        Intent intent=new Intent(MainActivity.this,BluetoothStart.class);
        startService(intent);
        Timer timer=new Timer(true);
        timer.schedule(task,1000,10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
