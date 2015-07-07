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

public class ServletsEdit extends HttpServlet {
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

        boolean existEdit = service.searchPersons(op.integerValid(request.getParameter("id_number")));
        if (!existEdit) {
            Person people = service.getPersons(op.integerValid(request.getParameter("id_number")));
            String mes = op.editForm(people);
            out.println(mes);                    
            
        } else {
            out.print("<h1> Person does not Exist</h1>");
            out.println("<input type=\"button\" value=\"Back\" onClick=\"location.href='Servlets'\"/>");            
        }
        out.println(endHtml);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(startHtml);

        Person people = service.getPersons(op.integerValid(request.getParameter("person")));
        Name n = people.getNames();
        n.setFirst_name(request.getParameter("first_name"));
        n.setLast_name(request.getParameter("last_name"));
        people.setAddress(request.getParameter("address"));
        people.setAge(op.integerValid(request.getParameter("age")));
        people.setGender(request.getParameter("gender"));
        people.setBday(op.dateValid(request.getParameter("bday")));
        people.setGrade(op.integerValid(request.getParameter("grade")));
        people.setDate_hired(op.dateValid(request.getParameter("date_hired")));
        people.setCurrently_employed(request.getParameter("currently_employed"));

        String[] upcontactDet = request.getParameterValues("contactDetail");
        String[] upcontactTyp = request.getParameterValues("contactType");
        String[] uprole = request.getParameterValues("roles");
        
        Set<Contacts> upcontacts = op.contactDetails(upcontactDet,upcontactTyp);
        people.setContact(upcontacts);
        people.setRole(op.addRole(uprole));            
        
        String message = service.updatePersons(people);              
        out.print("<h1>"+message+ "</h1>");  
        out.println("<input type=\"button\" value=\"Back\" onClick=\"location.href='EditPerson.html'\"/>");        
        out.println(endHtml);
    }


}    
