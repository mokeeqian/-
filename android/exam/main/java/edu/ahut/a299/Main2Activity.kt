package edu.ahut.a299

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intentIn = intent

        val result = intentIn.getIntExtra("result", 0)

        // 左边赢
        if ( result == 1 ) {
            ivRes.setImageResource(R.drawable.okc)
            tvRes.text = intentIn.getStringExtra("leftName") + " 赢"
        }

        //右边
        else if ( result == -1 ) {
            ivRes.setImageResource(R.drawable.rocket)
            tvRes.text = intentIn.getStringExtra("rightName") + " 赢"
        }
        else {
            tvRes.text = "双方平局"
        }

    }
}
