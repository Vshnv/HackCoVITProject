package com.rocketmail.vaishnavanil.hackcovit.test;

import com.rocketmail.vaishnavanil.hackcovit.clientlib.ClientQueryBuilder;
import com.rocketmail.vaishnavanil.hackcovit.clientlib.DoctorData;

public class Test {
    public static void main(String[] args) {
        ClientQueryBuilder builder = new ClientQueryBuilder();
        builder.setDoctorName("SINHA ROHAN");
        DoctorData[] data = builder.fetchData("https://vshnv.pagekite.me");
    }
}
