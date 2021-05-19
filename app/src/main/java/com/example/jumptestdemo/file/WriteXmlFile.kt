package com.example.jumptestdemo.file

import android.content.Context
import android.util.Log
import android.util.Xml
import org.xmlpull.v1.XmlSerializer
import java.io.OutputStream
import java.lang.Exception

class WriteXmlFile {

    fun Write(context : Context, fileName : String) {
        try {
            val serializer : XmlSerializer = Xml.newSerializer()
            val os : OutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            serializer.setOutput(os, "UTF-8")
            serializer.startDocument("UTF-8", true);

            // 开始根标签
            serializer.startTag(null, "data");
            for(index  in 1..10) {
                // 开始子标签
                serializer.startTag(null, "scheme")
                // 设置属性
                serializer.attribute(null, "id", (100 + index).toString())

                // 设置type
                // 开始type标签
                serializer.startTag(null, "type");
                // 写入值
                serializer.text("car");
                // 结束type标签
                serializer.endTag(null, "type");

                // 结束子标签
                serializer.endTag(null, "scheme");
            }
            // 结束根标签
            serializer.endTag(null, "data");

            // 结束文档
            serializer.endDocument();

        }catch (e : Exception){
            Log.e("Error", e.message!!)
        }
    }
}