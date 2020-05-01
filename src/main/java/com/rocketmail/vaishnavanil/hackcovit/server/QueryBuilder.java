package com.rocketmail.vaishnavanil.hackcovit.server;

import org.json.JSONObject;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueryBuilder {
    Map<String,String> params = new HashMap<String, String>();
    public QueryBuilder setDoctorName(String name){
        params.put("name",name);
        return this;
    }
    public QueryBuilder setRegID(String ID){
        params.put("registrationNo",ID);
        return this;
    }
    public QueryBuilder setRegYear(String year){
        params.put("regYear", year);//ADD AS EXTRA TO DB FROM YEAR   !!IMPORTANT TODO::
        return this;
    }
    public QueryBuilder setState(String state){
        params.put("state",state);
        return this;
    }


    public String execute(){
        Connection c = getConnection();
        Statement statement = null;

        try {
            statement = c.createStatement();
            JSONObject container = new JSONObject();
            ResultSet res = statement.executeQuery(getStatement());
            ResultSetMetaData meta = res.getMetaData();
            int colCount = meta.getColumnCount();
            while(res.next()){
                JSONObject doctorData = new JSONObject();
                for (int column = 1; column <= colCount; ++column)
                {
                    doctorData.put(meta.getColumnName(column),res.getString(column));
                }
                container.put(UUID.randomUUID().toString(),doctorData);
            }
            return container.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getStatement(){
        StringBuilder statement = new StringBuilder("SELECT * FROM dataTable WHERE " );
        if(params.isEmpty())return null;
        for(String key:params.keySet()){
            statement.append(key);
            statement.append("='");
            statement.append(params.get(key));
            statement.append("'");
        }

        return statement.toString();
    }

    private static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+ dbName,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    static String ip = "-";
    static  String port = "-";
    static String dbName = "-";
    static String userName = "-";
    static String password = "-";


}
