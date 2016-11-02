package a13279.egco428.com.fortune;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String Title = "Course Title";
    public static final String Date = "Course Description";
    public static final String pic = "courseNumber";

    public static final int DETAIL_REQ_CODE = 1001;
    protected List<Fortune> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = DataProvider.getData();
        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<Fortune> fortuneArrayAdapter = new FortuneArrayAdapter(this,0,data);
        listView.setAdapter(fortuneArrayAdapter); //push data in adapter into listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fortune course = data.get(position);
                displayDetail(course);
            }
        });

    }

    class FortuneArrayAdapter extends ArrayAdapter<Fortune> {
        Context context;
        List<Fortune> objects;

        public FortuneArrayAdapter(Context context, int resource, List<Fortune> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Fortune fortune = objects.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fortune_item, null);

            TextView txt = (TextView) view.findViewById(R.id.FortuneMsg);
            txt.setText(String.valueOf(fortune.getFortunemsg()));

            TextView txt2 = (TextView) view.findViewById(R.id.Date);
            txt2.setText("Date: "+fortune.getDate());

            String uri = fortune.getFortunepic();
            int res = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            image.setImageResource(res);
            return view;
        }

    }

    private void displayDetail(Fortune course){

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
        if (id == R.id.action_add) {
            startActivity(new Intent(MainActivity.this, New_Fortune.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
