package com.example.jumptestdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jumptestdemo.adpter.SchemeAdpter
import com.example.jumptestdemo.bean.Parameter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val parameters = initDefaultValue()

        val schemeAdapter = SchemeAdpter(parameters)
        val rvScheme : RecyclerView = findViewById(R.id.rv_scheme)
        rvScheme.layoutManager = LinearLayoutManager(this);
        rvScheme.adapter = schemeAdapter
    }

    private fun initDefaultValue() : List<Parameter> {
        val parameters : ArrayList<Parameter> = ArrayList()
        val parameter_1 = Parameter("start_name", "startName")
        val parameter_2 = Parameter("end_name", "endName")
        val parameter_3 = Parameter("from_coord", "fromCoord")
        val parameter_4 = Parameter("to_coord", "tocoord")
        parameters.add(parameter_1)
        parameters.add(parameter_2)
        parameters.add(parameter_3)
        parameters.add(parameter_4)
        return parameters
    }
}