package com.rocketmail.vaishnavanil.hackcovit.clientlib;

import org.json.JSONObject;

public class DoctorData extends JSONObject {

    public DoctorData(String s){
        super(s);
    }
    public String getDoctorName(){
        return String.valueOf(get("firstName"));
    }
    public String getRegistrationDate(){
        return String.valueOf(get("regDate"));
    }

    public String getParentName(){
        return String.valueOf(get("parentName"));
    }
    public String getBirthDate(){
        return String.valueOf(get("birthDateStr"));
    }
    public String getDegree(){
        return String.valueOf(get("doctorDegree"));
    }
    public String getUniversity(){
        return String.valueOf(get("university"));
    }
    public String getYearOfPassing(){
        return String.valueOf(get("yearOfPassing"));
    }
    public String getRegistrationNumber(){
        return String.valueOf(get("registrationNo"));
    }
    public String getStateCouncilName(){
        return String.valueOf(get("smcName"));
    }
    public String getAddress(){
        return String.valueOf(get("address"));
    }
}
