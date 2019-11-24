package com.example.day03_task_01;

public class Service {

    public static void increaseAmountCoffee(Order order) {
        order.setAmountCoffee(order.getAmountCoffee() + 1);
    }

    public static void decreaseAmountCoffee(Order order) {
        if ( order.getAmountCoffee() <= 0 )
            return;
        order.setAmountCoffee(order.getAmountCoffee() - 1);
    }

    // 暂时只写咖啡数据
    public static void generateOrder(Order order, String customname) {
        // 只要设置用户名
        order.setCustomName(customname);
    }

}
