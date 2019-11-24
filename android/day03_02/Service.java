package com.example.day03_02;

import java.util.Random;

public class Service {


    public static int generateRandomByLevel(String level) {
        if ( "easy".equals(level) ) {
            return random(50);
        }

        else if ( "hard".equals(level) ) {
            return random(100);
        }
        return -1;
    }

    public static int random( int max ) {
        return new Random().nextInt(max) + 1;
    }

    public static boolean compare( int a, int b ) {
        return a == b;
    }
}
