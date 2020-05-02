package com.rocketmail.vaishnavanil.hackcovit.server;





import com.rocketmail.vaishnavanil.hackcovit.server.scraper.ScrapeUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class RESTServer {
    public static void main(String[] args) {
        new ScrapeUtil().scrapeData();
        //new RESTServer();

    }

    public RESTServer(){
        setPort(1234);
        get("/hcvREST/doctors",new Route() {
            @Override
            public Object handle(Request request, Response response) {
                String registrationID = request.queryParams("regID");
                String registrationYear = request.queryParams("regY");
                String name = request.queryParams("name");
                String state = request.queryParams("state");

                //No data provided
                if(registrationID==null && registrationYear==null && name==null && state == null){
                    response.status(400);
                    return "";
                }



                response.status(200);
                QueryBuilder query = new QueryBuilder();
                if(registrationID!=null)query.setRegID(registrationID);
                if(registrationYear!=null)query.setRegYear(registrationYear);
                if(name!=null)query.setDoctorName(name);
                if(state!=null)query.setState(state);
                return query.execute();
            }
        });
    }
}
