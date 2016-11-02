package a13279.egco428.com.fortune;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class New_Fortune extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private boolean sensor = false;
    private SensorEvent events;
    private long lastUpdate;
    private boolean isPress=false;
    protected List<Fortune> data;
    Button Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__fortune);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Btn = (Button) findViewById(R.id.push_button);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

     public void PressBtn (View v){
        if (!isPress){// && sensor) {
            Btn.setText("Shaking");
            //getAccelerometer(events);
            Btn.setText("Save");
            isPress = true;
        } else {
            Btn.setText("Shake");
            isPress = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            //getAccelerometer(event);
            event = events;
            sensor = true;
        }
    }

    private void getAccelerometer(SensorEvent event){
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];
        int count=0;


        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH); //distance between 2 point
        long actualTime = System.currentTimeMillis();

        while (count<4)
        if (accelationSquareRoot >= 4) //
        {
//            if (actualTime - lastUpdate < 200) {
//                return;
//            }
//            lastUpdate = actualTime;
            count++;

        }
        Random aa = new Random();
        int ran = aa.nextInt(5);
        data = DataProvider.getData();
        Fortune course = data.get(ran);

        TextView tet = (TextView)findViewById(R.id.fortuneMsg);
        tet.setText("Result:"+course.getFortunemsg());

        Calendar cal = Calendar.getInstance();
        String d = cal.getTime().toString();

        TextView tet2 = (TextView)findViewById(R.id.fortuneDate);
        tet2.setText("Date: "+ d );

    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
