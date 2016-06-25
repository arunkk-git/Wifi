package tech.sree.com.wifi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ananth on 6/25/2016.
 */
public class L {
    static void T(Context context,String msg){

        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
    static void p(String msg){
        Log.d("ARUN",msg);
    }
}
