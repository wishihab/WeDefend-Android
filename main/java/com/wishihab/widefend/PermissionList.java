package com.wishihab.widefend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PermissionList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String permissiondefault = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbedapp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Spinner cSpinner = (Spinner)findViewById(R.id.spinner);

        cSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                permissiondefault = cSpinner.getSelectedItem().toString();
                if (position==0) {
                    permissiondefault = Manifest.permission.INTERNET;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==1){
                    permissiondefault = Manifest.permission.READ_EXTERNAL_STORAGE;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==2){
                    permissiondefault = Manifest.permission.CAMERA;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==3){
                    permissiondefault = Manifest.permission.RECORD_AUDIO;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==4){
                    permissiondefault = Manifest.permission.ACCESS_COARSE_LOCATION;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==5){
                    permissiondefault = Manifest.permission.READ_SMS;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==6){
                    permissiondefault = Manifest.permission.READ_CONTACTS;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==7){
                    permissiondefault = Manifest.permission.CALL_PHONE;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==8){
                    permissiondefault = Manifest.permission.READ_LOGS;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==9){
                    permissiondefault = Manifest.permission.BIND_DEVICE_ADMIN;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }else if(position==10){
                    permissiondefault = Manifest.permission.REBOOT;
                    ListView userInstalledApps = (ListView)findViewById(R.id.lists);
                    List<AppList> installedApps = getInstalledApps();
                    AppAdapter installedAppAdapter = new AppAdapter(PermissionList.this, installedApps);
                    userInstalledApps.setAdapter(installedAppAdapter);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private List<AppList> getInstalledApps() {

        PackageManager p = getPackageManager();
        List<AppList> res = new ArrayList<AppList>();
        final List<PackageInfo> apps = p.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (PackageInfo packageInfo : apps) {
            boolean bc = isSystemPackage(packageInfo);
            if (packageInfo.requestedPermissions == null)
                continue;
            for (String permission : packageInfo.requestedPermissions) {

                if (TextUtils.equals(permission, permissiondefault)) {
                    if(!bc){
                        String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                        Drawable icon = packageInfo.applicationInfo.loadIcon(getPackageManager());

                        res.add(new AppList(appName, icon));
                    }
                }
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_device) {
            Intent intent = new Intent(this, Device.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (id == R.id.nav_scan) {
            Intent intent = new Intent(this, Scan.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (id == R.id.nav_listapp) {
            Intent intent = new Intent(this, Listapp.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (id == R.id.nav_network) {
            Intent intent = new Intent(this, Network.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }else if (id == R.id.nav_findingip) {
            Intent intent = new Intent(this, FindingIP.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        } else if (id == R.id.nav_folderlock) {
            Intent intent = new Intent(this, Encryption.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, About.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
