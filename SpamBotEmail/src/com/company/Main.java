package com.company;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {




    public static void main(String[] args) {


        int repeatCount = 10; // amount of sms
        int delay = 4000;     // 4 sek

        SendEmail email = new SendEmail(repeatCount, delay);




        ExecutorService exec = Executors.newCachedThreadPool();

        // запускаем поток
        exec.execute(email);
        exec.shutdown();



    }



}
