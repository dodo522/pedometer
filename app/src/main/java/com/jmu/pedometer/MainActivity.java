package com.jmu.pedometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "fff";
    private TextView mStep;//显示步数
    private SensorManager mSensorManager;//管理器
    private Sensor mStepCounter;//传感器
    private float mNum = 0;//步数

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    long sysTime = System.currentTimeMillis();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //管理器实例
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.i(TAG,"Sensor size:"+sensorList.size());
        for(Sensor sensor : sensorList){
            Log.i(TAG,"Support Sensor:"+sensor.getName());
        }

        mStep = (TextView)findViewById(R.id.step);

        //获取计步器Sensor
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(mStepCounter != null){
            mSensorManager.registerListener((SensorEventListener) this,mStepCounter,mSensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Log.e(TAG,"no step counter sensor found");
        }

       // new TimeThread().start();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        mNum = event.values[0];
        mStep.setText(String.valueOf((int)mNum));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


