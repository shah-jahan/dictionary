package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.float_dict_activity.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchbttn.setOnClickListener {


            val inputstream = getResources().openRawResource(R.raw.olamenml)
            var key = userinput.text.toString()
            val reader = BufferedReader(InputStreamReader(inputstream))
            try
            {
                var csvLine:String= reader.readLine()
                csvLine = reader.readLine()
                reader.readLine()
                do{
                    //csvLine = reader.readLine()

                    var ids = csvLine.split((",").toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

                    var word= ids[1]
                    var flag = 0
                    if(word.toLowerCase() == key.toLowerCase())
                    {

                        resultView.text= key+" : "+ids[3]
                        flag=1
                    }

                    csvLine = reader.readLine()
                    if(csvLine == null && flag==0)
                        result.text="Soryy word not found"
                }while (csvLine != null && flag == 0)
            }catch (e:Exception) {
                Log.e("Unknown fuck", e.toString())



            }
        }

}}
