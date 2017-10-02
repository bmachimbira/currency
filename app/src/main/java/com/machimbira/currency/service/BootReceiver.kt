package com.machimbira.currency.service

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        JobScheduler.start()
    }
}