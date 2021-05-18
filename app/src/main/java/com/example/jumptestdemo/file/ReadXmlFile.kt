package com.example.jumptestdemo.file

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

            while (event != XmlPullParser.END_DOCUMENT) {
                if(event == XmlPullParser.START_TAG) {
                    if("scheme" == pullParser.name) {
                        val parametersList = ArrayList<Parameter>()
                        //val tag : Integer = Integer(pullParser.getAttributeValue(0))
                        var key = pullParser.name
                        while (key.isNotEmpty()) {
                            parametersList.add(Parameter(key, pullParser.nextText()))
                            key = pullParser.name
                        }
                        arrList.add(Scheme(parametersList))
                    }
                }
                event = pullParser.next()
            }

        } catch (e : Exception) {

        }

        return arrList
    }
}