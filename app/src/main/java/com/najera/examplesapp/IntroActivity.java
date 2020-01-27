package com.najera.examplesapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private List<IntroScreenItem> pagesForIntroList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fillingPagesForIntroList();

        screenPager = findViewById(R.id.introViewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, pagesForIntroList);
        screenPager.setAdapter(introViewPagerAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, ":)", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void fillingPagesForIntroList(){
        pagesForIntroList.add(new IntroScreenItem("", "", 1));
        pagesForIntroList.add(new IntroScreenItem("", "", 1));
    }

}
