package com.machimbira.currency.network.callback

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.model.ExchangeRateModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRateCallback(private val resultCallback: IResultCallback<ExchangeRateModel>, private val exchangeRateMapper: ExchangeRateMapper) : Callback<ResponseBody> {

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if(response.isSuccessful){
            val rates = exchangeRateMapper.mapResponseToModel(response.body())
            resultCallback.succeed(rates)
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        resultCallback.fail(listOf("No network"))
    }
}