package com.netpay.kafkaConsumerMicroService.util;

public enum CategoryEnum {

    AQUAMARINE("AQUAMARINE"),
    VIOLET("VIOLET"),
    RED("RED"),
    ORANGE("ORANGE"),
    INDIGO("INDIGO");

    public final String categoryName;

     private CategoryEnum(String categoryName) {
         this.categoryName=categoryName;
    }
}
