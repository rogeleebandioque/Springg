package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import hibernateactivity.web.Operations;

@SuppressWarnings("deprecation")
public class AddPersonController extends SimpleFormController {

    private Service service;
    private Operations operations;
    private final Logger logger = LoggerFactory.getLogger(AddPersonController.class);

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
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    protected ModelAndView showForm(HttpServletRequest request,
                                HttpServletResponse response,
                                BindException errors)
                         throws Exception {
        logger.debug("AddPersonController: showForm()");
        Map model = errors.getModel();
        Map add = new HashMap();
        populateModel(add);

        model.putAll(add);
        return new ModelAndView("UserForm", model);

    }

    protected Object formBackingObject(HttpServletRequest request)
                            throws Exception{
        logger.debug("AddPersonController: formBackingObject()");
        Person personForm = new Person();
        return personForm;
    }

    protected ModelAndView processFormSubmission(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Object command,
                                             BindException errors)
                                      throws Exception {
        ModelAndView mav = new ModelAndView();
        Person person = (Person) command;
        String[] type = request.getParameterValues("contactType");
        String[] details = request.getParameterValues("contactDetail");
        String[] roles = request.getParameterValues("r");
    
        Set<Roles> r = new HashSet();        
        Set<Contacts> c = new HashSet();

        if(roles!=null){
            logger.debug("AddPersonController: processFormSubmission");
            r = operations.addRole(roles);
            person.setRole(r);        
        }
        if(details!=null){
            c = operations.contactDetails(details, type);
            person.setContact(c);
        }

        if (errors.hasErrors()){
        System.out.println("error");
            List<ObjectError> e = errors.getAllErrors();
            System.out.println(person.getId());
            for(ObjectError er: e){
                System.out.println(er);
            }
            return showForm(request,response,errors);
        } 
        service.updatePersons(person);        
        mav.setViewName("Main");
        mav.addObject("msg", "User updated successfully!");
    
        return mav;   
    }

    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{ 
        logger.debug("AddPersonController: onSubmit()");
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
        String mess = service.addPersons(person);
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
 
