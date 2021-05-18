package com.example.jumptestdemo.adpter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.jumptestdemo.R
import com.example.jumptestdemo.bean.Parameter

class ParametersAdapter(val context: Context, var parameterList: ArrayList<Parameter>) : BaseAdapter(){

    override fun getCount(): Int {
        return parameterList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView =LayoutInflater.from(context).inflate(R.layout.item_parameter,parent,false)
        val tvParameterName : TextView = itemView.findViewById(R.id.tv_param_name)
        val tvParameterValue : TextView = itemView.findViewById(R.id.tv_param_value)
        val parameter = parameterList[position]
        if(parameter != null){
            tvParameterName.text = parameter.key
            tvParameterValue.text = parameter.value
        }

        return itemView
    }

    //动态添加item
    fun addItem(parameter : Parameter){
        if(parameterList == null) {
            parameterList = ArrayList<Parameter>()
        }
        parameterList.add(parameter)
        notifyDataSetChanged()
    }
}