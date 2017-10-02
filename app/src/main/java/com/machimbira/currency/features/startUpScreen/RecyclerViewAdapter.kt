package com.machimbira.currency.features.startUpScreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.machimbira.currency.R
import com.machimbira.currency.domain.Currency
import kotlinx.android.synthetic.main.currency_list_row.view.*

class RecyclerViewAdapter(val context: Context, val currencies: List<Currency>, val listener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(item: Currency)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(currencies.get(position), listener)

        holder?.description?.text = currencies[position].description
        holder?.code?.text = currencies[position].code
        holder?.currentRate?.text = String.format(context.resources.getString(R.string.current_rate_string), currencies[position].rate)
        holder?.minimum?.text = String.format(context.resources.getString(R.string.minimum_threshhold), currencies[position].minimum)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.currency_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val description = itemView.currency_description!!
        val code = itemView.currency_code!!
        val currentRate = itemView.currency_current_rate!!
        val minimum = itemView.currency_minimum!!

        fun bind(currency: Currency, listener:OnItemClickListener) {
            itemView.setOnClickListener{
                listener.onItemClick(currency)
            }
        }
    }

}
