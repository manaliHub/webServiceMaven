package com.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entity.Student;
import com.repository.MyRepositoryJDBC;
import com.repository.MyRepositoryJPA;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	
	//for JDBC
	MyRepositoryJDBC repo =  new MyRepositoryJDBC();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
   @GET
   @Produces(MediaType.APPLICATION_XML)
   @Path("student/{id}") 
   //run on browser : http://localhost:8080/DemoWS/webapi/myresource/student/1
   public Object getStudentWithId(@PathParam("id") int stId) {
    	String name = MyRepositoryJDBC.findData(stId).toString();
	   //String name = MyRepositoryJPA.findData(stId).toString();
    	System.out.println("Student with Id "+stId+" has name "+ name);
        return name;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("student")
    //run on browser : http://localhost:8080/DemoWS/webapi/myresource
    public Object getStudentWithOne() {
    	//String name = MyRepositoryJDBC.findData(1).toString();
    	String name = MyRepositoryJPA.findData(1).toString();
    	System.out.println("Student with Id "+1+" has name "+ name);
        return name;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("student")
    
    /* select POST
     * run on broser : http://localhost:8080/DemoWS/webapi/myresource/student
     * 
     * and type below in body -> row -> xml :
     * <student>
	 * <sId>3</sId>
	 * <name>Milind</name>
	 * <phn>333</phn>
     * </student>	
     */
    public Object saveStudent(Student st) {
    	//Object obj = MyRepositoryJDBC.persistData(st);
    	Object obj = MyRepositoryJPA.persistData(st);
    	System.out.println("Student with Id "+st.getsId()+" has been saved ");
        return obj;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("student/{id}")
    // run on browser : http://localhost:8080/DemoWS/webapi/myresource/student/3 with PUT
    public Object updateStudent(@PathParam("id") int stId) {
    	//Object obj = MyRepositoryJDBC.updateData(stId);
    	Object obj = MyRepositoryJPA.updateData(stId);
    	System.out.println("Student with Id "+stId+" has been updated ");
        return obj;
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_XML)
    @Path("student/{id}")
    public Object removeStudent(@PathParam("id") int stId) {
    	//Object obj = MyRepositoryJDBC.removeData(stId);
    	Object obj = MyRepositoryJPA.removeData(stId);
    	System.out.println("Student with Id "+stId+" has been removed ");
        return obj;
    }
    
    
}
