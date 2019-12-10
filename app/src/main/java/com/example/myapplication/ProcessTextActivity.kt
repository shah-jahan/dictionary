package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import de.siegmar.fastcsv.reader.CsvContainer
import de.siegmar.fastcsv.reader.CsvReader
import kotlinx.android.synthetic.main.float_dict_activity.*
import java.io.*
import java.nio.charset.StandardCharsets


class ProcessTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.float_dict_activity)




        var key = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()

        result.text=key

        val inputstream = getResources().openRawResource(R.raw.olamenml)
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

                    result.text= key+" : "+ids[3]
                    flag=1
                }

                csvLine = reader.readLine()
                if(csvLine == null && flag==0)
                    result.text="Soryy word not found"
            }while (csvLine != null && flag == 0)
        }catch (e:Exception) {
            Log.e("Unknown fuck", e.toString())



        }}}
