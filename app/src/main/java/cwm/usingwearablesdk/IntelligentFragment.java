package cwm.usingwearablesdk;

import android.content.Context;
import android.os.Bundle;
import com.github.machinarius.preferencefragment.PreferenceFragment;
import android.util.Log;

import android.preference.EditTextPreference;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class IntelligentFragment extends PreferenceFragment{

    private Boolean isSendtaryRemind = true;
    private Boolean isHandUp = true;
    private Boolean isOnWear = false;
    private Boolean isDoubleTap = false;
    private Boolean isWristScroll = false;
    private int stepAim = 0;

    ListenerForIntellignetFragment mCallback;

    public interface ListenerForIntellignetFragment{

        void onIntelligentSyncToRing();

    }

    // lift-cycle ----------------------------------------------------------------------------------
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            mCallback = (ListenerForIntellignetFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ListenForPersonalInfoFragment");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    } // onActivityCreated()

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.intellignet_settings);

        final SwitchPreference sedentarySwitch = (SwitchPreference) findPreference("sedentary_remind");
        final SwitchPreference handUpSwitch = (SwitchPreference) findPreference("hand_up");
        final SwitchPreference onWearSwitch = (SwitchPreference) findPreference("on-wear");
        final SwitchPreference doubleTapSwitch = (SwitchPreference) findPreference("double-tap");
        final SwitchPreference wristScrollSwitch = (SwitchPreference) findPreference("wrist-scroll");
        final EditTextPreference editTextPreference = (EditTextPreference) findPreference("step_number_aim");
        final Preference sync = findPreference("intelligent_sync");


        if (sedentarySwitch != null){
            sedentarySwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
                @Override
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject){
                    boolean isEnabled = false;
                    isEnabled = sedentarySwitch.isChecked();
                    if(isEnabled == false)
                        isSendtaryRemind = true;
                    else
                        isSendtaryRemind = false;

                    return true;
                }
            });
        }
        if(handUpSwitch != null) {
            handUpSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                    Boolean isEnabled = false;
                    isEnabled = handUpSwitch.isChecked();
                    if (isEnabled == false)
                        isHandUp = true;
                    else
                        isHandUp = false;

                    return true;

                }
            });
        }

        if(onWearSwitch != null) {
            onWearSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                    Boolean isEnabled = false;
                    isEnabled = onWearSwitch.isChecked();
                    if (isEnabled == false)
                        isOnWear = true;
                    else
                        isOnWear = false;

                    return true;

                }
            });
        }

        if(doubleTapSwitch != null) {
            doubleTapSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                    Boolean isEnabled = false;
                    isEnabled = doubleTapSwitch.isChecked();
                    if (isEnabled == false)
                        isDoubleTap = true;
                    else
                        isDoubleTap = false;

                    return true;
                }
            });
        }

        if(wristScrollSwitch != null) {
            wristScrollSwitch.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                    Boolean isEnabled = false;
                    isEnabled = wristScrollSwitch.isChecked();
                    if (isEnabled == false)
                        isWristScroll = true;
                    else
                        isWristScroll = false;

                    return true;
                }
            });
        }

        if(editTextPreference != null){
            editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
                public boolean onPreferenceChange(Preference arg0, Object isVibrateOnObject) {
                    stepAim = Integer.valueOf(editTextPreference.getEditText().getText().toString());
                   return true;
                }
            });
        }

        if(sync != null) {
            sync.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){

                @Override
                public boolean onPreferenceClick(Preference  preference){

                    mCallback.onIntelligentSyncToRing();
                    return true;
                }
            });
        }
    } // onCreate()

    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("INTELLIGENT  SYNC AIM", stepAim);
        editor.commit();
    }
    @Override
    public void onResume(){
        super.onResume();

        final SwitchPreference sedentarySwitch = (SwitchPreference) findPreference("sedentary_remind");
        final SwitchPreference handUpSwitch = (SwitchPreference) findPreference("hand_up");
        final SwitchPreference onWearSwitch = (SwitchPreference) findPreference("on-wear");
        final SwitchPreference doubleTapSwitch = (SwitchPreference) findPreference("double-tap");
        final SwitchPreference wristScrollSwitch = (SwitchPreference) findPreference("wrist-scroll");

        EditTextPreference aim = (EditTextPreference) findPreference("step_number_aim");

        isSendtaryRemind = sedentarySwitch.isChecked();
        isHandUp = handUpSwitch.isChecked();
        isOnWear = onWearSwitch.isChecked();
        isDoubleTap = doubleTapSwitch.isChecked();
        isWristScroll = wristScrollSwitch.isChecked();

        stepAim = Integer.valueOf(aim.getText());

    }

    //function
    public boolean getSendtaryRemindStatus(){ return isSendtaryRemind;}
    public boolean getHandUpStatus(){ return isHandUp;}
    public boolean getOnWearStatus() {return isOnWear;}
    public boolean getDoubleTapStatus() {return isDoubleTap;}
    public boolean getWritstScrollStatus() {return isWristScroll;}
    public int getAim(){return stepAim;}

}
