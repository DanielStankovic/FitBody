package link.fitbody;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Daniel on 8/12/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        ListPreference unit = (ListPreference)findPreference("unit_preference");
        CharSequence entry = unit.getEntry();
        unit.setSummary(entry);

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key){
            case "unit_preference":
                ListPreference unit = (ListPreference)findPreference(key);
                CharSequence entry = unit.getEntry();
                unit.setSummary(entry);
                if(entry.equals("Imperial")){
                    ((MotherActivity)getActivity()).showCalculatorToast("You are now using IMPERIAL system of measurements");
                } else{
                    ((MotherActivity)getActivity()).showCalculatorToast("You are now using METRIC system of measurements");
                }
        }
    }
}
