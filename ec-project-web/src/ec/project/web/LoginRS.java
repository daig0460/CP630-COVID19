package ec.project.web;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@RequestScoped
public class LoginRS {

//	@EJB
//	StatsEJBStatelessLocal sstateless;
//	
//	@GET
//    @Path("/countjson")
//    @Produces({ "application/json" })
//    public String countJSon() {
//        return "{\"Count\":\"" + sstateless.getCount() + "\"}";
//    }
//	
//	@GET
//    @Path("/minjson")
//    @Produces({ "application/json" })
//    public String minJSon() {
//        return "{\"Min\":\"" + sstateless.getMin() + "\"}";
//    }
//	
//	@GET
//    @Path("/maxjson")
//    @Produces({ "application/json" })
//    public String maxJSon() {
//        return "{\"Max\":\"" + sstateless.getMax() + "\"}";
//    }
//	
//	@GET
//    @Path("/meanjson")
//    @Produces({ "application/json" })
//    public String meanJSon() {
//        return "{\"Mean\":\"" + sstateless.getMean() + "\"}";
//    }
//	
//	@GET
//    @Path("/stdjson")
//    @Produces({ "application/json" })
//    public String stdJSon() {
//        return "{\"StdDev\":\"" + sstateless.getSTD() + "\"}";
//    }
//	
//	@GET
//    @Path("/summaryjson")
//    @Produces({ "application/json" })
//    public String summaryJSon() {
//        return "{\"Count\":\"" + sstateless.getCount() + "\"" +
//        	   ", \"Min\":\"" + sstateless.getMin() + "\"" +
//        	   ", \"Max\":\"" + sstateless.getMax() + "\"" +
//        	   ", \"Mean\":\"" + sstateless.getMean() + "\"" +
//        	   ", \"StdDev\":\"" + sstateless.getSTD() + 
//        	   "\"}";
//    }
	
}
