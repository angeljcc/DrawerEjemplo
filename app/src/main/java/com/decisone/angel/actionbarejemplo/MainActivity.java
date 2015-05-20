package com.decisone.angel.actionbarejemplo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    int ICONS [] = {R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action};

    String NAME = "Angel Jimenez de Cisneros";
    String EMAIL = "angeljcc@gmail.com";
    int PROFILE = R.drawable.descarga;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        toolbar = (Toolbar)findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setElevation((float) 20.0);
        //getSupportActionBar().setIcon(R.drawable.icono2);
        //getSupportActionBar().setLogo(R.drawable.icono);
        getSupportActionBar().addOnMenuVisibilityListener(new android.support.v7.app.ActionBar.OnMenuVisibilityListener() {
            @Override
            public void onMenuVisibilityChanged(boolean b) {

            }
        });
        getSupportActionBar().setShowHideAnimationEnabled(false);
        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        //mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);
        mAdapter = new Adaptador(TITLES,ICONS,NAME,EMAIL,PROFILE);
        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && mGestureDetector.onTouchEvent(e) && rv.getChildPosition(child)!=0){
                    Drawer.closeDrawers();
                    Toast.makeText(MainActivity.this, "The Item Clicked is: " + rv.getChildPosition(child), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout)findViewById(R.id.DrawerLayout);
        mDrawerToogle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle(getResources().getString(R.id.menu));

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle("probando");
                invalidateOptionsMenu();
            }
        };
        mDrawerToogle.setDrawerIndicatorEnabled(true);


        Drawer.setDrawerListener(mDrawerToogle);
        mDrawerToogle.syncState();
        



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
        if (id == R.id.action_settings) {


            return true;
        }
        if(id == R.id.compartir){
            return true;
        }
        if(id == R.id.buscar){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
