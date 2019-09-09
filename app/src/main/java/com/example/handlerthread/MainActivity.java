package com.example.handlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExampleHandlerThread handlerThread = new ExampleHandlerThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerThread.start();
    }

    public void doWork(View view){
        Message message = Message.obtain();
        message.what = 1;
        message.arg1 = 76;
        message.obj = "My object";

        handlerThread.getHandler().sendMessage(message);

        handlerThread.getHandler().sendEmptyMessage(1);

        Message message1 = Message.obtain(handlerThread.getHandler());
        message.what = 1;
        message.arg1 = 23;
        message.obj = "My object2";
        message1.sendToTarget();


        //System clock uptime millis means that it would be delayed for some seconds(here 2) after
        // System clock have been initialized
//        handlerThread.getHandler().postDelayed(new ExampleRunnable(), SystemClock.uptimeMillis() + 2000);
//        handlerThread.getHandler().post(new ExampleRunnable());
//        handlerThread.getHandler().postAtFrontOfQueue(new ExampleRunnable2());
    }

    public void removeMessages(View view){
        handlerThread.getHandler().removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class ExampleRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "Runnable1: " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    static class ExampleRunnable2 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "Runnable2: " + i);
                SystemClock.sleep(1000);
            }
        }
    }
}
