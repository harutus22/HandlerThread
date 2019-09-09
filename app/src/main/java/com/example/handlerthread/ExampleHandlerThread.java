package com.example.handlerthread;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

public class ExampleHandlerThread extends HandlerThread {
    private static final String TAG = "ExampleHandlerThread";
    public static final int EXAMPLE_TASK = 1;
    private Handler handler;
    public static final int EXAMPLE_TASK2 = 2;

    public ExampleHandlerThread() {

        super("MyExampleThread", Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case EXAMPLE_TASK:
                        Log.d(TAG, "handleMessage: " + "arg1: " + msg.arg1 + " object: " + msg.obj);
                        for (int i = 0; i < 4 ; i++) {
                            Log.d(TAG, "handleMessage: " + i);
                            SystemClock.sleep(1000);
                        }
                        break;
                    case EXAMPLE_TASK2:
                        showMessage(msg);
                        break;
                }
            }
        };
    }

    private void showMessage(Message msg){
        Log.d(TAG, "handleMessage: " + "arg1: " + msg.arg1 + " object: " + msg.obj);
        for (int i = 0; i < 4 ; i++) {
            Log.d(TAG, "handleMessage: " + i);
            SystemClock.sleep(1000);
        }
    }

    public Handler getHandler() {
        return handler;
    }
}
