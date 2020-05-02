package com.rocketmail.vaishnavanil.hackcovit.server.scraper;

import com.rocketmail.vaishnavanil.hackcovit.clientlib.DoctorData;
import com.rocketmail.vaishnavanil.hackcovit.server.QueryBuilder;
import javafx.util.Pair;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapeUtil {
    public void scrapeData(int year,int length){
        //ArrayList<String> idone = new ArrayList<String>();
        //ArrayList<String> idtwo = new ArrayList<String>();
        List<Pair<String,String>> idreg = new ArrayList<Pair<String, String>>();

        Document document = null;
        try {
            document = Jsoup.connect("https://mciindia.org/MCIRest/open/getPaginatedData?service=getPaginatedDoctor&draw=1&columns%5B0%5D%5Bdata%5D=0&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=true&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=1&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=2&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=3&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=4&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=5&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B6%5D%5Bdata%5D=6&columns%5B6%5D%5Bname%5D=&columns%5B6%5D%5Bsearchable%5D=true&columns%5B6%5D%5Borderable%5D=true&columns%5B6%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B6%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=0&order%5B0%5D%5Bdir%5D=asc&start=0&length="+length+"&search%5Bvalue%5D=&search%5Bregex%5D=false&year="+year+"&_=1588324175082")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(100000).ignoreContentType(true)
                    .get();
            String data = document.getElementsByTag("a").toString();

            String[] linedData = data.split("\n");
            for(String line:linedData){
                line = line.split("openDoctorDetailsnew")[1];
                line = line.split("\\\">View&lt;")[0];
                line = line.replace("('","").replace("')\\","");
                String[] sortedData = line.split("',\" '");
                loadDoctorData(sortedData[0],sortedData[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadDoctorData(String doctorID,String RegDValue){
        String reqBody = "{\"doctorId\":\"%DID%\",\"regdNoValue\":\"%REG%\"}";
        Connection.Response response = null;
        try {
            Connection con = Jsoup.connect("https://mciindia.org/MCIRest/open/getDataFromService?service=getDoctorDetailsByIdImr").header("Content-Type","application/json")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36")
                    .timeout(10 * 1000)
                    .method(Connection.Method.POST);
            //con.data("doctorId","12365635")
            //.data("regdNoValue","002025");
            con.requestBody(reqBody.replace("%DID%",doctorID).replace("%REG%",RegDValue));
            response = con.execute();

            Document document = response.parse();
            DoctorData obj =  new DoctorData(document.getElementsByTag("body").text());
            addToDataBase(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToDataBase(DoctorData d){
        QueryBuilder qb = new QueryBuilder();
        qb.insert(d.getDoctorName(),d.getRegistrationDate(),d.getParentName(),d.getBirthDate(),d.getDegree(),d.getUniversity(),d.getYearOfPassing(),d.getRegistrationNumber(),d.getStateCouncilName(),d.getAddress());
    }

}
