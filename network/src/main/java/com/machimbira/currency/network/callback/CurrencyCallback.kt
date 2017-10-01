package com.machimbira.currency.network.callback

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.mapper.CurrencyMapper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyCallback(private val resultCallback: IResultCallback<Map<String, Any>>, val currencyMapper: CurrencyMapper) : Callback<ResponseBody> {

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if(response.isSuccessful){
            val currencies = currencyMapper.mapResponseToModel(response.body())
            resultCallback.succeed(currencies)
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        resultCallback.fail(listOf("No network"))
    }
}
