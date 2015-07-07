package hibernateactivity.app;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import hibernateactivity.app.Operations;

public class ServletsAdd extends HttpServlet {
    private Service service;
    private Operations op;
    private String title;
    private String startHtml;
    private String endHtml;
    public void init() throws ServletException{
        service = new Service();
        op = new Operations();
        title = "Servlets Activity";
        startHtml = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"+
                    "<html>\n" +
                    "<head><title>" + title + "</title>" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"servlets.css\"/>" + 
                    "<script src=\"servlets.js\"></script></head>\n" +
                    "<body><div id=\"container\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n";
        endHtml = "</div></body></html>";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(startHtml);
        String choicePost = request.getParameter("todo").toLowerCase();

        Name name = new Name(request.getParameter("first_name"),
                            request.getParameter("last_name"));
        Person addPer = new Person(name, request.getParameter("address"),
                                        op.integerValid(request.getParameter("age")),
                                        request.getParameter("gender"),
                                        op.dateValid(request.getParameter("bday")),
                                        op.integerValid(request.getParameter("grade")),
                                        op.dateValid(request.getParameter("date_hired")),
                                        request.getParameter("currently_employed"));
        String[] contactDet = request.getParameterValues("contactDetail");
        String[] contactTyp = request.getParameterValues("contactType");
        String[] role = request.getParameterValues("roles");
        
        Set<Contacts> contacts = op.contactDetails(contactDet,contactTyp);
        addPer.setContact(contacts);
        addPer.setRole(op.addRole(role));                
        out.println("<h1>" + service.addPersons(addPer) + "</h1>");
        out.println("<input type=\"button\" value=\"Back\" onClick=\"location.href='AddPerson.html'\"/>");
                
        out.println(endHtml);
    }


}    
