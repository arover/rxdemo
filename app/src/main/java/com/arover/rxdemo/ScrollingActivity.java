package com.arover.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    private static final int DEMO_CONCAT = 0;
    private static final int DEMO_INTERVAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView demoList = (RecyclerView) findViewById(R.id.demo_list);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                view ->
                        Snackbar.make(view, "Replace with your own action",
                                Snackbar.LENGTH_LONG)
                                .show());

        demoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DemoAdapter demoAdapter = new DemoAdapter(this);
        demoAdapter.getList().add(new DemoAdapter.Item(DEMO_CONCAT, "Concat"));
        demoAdapter.getList().add(new DemoAdapter.Item(DEMO_INTERVAL, "Interval"));
        demoAdapter.setOnItemClickListener(view -> {

            DemoAdapter.Item item = (DemoAdapter.Item) view.getTag();
            showDemo(item);
        });
        demoList.setAdapter(demoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDemo(DemoAdapter.Item item) {
        Intent intent = null;

        switch (item.id) {
            case DEMO_CONCAT:
                intent = new Intent(this, RxConcatActivity.class);
                break;
            case DEMO_INTERVAL:
                intent = new Intent(this, RxIntervalActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}

