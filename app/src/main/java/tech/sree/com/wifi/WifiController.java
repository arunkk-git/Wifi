package tech.sree.com.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WifiController extends AppCompatActivity {

    private final String WIFI_ON  = "Enable_Wifi_Control";
    private final String WIFI_OFF  = "Disable_Wifi_Control";
    private static boolean initialStatus = false ;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performAction();
        finish();
    }
    private void  performAction() {

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        Bundle bundle = getIntent().getExtras();
        String alarmInfo = null;

        if (bundle != null) {
            alarmInfo = bundle.getString("ON");
            if (alarmInfo.equals(WIFI_ON)) {
                initialStatus = wifiManager.isWifiEnabled();
                L.p(" WIFI COntrol ON  initialStatus = " + initialStatus);
                toggleWiFi(false);
            } else if (alarmInfo.equals(WIFI_OFF)) {// && initialStatus) {
                L.p("WIFI COntrol OFF  initialStatus = " + initialStatus);
                if (initialStatus) {
            /*check the initialStatus, if it's
                 true then only Wifi will be ON after alarm.  else we simpley  ignore
              */
                    L.p(" WIFI Control OFF ");
                    toggleWiFi(true);
                }
            }
       //     finish();
        }
    }
    public void toggleWiFi(boolean status) {
        if (status != wifiManager.isWifiEnabled()) {
            L.p("toggleWiFi set = "+status);
            wifiManager.setWifiEnabled(status);
        }


    }
}
