package techsays.in;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if (status.isEmpty()) {
            status = "No Internet Connection";
        }
        if(status.equals("Wifi enabled"))
        {

        }
        else  if(status.equals("Mobile data enabled"))
        {

        }
        else
        {
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }

        //Crouton.make(context,status, Style.INFO).show();
    }
}