package com.example.day03_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private val RESULT_CODE_SUCCESS = 1
    private val RESULT_CODE_FAILED = 0
    private val RESULT_CODE_TIMEUP = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // 启动计时器
        countDownTimer.start()

        val intentIn:Intent = intent
        val intentOut = Intent()
        intentOut.setClass(applicationContext, MainActivity::class.java)

        val level:String?= intentIn.getStringExtra("level")

        // 默认在xml中配置0-100
        if ( level == "easy" ) {
            tvTips.text = "请输入0-50的数字"
        }

        val actual:Int = Service.generateRandomByLevel( level )     // 根据等级
        println("==========随机值=============  " + actual + " ==============")


        btnStart.setOnClickListener {
            // 获取输入
            val input:Int = edtNum.text.toString().toInt()
            // 猜对了
            if ( Service.compare( input, actual ) ) {
                // 传回信号
                intentOut.putExtra("result", "true")
                setResult(RESULT_CODE_SUCCESS, intentOut)
                startActivity(intentOut)
                finish()
            }
            else {
                // 传回信号
                intentOut.putExtra("result", "false")
                setResult(RESULT_CODE_FAILED, intentOut)
                startActivity(intentOut)
                finish()
            }
        }

    }


    /**
     * 计时器
     */
    private val countDownTimer = object : CountDownTimer(30000, 1000) {
        override fun onFinish() {
            setResult(RESULT_CODE_TIMEUP)
            finish()
        }

        override fun onTick(millisUntilFinished: Long) {
            tvCont.text = (millisUntilFinished / 1000).toString()
        }
    }
}
