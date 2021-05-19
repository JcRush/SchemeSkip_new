package com.example.jumptestdemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jumptestdemo.adpter.SchemeAdpter
import com.example.jumptestdemo.bean.Parameter
import com.example.jumptestdemo.bean.Scheme
import com.example.jumptestdemo.file.ReadXmlFile
import com.example.jumptestdemo.file.WriteXmlFile
import java.io.File
import java.lang.Exception


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
        return readXMlFileFromRes()
        //return ArrayList<Scheme>()
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

    //读取指定的xml文件
    fun readXMlFileFromRes() : ArrayList<Scheme> {
        val fileName = "assets/scheme.xml"
        val xmlRead = ReadXmlFile()
        return xmlRead.getXml(fileName)
    }

    //测试读写XML文件
    fun testXml() : ArrayList<Scheme>{
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 222);


        //val directory = File(Environment.getStorageDirectory().path  +  File.separator.toString() + "scheme")
        ///val directoryPath = "/sdcard/Scheme/"
        var arrayScheme : ArrayList<Scheme> = ArrayList()
        val fileName = "scheme.xml"
        try {

            //val fileDir =  File(directoryPath);
            //if (!fileDir.exists()) {
            //    fileDir.mkdir();
            //}
            //if(fileDir.exists()){
            //    Log.i("Directory" , "Exists")
            //}

            val xmlWrite = WriteXmlFile()
            val writePath = xmlWrite.Write(this, fileName)
            val xmlRead = ReadXmlFile()
            arrayScheme = xmlRead.getXml(fileName)
        } catch (e : Exception) {
            Log.e("Error", e.message!!)
        }

        return  arrayScheme
    }

    @Override
    fun onRequestPermissionsResult(requestCode : Int, permissions : Array<String>, grantResults : Array<Int>) {
        when (requestCode) {
            222 ->
            Toast.makeText(getApplicationContext(), "已申请权限", Toast.LENGTH_SHORT).show();

        }
    }



}