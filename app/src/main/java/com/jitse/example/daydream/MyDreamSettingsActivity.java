package com.jitse.example.daydream;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.jitse.example.R;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jitse on 4/2/15.
 */
public class MyDreamSettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(null);

        if( null != getApplicationContext() ){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
                if(StringUtils.equals(key, "daydream_gender")){
                    String genderKey = sharedPreferences.getString(key, "both");

                    String[] keys = getResources().getStringArray(R.array.daydream_gender);
                    String[] values = getResources().getStringArray(R.array.daydream_gender_values);

                    int idx = ArrayUtils.indexOf(keys, genderKey);

                    if( idx >= 0 ){
                        String gender = values[idx];

                        Preference genderPref = findPreference(key);
                        if( null != genderPref ){
                            genderPref.setSummary(gender);
                        }
                    }
                }
            });
        }

        addPreferencesFromResource(R.xml.daydream_preferences);
    }
}
