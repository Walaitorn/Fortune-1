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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class New_Fortune extends AppCompatActivity {//implements SensorEventListener {

    //private SensorManager sensorManager;
    //private boolean sensor = false;
    //private SensorEvent events;
    //private long lastUpdate;
    private boolean isPress=false;
    protected List<Fortune> data;
    TextView tet;
    ImageView cookie;
    TextView tet2;
    private String Msg;
    private String Date;
    private String color;
    private String img;
    private FortuneDataSource dataSource;
    private int countClick = 0;


    Button Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__fortune);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Btn = (Button) findViewById(R.id.push_button);
        tet = (TextView) findViewById(R.id.fortuneMsg);
        cookie = (ImageView)findViewById(R.id.imageView2);
        tet2 = (TextView)findViewById(R.id.fortuneDate);

        dataSource = new FortuneDataSource(this);
        dataSource.open();

        //sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //lastUpdate = System.currentTimeMillis();

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
        if (!isPress) {// && sensor) {
            Btn.setText("Save");
            randomCookies();
            isPress = true;
            countClick++;

        } else if(isPress && countClick==1 ) {
            pressSave();
            //Btn.setText("Shake");
            //isPress = false;
            countClick++;
        }

    }

//    @Override
//    public void onSensorChanged(SensorEvent event){
//        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//            //getAccelerometer(event);
//            event = events;
//            sensor = true;
//        }
//    }

    private void randomCookies(){

        Random aa = new Random();
        int ran = aa.nextInt(5);
        data = DataProvider.getData();
        Fortune course = data.get(ran);

        if (ran==3) {
            tet.setTextColor(Color.parseColor("#FF5722"));
            color = "#FF5722";
        }
        else {
            tet.setTextColor(Color.parseColor("#3F51B5"));
            color = "#3F51B5";
        }

        Msg = course.getFortunemsg();
        tet.setText("Result:  " + Msg);

        String uri = course.getFortunepic();
        int res = this.getResources().getIdentifier(uri, "drawable", this.getPackageName());
        cookie.setImageResource(res);
        img = uri;

        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();

        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        String localTime = date.format(currentLocalTime);

        Date = "Date: "+ localTime;
        tet2.setText(Date);

    }

    public void pressSave(){

        FortuneMessage comment = null;
        comment = dataSource.createFortune(Msg,Date,img,color);

    }
//    private void getAccelerometer(SensorEvent event){
//        float[] values = event.values;
//        // Movement
//        float x = values[0];
//        float y = values[1];
//        float z = values[2];
//        int count=0;
//
//
//        float accelationSquareRoot = (x * x + y * y + z * z)
//                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH); //distance between 2 point
//        long actualTime = System.currentTimeMillis();
//
//        while (count<4)
//        if (accelationSquareRoot >= 4) //
//        {
//            if (actualTime - lastUpdate < 500) {
//                return;
//            }
//            lastUpdate = actualTime;
//            count++;
//        }
//        Random aa = new Random();
//        int ran = aa.nextInt(5);
//        data = DataProvider.getData();
//        Fortune course = data.get(ran);
//
//        TextView tet = (TextView)findViewById(R.id.fortuneMsg);
//        tet.setText("Result:"+course.getFortunemsg());
//
//        Calendar cal = Calendar.getInstance();
//        String d = cal.getTime().toString();
//
//        TextView tet2 = (TextView)findViewById(R.id.fortuneDate);
//        tet2.setText("Date: "+ d );
//
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor,int accuracy){
//
//    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
//    }

}
