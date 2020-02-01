package com.najera.examplesapp;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPrefs {

    private SharedPreferences preferences;

    SharedPrefs(Context context){
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    //Method to save the the Night/Dark mode state
    void setNightModeState(Boolean state){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();

    }

    //Method to load the Night/Dark mode state
    boolean loadNightModeState(){

        return preferences.getBoolean("NightMode", false);

    }

    //test intro shared preferences
    void setIntroOpenedState(Boolean state){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroAlreadyOpen", state);
        editor.apply();

    }

    boolean loadIntroOpenedState(){

        return preferences.getBoolean("isIntroAlreadyOpen", false);
    }
}
