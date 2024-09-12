package com.itbuka.seckill.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format=simpleDateFormat.format(date);
        System.out.println(format);
//        System.out.println(date);
    }
}
