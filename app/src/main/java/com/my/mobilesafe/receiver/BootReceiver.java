package com.my.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSimSerialNumber();
        String sim_number = SpUtils.getString(context, ConstantValue.SIM_NUMBER, "");
        if(simSerialNumber.equals(sim_number)){
            SmsManager sms = SmsManager.getDefault();

            sms.sendTextMessage("5556", null, "sim change!!!", null, null);
        }
    }
}
