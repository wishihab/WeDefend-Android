package com.wishihab.widefend;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wishihab on 09/4/2018.
 * AESEncryption https://github.com/wishihab/WiDefend/blob/master/widefend_v1.2/src/widefend/E.java
 * Picker https://github.com/nbsp-team/MaterialFilePicker
 */

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;


public class Encryption extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    TextView edthfile;
    EditText edittex;
    String filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button b1 = (Button) findViewById(R.id.but_findfile);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);


        //find
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                checkPermissionsAndOpenFilePicker();
            }
        });


        //encrypt
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    CipherOutputStream cos;
                    int readbyte;
                    edittex = (EditText)findViewById(R.id.editText_ex);
                    byte[] buf = new byte[1024];
                    FileInputStream inFile = new FileInputStream(edthfile.getText().toString());
                    FileOutputStream outFile = new FileOutputStream(edthfile.getText().toString()+edittex.getText().toString());

                    byte k[]="Wif3NDeRCr3ptErS".getBytes();
                    SecretKeySpec key = new SecretKeySpec(k, "AES");
                    Cipher enc = Cipher.getInstance("AES");
                    enc.init(Cipher.ENCRYPT_MODE, key);
                    cos = new CipherOutputStream(outFile, enc);
                    // read byte from original file
                    byte[] getBytes = {};
                    try{
                        File file = new File(edthfile.getText().toString());
                        getBytes = new byte[(int) file.length()];
                        InputStream is = new FileInputStream(file);
                        is.read(getBytes);
                        is.close();

                        while((readbyte = inFile.read(buf)) != -1){
                            byte[] output = enc.update(buf, 0, readbyte);
                            if (output != null){
                                outFile.write(output);
                            }
                        }
                        byte[] output = enc.doFinal();
                        if (output != null){
                            cos.write(output);
                        }
                        outFile.flush();
                        outFile.close();
                        Toast.makeText(Encryption.this, "Encryption Success!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }catch (Exception e){
                    e.printStackTrace();

                }
            }

        });
        //decrypt
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    CipherOutputStream cos;
                    int readbyte;
                    edittex = (EditText)findViewById(R.id.editText_ex);

                    byte[] buf = new byte[1024];
                    FileInputStream inFile = new FileInputStream(edthfile.getText().toString());
                    FileOutputStream outFile = new FileOutputStream(edthfile.getText().toString()+edittex.getText().toString());

                    byte k[]="Wif3NDeRCr3ptErS".getBytes();
                    SecretKeySpec key = new SecretKeySpec(k, "AES");
                    Cipher enc = Cipher.getInstance("AES");
                    enc.init(Cipher.DECRYPT_MODE, key);
                    cos = new CipherOutputStream(outFile, enc);
                    // read byte from original file
                    byte[] getBytes = {};
                    try{
                        File file = new File(edthfile.getText().toString());
                        getBytes = new byte[(int) file.length()];
                        InputStream is = new FileInputStream(file);
                        is.read(getBytes);
                        //if file bigger make here
                        is.close();

                        while((readbyte = inFile.read(buf)) != -1){
                            byte[] output = enc.update(buf, 0, readbyte);
                            if (output != null){
                                outFile.write(output);
                            }
                        }
                        byte[] output = enc.doFinal();
                        if (output != null){
                            cos.write(output);
                        }
                        Toast.makeText(Encryption.this, "Decryption Success!", Toast.LENGTH_SHORT).show();
                        outFile.flush();
                        outFile.close();


                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }catch (Exception e){
                    e.printStackTrace();

                }

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
    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withTitle("Choose a file")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = resultData.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            // Do anything with file
            edthfile = (TextView) findViewById(R.id.text_pathfile);
            edthfile.setText(filepath);

        }
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
        }else if (id == R.id.nav_permission) {
            Intent intent = new Intent(this, PermissionList.class);
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
