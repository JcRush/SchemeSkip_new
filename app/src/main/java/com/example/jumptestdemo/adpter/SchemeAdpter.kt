package com.example.jumptestdemo.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumptestdemo.R
import com.example.jumptestdemo.bean.Parameter

class SchemeAdpter(private val dataSet:List<Parameter>) :
    RecyclerView.Adapter<SchemeAdpter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val paramName : TextView
        val paramValue : TextView

        init {
            paramName = view.findViewById(R.id.tv_param_name)
            paramValue = view.findViewById(R.id.tv_param_value)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_parameter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.paramName.text = dataSet[position].value
    }

    override fun getItemCount() = dataSet.size

}