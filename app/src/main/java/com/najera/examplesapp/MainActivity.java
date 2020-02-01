package com.najera.examplesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch switchDarkMode;
    private Button showIntroButton;
    private SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = new SharedPrefs(this);

        if (prefs.loadNightModeState()){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switchDarkMode = findViewById(R.id.switchNightMode);
        showIntroButton = findViewById(R.id.showIntroButton);

        if (prefs.loadNightModeState()){
            switchDarkMode.setChecked(true);
        }

        switchChangeListener();
        buttonClickListener();

    }

    public void switchChangeListener(){
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    prefs.setNightModeState(true);
                    restartApp();
                }
                else {
                    prefs.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }

    public void buttonClickListener(){
        showIntroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIntroOpenedStatus();
                Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void restartApp(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveIntroOpenedStatus(){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editData = preferences.edit();
        editData.putBoolean("isIntroOpened", false);
        editData.apply();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
