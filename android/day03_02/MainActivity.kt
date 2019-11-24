package com.example.day03_02

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public val REQUEST_CODE_EASY:Int = 11;
    public val REQUEST_CODE_HARD = 22;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentOut = Intent()    // 跳转出去的
        val intentIn:Intent = intent       // 跳转进来的

        intentOut.setClass(applicationContext, Main2Activity::class.java)

        // 简单
        btnEasy.setOnClickListener {
            intentOut.removeExtra("level")
            intentOut.putExtra("level", "easy")
            startActivityForResult(intentOut, REQUEST_CODE_EASY)

        }

        // 困难
        btnHard.setOnClickListener {
            intentOut.removeExtra("level")
            intentOut.putExtra("level", "hard")
            startActivityForResult(intentOut, REQUEST_CODE_HARD)
        }

        // 更新
        val result:String ? = intentIn.getStringExtra("result")
        if ("true" == result) {
            println("成功")
            tvMsg.text = "猜对了"
            ivMsg.setImageResource(R.drawable.success)
        }
        else if ("false" == result) {
            println("失败")
            tvMsg.text = "猜错了"
            ivMsg.setImageResource(R.drawable.faild)
        }
    }

    /**
     * 回调函数, 这里我只对超时提交进行处理
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode == -1 ) {
            tvMsg.text = "超时自动结束"
        }
    }


}
