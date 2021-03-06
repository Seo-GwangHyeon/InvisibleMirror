/*
 * Copyright (C) 2008-2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.invisiblemirror.keyboard.inputmethod;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.text.AutoText;
import android.util.Log;
import android.widget.Toast;

import com.example.invisblemirror.R;

public class KeyboardSettings extends PreferenceActivity 
	implements Preference.OnPreferenceChangeListener{
    
    private static final String QUICK_FIXES_KEY = "quick_fixes";
   // private static final String SHOW_SUGGESTIONS_KEY = "show_suggestions";
   // private static final String PREDICTION_SETTINGS_KEY = "prediction_settings";
    private static final String PREF_SELECT_SKIN = "select_skin";
    //private static final String SHOW_SUGGESTIONS_KEY = "show_suggestions";
    
    private CheckBoxPreference mQuickFixes;
//    private CheckBoxPreference mShowSuggestions;
    private ListPreference mSelectSkin;
    
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.prefs);
        mQuickFixes = (CheckBoxPreference) findPreference(QUICK_FIXES_KEY);
     //   mShowSuggestions = (CheckBoxPreference) findPreference(SHOW_SUGGESTIONS_KEY);
        mSelectSkin = (ListPreference) findPreference(PREF_SELECT_SKIN);
        
        mSelectSkin.setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int autoTextSize = AutoText.getSize(getListView());
     /*   if (autoTextSize < 1) {
            ((PreferenceGroup) findPreference(PREDICTION_SETTINGS_KEY))
                .removePreference(mQuickFixes);
        } else {
            mShowSuggestions.setDependency(QUICK_FIXES_KEY);
        }*/
        
        String val = mSelectSkin.getValue();
        setSkinPreferenceSummary(val);
    }
    
    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	
        String strSkin = setSkinPreferenceSummary(objValue.toString());
        
        String message = getString(R.string.set_skin, strSkin);
        Toast.makeText(KeyboardSettings.this, message, Toast.LENGTH_LONG).show();
        return true;
    }
    
    private String setSkinPreferenceSummary(String objVal)
    {
    	String[] val = getResources().getStringArray(R.array.pref_skin_values);
        String[] str = getResources().getStringArray(R.array.pref_skin_choices);
        String skin;
        if(0 == val[0].compareTo(objVal.toString()))
        	skin = str[0];
        else
        	skin = str[1];
        
        mSelectSkin.setSummary(skin);
        return skin;
    }
}
