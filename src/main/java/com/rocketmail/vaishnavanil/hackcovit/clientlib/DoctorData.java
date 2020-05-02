package com.rocketmail.vaishnavanil.hackcovit.clientlib;

import org.json.JSONObject;

public class DoctorData extends JSONObject {

    public DoctorData(String s){
        super(s);
    }
    public String getDoctorName(){
        return getString("firstName");
    }
    public String getRegistrationDate(){
        return getString("regDate");
    }

    public String getParentName(){
        return getString("parentName");
    }
    public String getBirthDate(){
        return getString("birthDateStr");
    }
    public String getDegree(){
        return getString("doctorDegree");
    }
    public String getUniversity(){
        return getString("university");
    }
    public String getYearOfPassing(){
        return getString("yearOfPassing");
    }
    public String getRegistrationNumber(){
        return getString("registrationNo");
    }
    public String getStateCouncilName(){
        return getString("smcName");
    }
    public String getAddress(){
        return getString("address");
    }
}
