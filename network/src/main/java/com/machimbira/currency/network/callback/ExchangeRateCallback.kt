package com.machimbira.currency.network.callback

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.model.ExchangeRateModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRateCallback(private val resultCallback: IResultCallback<ExchangeRateModel>) : Callback<ExchangeRateModel> {

    override fun onResponse(call: Call<ExchangeRateModel>, response: Response<ExchangeRateModel>) {
        if(response.isSuccessful){
            resultCallback.succeed(response.body())
        }
    }

    override fun onFailure(call: Call<ExchangeRateModel>, t: Throwable) {
        resultCallback.fail(listOf("No network"))
    }
}