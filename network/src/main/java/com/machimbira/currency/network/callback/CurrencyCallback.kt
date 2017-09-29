package com.machimbira.currency.network.callback

import com.machimbira.currency.common.IResultCallback
import com.machimbira.currency.network.model.CurrencyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyCallback(private val resultCallback: IResultCallback<List<CurrencyModel>>) : Callback<List<CurrencyModel>> {

    override fun onResponse(call: Call<List<CurrencyModel>>, response: Response<List<CurrencyModel>>) {
        if(response.isSuccessful){
            resultCallback.succeed(response.body()!!)
        }
    }

    override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
        resultCallback.fail(listOf("No network"))
    }
}
