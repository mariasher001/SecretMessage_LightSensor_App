package hu.unideb.inf.mobilef10.maria.secretmessage_lightsensor_app;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.EditText;
import android.widget.TextView;

public class LightSensorEventListener implements SensorEventListener {
    private TextView textView;
    private EditText plainText;

    public void setBg(TextView bg) {
        this.textView = bg;
    }

    public void setText(EditText text) {
        this.plainText = text;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lux = sensorEvent.values[0];
        if( lux<0.5 ) {
            textView.setBackgroundColor(Color.rgb(0,0,0));
            textView.setTextColor(Color.rgb(255,255,255));
        }
        else {
            textView.setBackgroundColor(Color.rgb(255, 255, 255));
            textView.setBackgroundColor(Color.rgb(255, 255, 255));
            plainText.setTextColor(Color.rgb(0,0,0));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
