package com.najera.examplesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context context;
    List<IntroScreenItem> itemsList;

    //CONSTRUCTOR
    public IntroViewPagerAdapter(Context context, List<IntroScreenItem> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = layoutInflater.inflate(R.layout.layout_screen_intro, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.img_intro);
        TextView txtTitle = layoutScreen.findViewById(R.id.txt_intro_title);
        TextView txtDescription = layoutScreen.findViewById(R.id.txt_intro_description);

        container.addView(layoutScreen);

        return layoutScreen;

    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
