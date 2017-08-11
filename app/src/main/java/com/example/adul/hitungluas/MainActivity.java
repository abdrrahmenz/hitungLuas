package com.example.adul.hitungluas;

import android.content.Intent;
import android.net.Uri;
//import android.support.design.widget.TabLayout;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adul.hitungluas.adapter.TabFragmentPagerAdapter;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener {
    /*HitungLuas
    private EditText edtPanjang, edtLebar;
    private Button btnHitung;
    private TextView txtLuas;*/

    /*Sample_Intent
    private Button btnSub1, btnSub2, btnDial;
    private String strIntent;
    private EditText txtIntent;*/

    /*Tab_Fragment
    //deklarasi semua komponen View yang akan digunakan
    private Toolbar toolbar;
    private ViewPager pager;
    private TabLayout tabs;*/

    /*ListView_Example
    private ListView lvItem;
    private String[] footballClubs = new String[]{
            "Juventus", "Manchester United", "Liverpool",
            "Bayern Munchen", "Real Madrid", "Ajax Amsterdam",
            "Barcelona"
    };*/

    private LoadMoreListView lvItem;
    private SwipeRefreshLayout swipeMain;
    private LinkedList<String> list;
    private ArrayAdapter<String> adapter;
    private int MaxPage = 5;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setTitle("Material Tab");

        lvItem = (LoadMoreListView)findViewById(R.id.lv_item);
        swipeMain = (SwipeRefreshLayout)findViewById(R.id.swipe_main);
        list = new LinkedList<>();

        populateDefaultData();

        //listener untuk loadmore
        lvItem.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (currentPage < MaxPage) {
                    new FakeLoadmoreAsync().execute();
                } else {
                    lvItem.onLoadMoreComplete();
                }
            }
        });

        swipeMain.setOnRefreshListener(this);

        /*ListVIew_Example
        lvItem = (ListView)findViewById(R.id.lv_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, footballClubs);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Kamu klik : "+footballClubs[position], Toast.LENGTH_LONG).show();
            }
        });*/

        /*FlexibleUI
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frame_container,
                            new FirstFragment(),
                            FirstFragment.class.getSimpleName()).commit();
        }*/

        /*Tab_Fragment
        //set up toolbar
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Material Tab");

        //inisialisasi tab dan pager
        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        //set object adapter kedalam ViewPager
        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));

        //Manimpilasi sedikit untuk set TextColor pada Tab
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));

        //set tab ke ViewPager
        tabs.setupWithViewPager(pager);

        //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);*/

    /*Sample_Intent
        btnSub1 = (Button)findViewById(R.id.btn_activity_sub_1);
        btnSub2 = (Button)findViewById(R.id.btn_activity_sub_2);
        btnDial = (Button)findViewById(R.id.btn_activity_dial);

        txtIntent = (EditText)findViewById(R.id.text_Intent);

        btnSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intent);
            }
        });

        btnSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strIntent = txtIntent.getText().toString();
                Intent intent = new Intent(MainActivity.this, Sub2Activity.class);
                intent.putExtra(Sub2Activity.KEY_DATA, strIntent);
                startActivityForResult(intent, 0);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:089634518222"));
                startActivity(intent);
            }
        });*/

        /*HitungLuas
        edtPanjang = (EditText)findViewById(R.id.edt_panjang);
        edtLebar = (EditText)findViewById(R.id.edt_lebar);
        btnHitung = (Button)findViewById(R.id.btn_hitung);
        txtLuas = (TextView)findViewById(R.id.txt_luas);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtPanjang.getText()) || TextUtils.isEmpty(edtLebar.getText())){
                    Toast.makeText(getApplicationContext(),"Nilai yang dimasukkan tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Do Something
                    try{
                        String panjang = edtPanjang.getText().toString().trim();
                        String lebar = edtLebar.getText().toString().trim();
                        double p = Double.valueOf(panjang);
                        double l = Double.valueOf(lebar);
                        double luas = p * l;
                        txtLuas.setText("Luas : "+luas);
                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/
    }

    private void populateDefaultData(){
        String[] androidVersion = new String[]{
                "Not Apple", "Not Blackberry",
                "Cupcake", "Donut",
                "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice cream sandwich",
                "Jelly Bean", "Kitkat", "Lollipop",
                "M Preview", "N (Coming soon on 2016)"
        };

        for (int i = 0; i < androidVersion.length; i++){
            list.add(androidVersion[i]);
        }

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, list);
        lvItem.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        new FakePullRefreshAsync().execute();
    }

    //Background proses palsu untuk melakukan proses delay dan append data di bagian bawah list
    private class FakeLoadmoreAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(4000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateLoadmoreData();
            currentPage +=1;
        }
    }

    private void populateLoadmoreData() {
        String loadmoreText = "Added on loadmore";
        for (int i = 0; i < 10; i++){
            list.addLast(loadmoreText);
        }
        adapter.notifyDataSetChanged();
        lvItem.onLoadMoreComplete();
        lvItem.setSelection(list.size() - 11);
    }

    //Background proses palsu untuk melakukan proses delay dan append data di bagian atas list
    private class FakePullRefreshAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(4000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populatePullRefreshData();
        }
    }

    private void populatePullRefreshData() {
        String swipePullRefreshText = "Added after swipe layout";
        for (int i = 0; i < 5; i++){
            list.addFirst(swipePullRefreshText);
        }
        swipeMain.setRefreshing(false);
        adapter.notifyDataSetChanged();
        lvItem.setSelection(0);

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
        if (id == R.id.action_bookmark) {
            Toast.makeText(this, "Bookmarked!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
