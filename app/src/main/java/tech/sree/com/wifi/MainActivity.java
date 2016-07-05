package tech.sree.com.wifi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mySharedpreferences = null ;
    private String MyPREFERENCES = "MyWifiSettigns";
    TextView fromTime,toTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        fromTime = (TextView) findViewById(R.id.fromText);
        toTime = (TextView) findViewById(R.id.toText);

    }

    public void processOnClick(View V){

        switch (V.getId()){
            case R.id.alarmON:
            case R.id.fromText:
                Intent alarmOnIntent =  new Intent(getApplicationContext() ,Alarm.class);
                alarmOnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                alarmOnIntent.putExtra("ON",true);
                startActivity(alarmOnIntent);
                break;
            case R.id.alarmOFF:
            case R.id.toText:
                Intent alarmOffIntent =  new Intent(getApplicationContext() ,Alarm.class);
                alarmOffIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                alarmOffIntent.putExtra("ON",false);
                startActivity(alarmOffIntent);
                break;
            case R.id.submitText:
            case R.id.submit:
                int onHour = 0,onMinute = 0,offHour = 0,offMinute = 0;
                onHour = mySharedpreferences.getInt("onHour",0) ;
                onMinute = mySharedpreferences.getInt("onMinute",0) ;
                offHour = mySharedpreferences.getInt("offHour",0) ;
                offMinute = mySharedpreferences.getInt("offMinute",0) ;

                String onTime =timeToString(onHour,onMinute);
                String offTime = timeToString(offHour,offMinute);
                fromTime.setText(onTime);
                toTime.setText(offTime);

                String timer = "On From : "+onTime+ " ->  "+offTime;
                L.T(this,"Confirm !!!  "+timer);
                setWifiOnOffAlarm( onHour,onMinute ,offHour ,offMinute );

                break;
            default:
        }
    }
    private void setWifiOnOffAlarm(int onHour ,int onMinute ,int offHour,int offMinute ){
        Calendar futureDateON = Calendar.getInstance();
        Calendar futureDateOFF = Calendar.getInstance();
        futureDateON.set(Calendar.HOUR_OF_DAY, onHour);
        futureDateON.set(Calendar.MINUTE, onMinute);
        futureDateON.set(Calendar.SECOND,10);
        futureDateOFF.set(Calendar.HOUR_OF_DAY, offHour);
        futureDateOFF.set(Calendar.MINUTE, offMinute);
        futureDateOFF.set(Calendar.SECOND,5);

        Intent intentON = new Intent(MainActivity.this, WifiController.class);
        intentON.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentON.putExtra("ON", "Enable_Wifi_Control");

        PendingIntent piON = PendingIntent.getActivity(MainActivity.this, 2, intentON, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager amON = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        amON.set(AlarmManager.RTC_WAKEUP, futureDateON.getTimeInMillis(), piON);

        Intent intentOFF = new Intent(MainActivity.this, WifiController.class);
        intentOFF.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentOFF.putExtra("ON", "Disable_Wifi_Control");

        PendingIntent piOFF = PendingIntent.getActivity(MainActivity.this, 3, intentOFF, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager amOFF = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        amOFF.set(AlarmManager.RTC_WAKEUP, futureDateOFF.getTimeInMillis(), piOFF);
    }
    private String timeToString(int Hour,int Minute){
        StringBuffer time = new StringBuffer();
        if (Hour < 10)
            time.append("0" +Hour);
        else
            time.append(Hour);
        time.append(" : ");
        if (Minute < 10)
            time.append("0" +Minute);
        else
            time.append(Minute);
        return time.toString();
    }

}
