package com.najera.examplesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    //Declare views
    FloatingActionButton nextFab, getStartedFab;
    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private List<IntroScreenItem> pagesForIntroList;
    private TabLayout tabIndicator;

    //Declare variables
    private int position = 0;
    private Animation animationFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DarkTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (restorePrefsData()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        //init views
        screenPager = findViewById(R.id.introViewPager);
        tabIndicator = findViewById(R.id.tabIndicator);
        nextFab = findViewById(R.id.intro_fab_next);
        getStartedFab = findViewById(R.id.intro_fab_getStarted);
        animationFab = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.intro_button_animation);

        //test
        animationFab.setDuration(350);

        //fill list intros
        fillPagesForIntroList();

        //setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(this, pagesForIntroList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setting up tabIndicator with viewPager
        tabIndicator.setupWithViewPager(screenPager);

        //onClick fab next
        nextFabClick();

        //onClick fab getStarted
        getStartedFabClick();

        //tabIndicator change listener
        tabIndicatorChangeListener();

    }

    //Fill the intro list with the intros needed
    private void fillPagesForIntroList(){

        pagesForIntroList = new ArrayList<>();

        pagesForIntroList.add(new IntroScreenItem(":)", getString(R.string.lorem_example), R.drawable.intro_img1));
        pagesForIntroList.add(new IntroScreenItem("Hi Motherfucker", getString(R.string.lorem_example), R.drawable.intro_img2));
        pagesForIntroList.add(new IntroScreenItem("Its britney bitch", getString(R.string.lorem_example), R.drawable.intro_img3));

    }

    //Show the getStarted button, hide the indicator tab and the next button
    private void loadLastScreen() {

        nextFab.hide();
        tabIndicator.setVisibility(View.INVISIBLE);
        getStartedFab.show();
        getStartedFab.setAnimation(animationFab);

    }

    //Identify a change in the tabIndicator, show the lastScreen when the intro is finished
    private void tabIndicatorChangeListener(){

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == pagesForIntroList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //next fab click listener. change the page of the intro in every click until the pages end.
    private void nextFabClick(){

        nextFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenPager.getCurrentItem();

                if(position < pagesForIntroList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == pagesForIntroList.size()-1){
                    loadLastScreen();
                }
            }
        });
    }

    //getStarted fab click listener. change the activity to the main activity and save info.
    private void getStartedFabClick(){
        getStartedFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                savePrefsData();
                finish();

            }
        });
    }

    private void savePrefsData(){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editData = preferences.edit();
        editData.putBoolean("isIntroOpened", true);
        editData.apply();

    }

    private boolean restorePrefsData(){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        return preferences.getBoolean("isIntroOpened", false);

    }
}
