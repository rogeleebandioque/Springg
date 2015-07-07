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


public class ServletsDelete extends HttpServlet {
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println(startHtml);
        
        boolean exist = service.searchPersons(op.integerValid(request.getParameter("id_number")));
        if (!exist) {
            String mes = service.deletePersons(op.integerValid(request.getParameter("id_number")));
            out.print("<h1>"+mes+ "</h1>");
        } else {
            out.print("<h1> Person does not Exist</h1>");
        }
        out.println("<input type=\"button\" value=\"Back\" onClick=\"location.href='DeletePerson.html'\"/>");       
        out.println(endHtml);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
}    
