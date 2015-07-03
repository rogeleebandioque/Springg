package hibernateactivity.app;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Name;

// Extend HttpServlet class
public class Servlets extends HttpServlet {

    // Method to handle GET method request.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "List of Persons";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";

        String listBy = request.getParameter("display");
        String orderBy = "asc";

        out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body>\n" +
        "<h1 align=\"center\">" + title + "</h1>\n" +
        "<table border=\"1\">\n");
        Service service = new Service();
        List<Person> person = service.getPersons(listBy, orderBy);
        
        out.println(displayPerson(person));
        out.print("\n </body> \n </html>");

    }
    // Method to handle POST method request.
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static String displayPerson(List<Person> person) {
        String words = "<tr><th>Name</th><th>Grade</th><th>Date Hired</th><th>Contact Number</th></tr>";
        for(Person persons: person){
            Name name = persons.getNames();
            words = words +"<tr><td>"+ name.getFirst_name() + " " + name.getLast_name()+"</td>" + 
                           "<td>"+ persons.getGrade() +"</td>" +
                           "<td>"+ persons.getDate_hired() +"</td> <td>";

            for(Contacts contact:persons.getContact()){
                words = words + contact.getType() + ": " + contact.getContact() + " \n" ;
            }     
            words = words + "</td></tr>";
        }
        words = words + "</table>";
        return words;
    }
}

