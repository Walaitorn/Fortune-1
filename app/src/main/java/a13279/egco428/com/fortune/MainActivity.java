package a13279.egco428.com.fortune;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    private FortuneDataSource dataSource;
    private ArrayAdapter<FortuneMessage> fortuneArrayAdapter;


    public static final int DETAIL_REQ_CODE = 1001;
    protected List<FortuneMessage> values;

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSource = new FortuneDataSource(this);
        dataSource.open();
        values = dataSource.getAllComments();
        fortuneArrayAdapter = new FortuneArrayAdapter(this,0,values);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(fortuneArrayAdapter); //push data in adapter into listview


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view,final int position, long l) {

                if(fortuneArrayAdapter.getCount()>0){

                    final FortuneMessage comment = fortuneArrayAdapter.getItem(position); // delete first item
                    dataSource.deleteComment(comment); // delete in database
                    view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            fortuneArrayAdapter.remove(comment); // delete in listviewlist.remove(item);
                            view.setAlpha(1);
                        }
                    });
                }
            }
        });
        fortuneArrayAdapter.notifyDataSetChanged();

    }


    class FortuneArrayAdapter extends ArrayAdapter<FortuneMessage> {
        Context context;
        List<FortuneMessage> objects;

        public FortuneArrayAdapter(Context context, int resource, List<FortuneMessage> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FortuneMessage fortune = objects.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fortune_item, null);

            TextView txt = (TextView) view.findViewById(R.id.FortuneMsg);
            txt.setText(String.valueOf(fortune.getComment()));
            txt.setTextColor(Color.parseColor(fortune.getColor()));

            TextView txt2 = (TextView) view.findViewById(R.id.Date);
            txt2.setText("Date: "+fortune.getDate());

            String uri = fortune.getPic();
            int res = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            image.setImageResource(res);
            return view;
        }

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

    @Override
    protected void onResume(){
        dataSource.open();
        values = dataSource.getAllComments();
        fortuneArrayAdapter = new FortuneArrayAdapter(this,0,values);
        listView.setAdapter(fortuneArrayAdapter);
        super.onResume();
    }

    @Override
    protected void onPause(){
        dataSource.close();
        super.onPause();
    }

}
