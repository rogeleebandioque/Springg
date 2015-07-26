package hibernateactivity.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Operations {
    
    public Set contactDetails(String[] cDetails, String[] cType) {
        Set cD = new HashSet();
        for (int i=0; i<cDetails.length; i++) {
            Contacts c = new Contacts(cDetails[i],cType[i].toLowerCase());
            cD.add(c);
        }
        return cD;
    }
    
    public Set<Roles> addRole(String[] roles) {
        Set r = new HashSet();       
        for(int i=0; i<roles.length;i++) {
            switch(roles[i].toLowerCase()) {
                case "police": 
                    r.add(new Roles(1,"Police"));
                    break;
                case "politician":
                    r.add(new Roles(2,"Politician"));
                    break;
                case "soldier":
                    r.add(new Roles(3,"Soldier"));
                    break;
                case "celebrity":
                    r.add(new Roles(4,"Celebrity"));
                    break;
                case "worker":
                    r.add(new Roles(5,"Worker"));
                    break;
            }
        }
        return r;
    }

    public Date dateValid(String date) {
        DateValidator dateVal = DateValidator.getInstance();
        Date dt = null;                    
        
        dt = dateVal.validate(date, "yyyy-MM-dd");
        if(dt == null) {
            date = "0001-01-01";
            dt = dateVal.validate(date, "yyyy-MM-dd");
        } 
        return dt; 
    }

    public int integerValid(String in) {
        boolean a = true;
        int input = 0;
        
            try {
                input = Integer.parseInt(in);
            } catch(NumberFormatException e) {
                input = 100;
            }
        return input;
    }

    public boolean patternMatch(String num) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(num);
        boolean matches = matcher.matches();

        return matches;
    }
}
