package edu.ahut.a299

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.ahut.a299.entity.Team
import edu.ahut.a299.service.TeamManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val left:Team = Team()
        val right:Team = Team()
        left.teamName = "雷霆"
        right.teamName = "火箭"

        tvNameLeft.text = left.teamName
        tvNameRight.text = right.teamName

        val intentOut:Intent = Intent()
        intentOut.setClass( applicationContext, Main2Activity::class.java )

        btnLeft.setOnClickListener {
            TeamManager.addScore( left )
            // 视图更新
            tvLeftScore.text = left.teamScore.toString()
        }

        btnRight.setOnClickListener {
            TeamManager.addScore( right )
            // 视图更新
            tvRightScore.text = right.teamScore.toString()
        }

        btnOK.setOnClickListener {
            val result = TeamManager.compareScore(left, right)

            intentOut.putExtra("result", result)
            intentOut.putExtra("leftName", left.teamName)
            intentOut.putExtra("rightName", right.teamName)
            startActivity(intentOut)
            finish()
        }

    }
}
