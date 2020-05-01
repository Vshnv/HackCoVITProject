package com.rocketmail.vaishnavanil.hackcovit.enums;

import org.apache.commons.text.WordUtils;

public enum State {
    ANDHRA_PRADESH,
    ARUNACHAL_PRADESH,
    ASSAM,
    BIHAR,
    DELHI,
    GOA,
    GUJARAT,
    HARYANA,
    HIMACHAL_PRADESH,
    JAMMU_n_KASHMIR,
    JHARKHAND,
    KARNATAKA,
    MADHYA_PRADESH,
    MAHARASHTRA,
    MANIPUR,
    MIZORAM,
    NAGALAND,
    ORISSA,
    PUNJAB,
    RAJASTHAN,
    SIKKIM,
    TAMIL_NADU,
    TELANGANA,
    KERALA,
    TRIPURA,
    UTTARAKHAND,
    UTTAR_PRADESH,
    WEST_BENGAL
    ;

    public String getCouncilName() {
        return  fixCase(name().replace("KERALA","Travancore Cochin").replace("_n_"," & ").replace("_"," ")).trim() + " Medical Council";
    }

    private static String fixCase(String s){
        return WordUtils.capitalizeFully(s);
    }
}
