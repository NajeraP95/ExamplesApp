package com.najera.examplesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    SharedPreferences preferences;

    public SharedPrefs(Context context){
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    //Method to save the the Night/Dark mode state
    public void setNightModeState(Boolean state){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();

    }

    public boolean loadNightModeState(){
        Boolean state = preferences.getBoolean("NightMode", false);
        return state;
    }
}
