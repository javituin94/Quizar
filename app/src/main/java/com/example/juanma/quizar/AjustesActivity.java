package com.example.juanma.quizar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.widget.EditText;


import java.util.List;

/**
 * A {@link android.preference.PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class AjustesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    CheckBoxPreference checkbox;
    EditTextPreference text;
    ListPreference list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.ajustes);
        text = (EditTextPreference) findPreference("pref_usuario");
        text.setSummary(getPreferenceScreen().getSharedPreferences().getString("pref_usuario",""));
        list = (ListPreference) findPreference("pref_nivel");
        list.setSummary(getPreferenceScreen().getSharedPreferences().getString("pref_nivel",""));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {



        if(key.equals("pref_sonido")){
            checkbox = (CheckBoxPreference) findPreference("pref_sonido");
            AudioManager sonido= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            if(checkbox.isChecked()){
                sonido.setStreamVolume(AudioManager.STREAM_MUSIC,0,AudioManager.FLAG_SHOW_UI);
            }else{
                sonido.setStreamVolume(AudioManager.STREAM_MUSIC,12,AudioManager.FLAG_SHOW_UI);
            }
        }

        if (key.equals("pref_usuario")) {
            text = (EditTextPreference) findPreference(key);
            text.setSummary(sharedPref.getString(key,""));
            text.setDefaultValue(sharedPref.getString(key,""));
            text.setText(sharedPref.getString(key,""));

        }

        if (key.equals("pref_nivel")) {
            list = (ListPreference) findPreference("pref_nivel");
            list.setSummary(sharedPref.getString(key,""));
            AudioManager sonido= (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            sonido.setStreamVolume(AudioManager.STREAM_MUSIC,Integer.parseInt(list.getSummary().toString()),AudioManager.FLAG_SHOW_UI);

        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }



}
