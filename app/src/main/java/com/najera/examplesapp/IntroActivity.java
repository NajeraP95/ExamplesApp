package com.najera.examplesapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //init views
        screenPager = findViewById(R.id.introViewPager);
        tabIndicator = findViewById(R.id.tabIndicator);
        nextFab = findViewById(R.id.intro_fab_next);
        getStartedFab = findViewById(R.id.intro_fab_getStarted);

        //fill list intros
        fillPagesForIntroList();

        //setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(this, pagesForIntroList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setting up tabIndicator with viewPager
        tabIndicator.setupWithViewPager(screenPager);


        //onClick fab next
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

        //TODO: add an animation to the getstarted fab

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
}
