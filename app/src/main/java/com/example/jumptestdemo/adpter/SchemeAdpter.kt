package com.example.jumptestdemo.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumptestdemo.R
import com.example.jumptestdemo.bean.Parameter
import com.example.jumptestdemo.bean.Scheme

class SchemeAdpter(private val context : Context, private var dataSet:ArrayList<Scheme>) :
    RecyclerView.Adapter<SchemeAdpter.ViewHolder>() {

    private var itemClickListener: IKotlinItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llayout : LinearLayout = view.findViewById(R.id.ll_parameter)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_scheme, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val parameterList : List<Parameter> = dataSet[position].parameters
        for(parameter in parameterList){
            val tvParameter = TextView(context)
            tvParameter.text = parameter.key + "    " + parameter.value
            tvParameter.textSize = 20F
            viewHolder.llayout.addView(tvParameter)
        }

        // 点击事件
        viewHolder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount() = dataSet.size

    //动态添加item
    fun add(scheme : Scheme){
        if(dataSet.isNullOrEmpty()){
            dataSet = ArrayList<Scheme>()
        }
        dataSet.add(dataSet.size, scheme)
        notifyItemInserted(dataSet.size)
    }

    //动态更改
    fun change(list : ArrayList<Scheme>) {
        dataSet.clear()
        notifyItemRangeRemoved(0, dataSet.size)
        dataSet.addAll(list)
        //notifyItemRangeInserted(0, list.size )
        notifyItemRangeInserted(0, list.size-1)

    }

    //返回指定position的Scheme
    fun getScheme(position : Int) : Scheme {
        return dataSet[position]
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }

}