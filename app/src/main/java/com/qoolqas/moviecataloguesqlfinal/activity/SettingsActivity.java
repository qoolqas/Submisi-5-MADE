//package com.qoolqas.moviecataloguesqlfinal.activity;
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.preference.Preference;
//import androidx.preference.PreferenceFragmentCompat;
//import androidx.preference.SwitchPreferenceCompat;
//
//import com.qoolqas.moviecataloguesqlfinal.R;
//import com.qoolqas.moviecataloguesqlfinal.notif.Daily;
//import com.qoolqas.moviecataloguesqlfinal.notif.MyReceiver;
//
//import java.util.Calendar;
//import java.util.Objects;
//
//public class SettingsActivity extends AppCompatActivity {
//    static MyReceiver release;
//    static Daily daily;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings_activity);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.settings, new SettingsFragment())
//                .commit();
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
//
//    public static class SettingsFragment extends PreferenceFragmentCompat {
//        SwitchPreferenceCompat Mrelease,Mdaily;
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            setPreferencesFromResource(R.xml.root_preferences, rootKey);
//            Mrelease = findPreference(getResources().getString(R.string.sync_title));
//            Mdaily = findPreference(getResources().getString(R.string.attachment_title));
//            Mrelease.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    if (Mrelease.isChecked()){
//                        release = new MyReceiver();
//                        setRelease(getContext());
//                    }else if (!Mrelease.isChecked()){
//                        release = new MyReceiver();
//                        Release(getContext());
//                    }
//                    return false;
//                }
//            });
//            daily.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    if (daily.isChecked()){
//                        daily_receiver = new daily_reciever();
//                        daily_receiver.daily_setalarmmanager(getContext());
//                    }else if (!daily.isChecked()){
//                        daily_receiver = new daily_reciever();
//                        daily_receiver.daily_setcancel(getContext());
//                    }
//                    return false;
//                }
//            });
//
//
//        }
//    }
//    public static void setRelease(Context context){
//        Intent intent = new Intent(context, MyReceiver.class);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,8);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        if (alarmManager != null){
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//        }
//    }
//    public static void Release(Context context){
//        Intent intent = new Intent(context,MyReceiver.class);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        Objects.requireNonNull(alarmManager).cancel(pendingIntent);
//    }
//}