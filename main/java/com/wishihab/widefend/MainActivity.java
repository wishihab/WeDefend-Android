package com.wishihab.widefend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if ( id == android.R.id.home ) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_device) {
            Intent intent = new Intent(this, Device.class);
            startActivity(intent);

        } else if (id == R.id.nav_scan) {
            Intent intent = new Intent(this, Scan.class);
            startActivity(intent);

        } else if (id == R.id.nav_listapp) {
            Intent intent = new Intent(this, Listapp.class);
            startActivity(intent);

        }else if (id == R.id.nav_permission) {
            Intent intent = new Intent(this, PermissionList.class);
            startActivity(intent);

        } else if (id == R.id.nav_network) {
            Intent intent = new Intent(this, Network.class);
            startActivity(intent);

        }else if (id == R.id.nav_findingip) {
            Intent intent = new Intent(this, FindingIP.class);
            startActivity(intent);


        } else if (id == R.id.nav_folderlock) {
            Intent intent = new Intent(this, Encryption.class);
            startActivity(intent);


        }else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);

        }else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareDes = "WIDefend - App to monitoring your device and protect from trojan. Download github.com/wishihab";
            intent.putExtra(Intent.EXTRA_TEXT,shareDes);
            startActivity(Intent.createChooser(intent, "Share with"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
