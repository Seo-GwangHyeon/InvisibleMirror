package com.example.invisiblemirror;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.invisiblemirror.mover.AlarmSoundService;

public class TestReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent=new Intent(context, AlarmSoundService.class);
        context.startService(serviceIntent);
    }
}
