package tech.sree.com.wifi;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class Alarm extends AppCompatActivity {
    private TimePicker timer;
    private SharedPreferences mySharedpreferences = null ;
    private String MyPREFERENCES = "MyWifiSettigns";

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
            offHour = hour;
            offMinute = minute;
            editor.putInt("offHour",offHour);
            editor.putInt("offMinute",offMinute);
        }
        editor.commit();
        finish();
    }
}
