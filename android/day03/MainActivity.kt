package com.example.day03_task_01


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // order对象
    private val order = Order()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 增加咖啡数目
         */
        btnAdd.setOnClickListener {
            Service.increaseAmountCoffee(order)
            updateView()
        }

        /**
         * 减少咖啡数目
         */
        btnSub.setOnClickListener {
            Service.decreaseAmountCoffee(order)
            updateView()
        }

        /**
         * 订单提交
         */
        btnOk.setOnClickListener {
            Service.generateOrder(order, edtName.text.toString())

            if( order.customName == "" || order.customName.startsWith(" ") ) {
                tvResult.text = "你还没填写用户名，不能生成订单！"
                return@setOnClickListener
            }

            if( order.amountCoffee == 0 ) {
                tvResult.text = "你还没选择咖啡的数目，不能生成订单！"
                return@setOnClickListener
            }

            tvResult.text = "你的订单数据：\n用户名：\t" + order.customName + "\n咖啡数目: \t" + order.amountCoffee
        }
    }

    private fun updateView() {
//        tvCount.setText( order.amountCoffee )
        tvCount.text = order.amountCoffee.toString()
//        tvResult.setText("你的订单是：\n" + order.toString())
    }
}
