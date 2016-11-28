package com.example.com.project;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class CLoading extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        /* 네비게이션 드로어 초기화 */
        SetNav();
    }

     public void onClickButton(View v)
     {
         String nav_char = "";
         IntentFilter filter = new IntentFilter();

         switch (v.getId())
         {
             case R.id.btn_pro:
                 Intent intent = new Intent(getApplicationContext(), CViewpager_pro.class);
                 nav_char = "정보처리";
                 intent.putExtra("name",nav_char);
                 startActivity(intent);
                 break;
         }
     }

    private void SetNav()
    {
        /**/
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        String nav_char = "";
        IntentFilter filter = new IntentFilter();

        if(id == R.id.nav_listview_pro)
        {
            Intent intent = new Intent(CLoading.this, CViewpager_pro.class);
            nav_char = "정보처리";
            intent.putExtra("name",nav_char);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}