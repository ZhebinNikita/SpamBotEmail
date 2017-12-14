package com.company;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {




    public static void main(String[] args) {

        int i = 0;
        int repeatCount = 10; // amount of sms
        int delay = 4000;     // 4 sek

        // массив высылаемых сообщений в зависимости от размера списка адрессов почт
        SendEmail[] email = new SendEmail[EmailData.adresses.length];
        for(i = 0; i < EmailData.adresses.length; i++)
            email[i] = new SendEmail(repeatCount, delay, i); // i - итератор для определения почтового ящика в списке


        ExecutorService exec = Executors.newCachedThreadPool();

        // запускаем по потоку на каждую почту
        for(i = 0; i < EmailData.adresses.length; i++)
            exec.execute(email[i]);


        exec.shutdown();
    }



}
