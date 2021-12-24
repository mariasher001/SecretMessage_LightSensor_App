package hu.unideb.inf.mobilef10.maria.secretmessage_lightsensor_app;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;


public class AccelerometerEventListener implements SensorEventListener {

    private static final float SHAKE_THRESHOLD = 7.55f;
    private static final int MIN_TIMES_IN_BETWEEN_SHAKES = 1000;
    private long mLastShakeTime;
    private long curTime;

    private TextView textView;

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        curTime = System.currentTimeMillis();
        if ((curTime - mLastShakeTime) > MIN_TIMES_IN_BETWEEN_SHAKES) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            double acceleration = Math.sqrt(Math.pow(x, 2) +
                    Math.pow(y, 2) +
                    Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
            Log.d("LMAO", "Acceleration is " + acceleration + "m/s^2");

            if(acceleration>SHAKE_THRESHOLD){
                mLastShakeTime = curTime;
                textView.setTextColor(Color.rgb(255,255,255));
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
