package com.example.jumptestdemo.file

import android.util.Log
import android.util.Xml
import com.example.jumptestdemo.bean.Parameter
import com.example.jumptestdemo.bean.Scheme
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

class ReadXmlFile {

    fun getXml(fileName : String) : ArrayList<Scheme> {

        val arrList = ArrayList<Scheme>()
        val inStream: InputStream = this.javaClass.classLoader
            .getResourceAsStream(fileName)
        try {
            val pullParser : XmlPullParser = Xml.newPullParser()
            pullParser.setInput(inStream, "UTF-8")
            var event = pullParser.eventType
            val parameterList = ArrayList<Parameter>()
            while (event != XmlPullParser.END_DOCUMENT) {
                when (event) {
                    XmlPullParser.START_TAG -> {
                        if (pullParser.name == "scheme") {
                            if (parameterList.size != 0) {
                                arrList.add(Scheme(parameterList))
                            }
                            parameterList.clear()
                        }
                        if (pullParser.name == "parameter") {
                            parameterList.add(Parameter(pullParser.getAttributeValue(null, "name"), pullParser.nextText()))
                        }
                    }
                }
                event = pullParser.next()
            }

            arrList.add(Scheme(parameterList))

        } catch (e : Exception) {
            Log.e("Error", e.message!!)
        }

        return arrList
    }
}