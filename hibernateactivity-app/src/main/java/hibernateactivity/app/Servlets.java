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
import org.apache.commons.lang3.StringUtils;

public class Servlets extends HttpServlet {
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
                    "<script src=\"Servlets.js\"></script></head>\n" +
                    "<body><div id=\"container\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n";
        endHtml = "</div></body></html>";

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(startHtml);
        out.println(op.createFields());
        out.println(op.searchBox());
        String searchQ = request.getParameter("search");
        if(!StringUtils.isEmpty(searchQ)){
            List<Person> searchList = service.searchPerson(searchQ);
            out.println(op.displaySearch(searchList));
        }
        List<Person> person = service.getPersons("last_name", "asc");
        out.println(op.displayPerson(person, "Last Name"));
        out.println(op.listBy());
        out.println(endHtml);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(startHtml);
        out.println(op.createFields());
        out.println(op.searchBox());
        String choice = request.getParameter("todo").toLowerCase();
        switch (choice) {
            case "person":
                List<Person> person = service.getPersons(request.getParameter("listBy"), request.getParameter("order"));
                out.println(op.displayPerson(person,request.getParameter("listBy")));
                break;

            case "role":
                Integer categ = op.integerValid(request.getParameter("listBy"));
                Roles roles = service.getByRole(categ);
                out.println(op.displayRole(roles,categ));
                break;
        }
        
        out.println(op.listBy());   
        out.println(endHtml);
    }
}    
