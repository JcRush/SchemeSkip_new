package com.example.jumptestdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jumptestdemo.adpter.SchemeAdpter
import com.example.jumptestdemo.bean.Parameter
import com.example.jumptestdemo.bean.Scheme


class MainActivity : AppCompatActivity() {

    private lateinit var schemeAdapter : SchemeAdpter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initView()
    }

    //从添加参数页面返回
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val parameters = intent?.getStringExtra("Parameter")
        val getScheme = opStr2Scheme(parameters!!)
        schemeAdapter.add(getScheme)
    }

    private fun initView() {
        val schemeList = initDefaultValue()

        schemeAdapter = SchemeAdpter(this, schemeList)
        val rvScheme : RecyclerView = findViewById(R.id.rv_scheme)
        rvScheme.layoutManager = LinearLayoutManager(this);
        rvScheme.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvScheme.adapter = schemeAdapter

        val btnAddCheme : View = findViewById(R.id.btn_add_scheme)
        btnAddCheme.setOnClickListener{
            val intent : Intent = Intent()
            intent.setClass(this, AddSchemeActivity::class.java)
            startActivity(intent)
        }

        //为item添加点击事件
        schemeAdapter!!.setOnKotlinItemClickListener(object : SchemeAdpter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                val scheme = schemeAdapter.getScheme(position)
                val stringBuffer = StringBuffer("qqmap://map/routeplan?")
                for(parameter in scheme.parameters) {
                    stringBuffer.append("&" + parameter.key + "=" + parameter.value)
                }
                val intent = Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
                startActivity(intent)
            }
        })
    }

    private fun initDefaultValue() : ArrayList<Scheme> {
        return ArrayList<Scheme>()
    }

    //字符串转换成Scheme
    private fun opStr2Scheme(str : String) : Scheme {
        val parms = ArrayList<Parameter>()
        val parameterList : List<String> = str.split("&")
        if(parameterList.isNotEmpty()){
            for(parameter in parameterList){
                if(parameter.isNullOrEmpty()) continue;
                val keyValue : List<String> = parameter.split("=")
                if(keyValue.isNotEmpty()){
                    parms.add(Parameter(keyValue[0], keyValue[1]))
                }
            }
        }
        return Scheme(parms)
    }


}