package com.rocketmail.vaishnavanil.hackcovit.clientlib;

import com.rocketmail.vaishnavanil.hackcovit.enums.State;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientQueryBuilder {

    Map<String,String> params = new HashMap<String, String>();
    public ClientQueryBuilder setDoctorName(String name){
        params.put("name",name);
        return this;
    }
    public ClientQueryBuilder setRegID(String ID){
        params.put("regID",ID);
        return this;
    }
    public ClientQueryBuilder setRegYear(String year){
        params.put("regY", year);
        return this;
    }
    public ClientQueryBuilder setState(State state){
        params.put("state",state.getCouncilName());
        return this;
    }





    public  JSONObject[] fetchData(String url){
        if(params.isEmpty())return null;
        Connection.Response response =
                null;
        try {
            Connection con = Jsoup.connect(buildUrl(url))
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36")
                    .timeout(10 * 1000)
                    .method(Connection.Method.GET);
            for(String key:params.keySet()){
                con.data(key,params.get(key));
            }
            response = con.followRedirects(true).execute();
            Document document = response.parse();
            JSONObject obj = new JSONObject(document.getElementsByTag("body").toString());
            if(obj.isEmpty())return null;
            DoctorData[] result = new DoctorData[obj.length()];
            int index = 0;
            for(String uuid:obj.keySet()){
                result[index++] = (DoctorData) obj.getJSONObject(uuid);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String buildUrl(String url){
        StringBuilder builder = new StringBuilder(url+"/hcvREST/doctors?");
        if(params.isEmpty())return "";

        for(String key:params.keySet()){
            builder.append(key);
            builder.append('=');
            builder.append(params.get(key));
            builder.append('&');
        }
        builder.setLength(builder.length()-1);

        return builder.toString().trim();
    }

}
