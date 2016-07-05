package tech.sree.com.wifi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {
    private TimePicker timer;
    private SharedPreferences mySharedpreferences = null ;
    private String MyPREFERENCES = "MyWifiSettigns";
    private final  int MinDiff =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timer = (TimePicker) findViewById(R.id.timePicker);
        mySharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);


    }
    public void processOnClickAlarm(View V){
        int onHour = 0,onMinute = 0,offHour = 0,offMinute = 0;

        boolean alarm = getIntent().getBooleanExtra("ON",false);

        SharedPreferences.Editor editor = mySharedpreferences.edit();

        int  hour = 0,minute = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timer.getHour();
            minute = timer.getMinute();
        }else {
            hour = timer.getCurrentHour();
            minute = timer.getCurrentMinute();
        }
        if(alarm== true){ //Alarm Triggered to TurnOff Wifi
            onHour = hour;
            onMinute = minute;
            editor.putInt("onHour",onHour);
            editor.putInt("onMinute",onMinute);
        }else {
            /*
            Calendar futureDateON = Calendar.getInstance();
            Calendar futureDateOFF = Calendar.getInstance();
            futureDateON.set(Calendar.HOUR_OF_DAY, onHour);
            futureDateON.set(Calendar.MINUTE, onMinute);
            futureDateON.set(Calendar.SECOND,10);
            futureDateOFF.set(Calendar.HOUR_OF_DAY, offHour);
            futureDateOFF.set(Calendar.MINUTE, offMinute);
            futureDateOFF.set(Calendar.SECOND,5);

            if(futureDateOFF.before(futureDateON))
             */
            L.p("onHour : "+onHour+" onMinute : "+onMinute);
            if(hour== onHour &&  minute <= onMinute+MinDiff)
            {
                L.T(this ,"Set prpoer Time !!!\n Minimum diff: "+MinDiff);
                hour++;
                if(minute+MinDiff < 60)
                    minute = minute+MinDiff;
                else
                    minute = minute+((minute+MinDiff) - 61);
            }

            if(hour < onHour){
                L.T(this,"Set prpoer Time !!!\n Minimum diff: "+MinDiff);
                hour= onHour;

                if(minute+MinDiff < 60)
                    minute = onMinute+MinDiff;
                else
                    minute = onMinute+((onMinute+MinDiff) - 61);
            }

            offHour = hour;
            offMinute = minute;

            editor.putInt("offHour",offHour);
            editor.putInt("offMinute",offMinute);
        }
        editor.commit();
        finish();
    }
}
