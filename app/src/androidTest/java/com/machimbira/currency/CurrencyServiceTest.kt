package com.machimbira.currency

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.machimbira.currency.service.JobScheduler
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CurrencyServiceTest
{
    @Test
    fun testJobScheduler() {
        JobScheduler.start()
    }
}
