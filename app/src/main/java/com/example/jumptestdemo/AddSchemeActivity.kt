package com.example.jumptestdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import com.example.jumptestdemo.adpter.ParametersAdapter
import com.example.jumptestdemo.bean.Parameter
import java.lang.StringBuilder

class AddSchemeActivity : AppCompatActivity() {

    companion object{
        val strParameter = StringBuilder()
        val parameterList = ArrayList<Parameter>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_scheme)

        initView()
    }

    private fun initView() {
        //清空遗留数据
        parameterList.clear()
        strParameter.clear()

        val btnReturn : View = findViewById(R.id.btn_return)
        val btnAddParamter : View = findViewById(R.id.btn_add_paramters)
        val etParameterName : EditText = findViewById(R.id.et_parameter_name)
        val etParameterValue : EditText = findViewById(R.id.et_parameter_value)
        val lvParamters :ListView = findViewById(R.id.lv_parameters)
        val adapter = ParametersAdapter(this, parameterList)
        lvParamters.adapter = adapter

        btnReturn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            intent.putExtra("Parameter", strParameter.toString())
            startActivity(intent)
            finish()
        }

        btnAddParamter.setOnClickListener {
            val parameterName = etParameterName.text.toString()
            val parameterValue = etParameterValue.text.toString()

            if(parameterName.isNotEmpty()){
                adapter.addItem(Parameter(parameterName, parameterValue))
                strParameter.append("&$parameterName=$parameterValue")
            }

            etParameterName.setText("")
            etParameterValue.setText("")
        }
    }
}