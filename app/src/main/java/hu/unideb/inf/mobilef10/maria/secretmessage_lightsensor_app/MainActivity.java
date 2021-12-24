package hu.unideb.inf.mobilef10.maria.secretmessage_lightsensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private EditText plainText;
    private TextView textView;
    private Layout constraintLayout;

    private SensorManager sensorManager;
    private Sensor Light_Sensor;
    private Sensor Acceleration_Sensor;
    private LightSensorEventListener Lsel = new LightSensorEventListener();
    private AccelerometerEventListener AEL = new AccelerometerEventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking the components used in xml with main
        plainText = findViewById(R.id.plain_text);
        textView = findViewById(R.id.textView);


        //creating a sensor-manager to manage sensors
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Light_Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Acceleration_Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Lsel.setBg(textView);
        Lsel.setText(plainText);
        AEL.setTextView(textView);
        //plainText.setVisibility(View.INVISIBLE);
        sensorManager.registerListener(Lsel,Light_Sensor,SensorManager.SENSOR_DELAY_NORMAL);
        //registering the AEL
        if(Acceleration_Sensor != null){
            sensorManager.registerListener(AEL,Acceleration_Sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void MessageButtonClicked(View view) {
        textView.append(plainText.getText().toString()+"\n");
        textView.setTextColor(Color.rgb(0,0,0));
        plainText.setText("");
        Toast.makeText(MainActivity.this, "Shake to make text disappear", Toast.LENGTH_LONG).show();
    }
    public void ResetButtonClicked(View view) {
        textView.setText("");
        textView.setTextColor(Color.rgb(0,0,0));
        plainText.setText("");
        //plainText.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(Lsel);
        sensorManager.unregisterListener(AEL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(Lsel,Light_Sensor,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(AEL,Acceleration_Sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


}