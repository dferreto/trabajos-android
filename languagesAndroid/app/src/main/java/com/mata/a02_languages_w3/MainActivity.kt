package com.mata.a02_languages_w3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn_show:  Button = findViewById(R.id.btn_show)
        var btn_shop:  Button = findViewById(R.id.btn_shop)
        var btn_print: Button = findViewById(R.id.btn_print)

        btn_show.setOnClickListener {
            Toast.makeText(this,R.string.msg_show ,Toast.LENGTH_SHORT).show()
        }

        btn_shop.setOnClickListener {
            Toast.makeText(this,R.string.msg_shop,Toast.LENGTH_SHORT).show()
        }

        btn_print.setOnClickListener {
            Toast.makeText(this,R.string.msg_print,Toast.LENGTH_SHORT).show()
        }
    }
}