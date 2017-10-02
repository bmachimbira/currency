package com.machimbira.currency.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.machimbira.currency.configuration.CurrencyApplication


class JobScheduler {

    companion object {


        fun start() {
            val services = HashMap<Service, Long>()
            services.put(CurrencyRatesService(), AlarmManager.INTERVAL_HOUR)


            val thread = Thread {
                val context = CurrencyApplication.getContext()
                val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                for (service: Service in services.keys) {
                    val startStashService = Intent(context, service::class.java)
                    val stashServiceIntent = PendingIntent.getService(context, 100, startStashService, 0)

                    val interval = services.getValue(key = service)
                    manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), interval, stashServiceIntent)

                    context.startService(startStashService)
                }
            }

            thread.start()
        }
    }
}
