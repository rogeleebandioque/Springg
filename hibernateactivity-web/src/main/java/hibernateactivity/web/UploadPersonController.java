package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.FileUtils;


import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import hibernateactivity.core.model.FileUpload;
import hibernateactivity.web.Operations;

@SuppressWarnings("deprecation")
public class UploadPersonController extends SimpleFormController {

    private Service service;
    private Operations operations;
    private final Logger logger = LoggerFactory.getLogger(UploadPersonController.class);

    public void setOperations(Operations operations){
        this.operations = operations;
    }

    public void setService(Service service){
        this.service = service;
    }      

    protected void initBinder(HttpServletRequest request,
                          ServletRequestDataBinder binder)
                   throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
    }

    protected ModelAndView showForm(HttpServletRequest request,
                                HttpServletResponse response,
                                BindException errors)
                         throws Exception {
        logger.debug("UploadPersonController: showForm()");
        Map model = errors.getModel();
        Person p = (Person)model.values().iterator().next();
        
        Map add = new HashMap();
        populateModel(add);
        add.put("roles", p.getRole());
        add.put("contact", p.getContact());
        model.putAll(add);
        return new ModelAndView("UserForm", model);

    }

    protected Object formBackingObject(HttpServletRequest request)
                            throws Exception{
        logger.debug("UploadPersonController: formBackingObject()");
        Person personForm = new Person();
        Name n = personForm.getNames();
        Set<Contacts> c = personForm.getContact();
        Set<Roles> r = personForm.getRole();
        File myFile = new File("Uploaded Files/"+request.getParameter("name"));
        List<String> personDetails = FileUtils.readLines(myFile);
            
                for(String details: personDetails){
                    System.out.println(details);
                    String[] detail = details.split(":");        
                    switch (detail[0]){
                        case "first name":
                            n.setFirst_name(detail[1]);
                            break;

                        case "last name":
                            n.setLast_name(detail[1]);
                            break;

                        case "address":
                            personForm.setAddress(detail[1]);
                            break;

                        case "age":
                            personForm.setAge(operations.integerValid(detail[1]));
                           break;

                        case "grade":
                            personForm.setGrade(operations.integerValid(detail[1]));
                           break;

                        case "birthday":
                            personForm.setBday(operations.dateValid(detail[1]));
                            break;

                        case "contact":
                            String[] contacts = detail[1].split("=");
                            c.add(new Contacts(contacts[1],contacts[0]));
                            break;

                        case "gender":
                            personForm.setGender(detail[1]);
                            break;

                        case "date hired":
                            personForm.setDate_hired(operations.dateValid(detail[1]));
                            break;

                        case "currently employed":
                            personForm.setCurrently_employed(detail[1]);
                            break;

                        case "role":
                            switch(detail[1]){
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
                            break;
                            
                        default:
                            break;
                    }//switch
                }
        return personForm;
    }

    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{
        logger.debug("UploadPersonController: onSubmit()");         
        Person person = (Person) command;
        Set<Roles> r = new HashSet();        
        Set<Contacts> c = new HashSet();
        String[] detail = request.getParameterValues("contactDetail");
        String[] type = request.getParameterValues("contactType");
        String[] roles = request.getParameterValues("r");

        if(roles!=null){
            r = operations.addRole(roles);
            person.setRole(r);        
                
        }
        if(detail!=null){
            c = operations.contactDetails(detail, type);
            person.setContact(c);
        }

        if (errors.hasErrors()){
            return new ModelAndView("UserForm","person",person);   
        }
        service.addPersons(person);
        Map mav = new HashMap();
        mav.put("msg", "Person added successfully!");
        mav.put("person", service.getPerson());

        return new ModelAndView("Main",mav);
    }

    public void populateModel(Map add){
        List<String> roleId = new ArrayList<String>();
        roleId.add("Police");
        roleId.add("Politician");
        roleId.add("Soldier");
        roleId.add("Celebrity");
        roleId.add("Worker");
        add.put("roleId", roleId);
    }
}
 
