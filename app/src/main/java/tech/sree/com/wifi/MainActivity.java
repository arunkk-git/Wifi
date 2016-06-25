package tech.sree.com.wifi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void processOnClick(View V){
        switch (V.getId()){
            case R.id.alarmON:
            case R.id.fromText:
                Intent alarmOnIntent =  new Intent(getApplicationContext() ,Alarm.class);
                alarmOnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(alarmOnIntent);
                break;
            case R.id.alarmOFF:
            case R.id.toText:
                Intent alarmOffIntent =  new Intent(getApplicationContext() ,Alarm.class);
                alarmOffIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(alarmOffIntent);
                break;
            case R.id.submitText:
            case R.id.submit:
                L.T(this,"Confirm !!!");
                break;
            default:
        }
    }
}
