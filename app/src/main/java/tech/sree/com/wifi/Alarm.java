package tech.sree.com.wifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }
    public void processOnClickAlarm(View V){
        L.T(this,"Alarm Time Set DOne !!!!");
        finish();

    }
}
