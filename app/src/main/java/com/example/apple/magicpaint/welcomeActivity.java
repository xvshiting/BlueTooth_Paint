package com.example.apple.magicpaint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.apple.magic.R;
import com.magicpaint.module.pixel;


public class welcomeActivity extends Activity {
     int images[]={R.drawable.welcome1,R.drawable.welcome2};
    private ImageView welcomeImage;
    private DisplayMetrics dm=new DisplayMetrics();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        welcomeImage=(ImageView)findViewById(R.id.welcomeImage);
        welcomeImage.setBackgroundResource(images[1]);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        pixel.pixelx=dm.widthPixels;
        pixel.pixely=(int)(dm.heightPixels*0.8);
        startActivity(new Intent(welcomeActivity.this,MainActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
