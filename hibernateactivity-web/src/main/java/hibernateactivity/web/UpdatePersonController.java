package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.ObjectError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Roles;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.web.Operations;

@SuppressWarnings("deprecation")
public class UpdatePersonController extends SimpleFormController {
 
    private Service service;
    private Operations operations;
    private final Logger logger = LoggerFactory.getLogger(UpdatePersonController.class);

    public void setService(Service service){
        this.service = service;
    } 

    public void setOperations(Operations operations){
        this.operations = operations;
    }

    protected void initBinder(HttpServletRequest request,
                          ServletRequestDataBinder binder)
                   throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));    
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
        logger.debug("UpdatePersontroller: showForm()");
        Map model = errors.getModel();
        System.out.println(model.size());
        Person person = (Person)model.values().iterator().next();
        Set<Roles> roles = person.getRole();
        Set<Contacts> contact = person.getContact();
        Map map = new HashMap();

        populateModel(map);
        map.put("roles", roles);
        map.put("contact", contact);

        model.putAll(map);
        return new ModelAndView("UserForm",model);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception{
        logger.debug("UpdatePersonController: formBackingObject()");
        Person personForm = service.getIndividual(Integer.parseInt(request.getParameter("id")));
        System.out.println("form backing object");
        return personForm;
    }
     
    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{
        logger.debug("UpdatPersonController: onSubmit()");
        ModelAndView mav = new ModelAndView();
        Person person = (Person) command;
        String[] type = request.getParameterValues("contactType");
        String[] details = request.getParameterValues("contactDetail");
        String[] roles = request.getParameterValues("r");

        Set<Roles> r = new HashSet();        
        Set<Contacts> c = new HashSet();

        if(roles!=null){
            r = operations.addRole(roles);
            person.setRole(r);        
        }
        if(details!=null){
            c = operations.contactDetails(details, type);
            person.setContact(c);
        }

        if (errors.hasErrors()){
            return new ModelAndView("UserForm", "person", person);
        } 
        service.updatePersons(person);        
        mav.setViewName("Main");
        mav.addObject("person", service.getPerson());
        mav.addObject("msg", "User updated successfully!");
    
        return mav;
    }

    public void populateModel(Map map){
        List<String> roleId = new ArrayList<String>();
        roleId.add("Police");
        roleId.add("Politician");
        roleId.add("Soldier");
        roleId.add("Celebrity");
        roleId.add("Worker");
        map.put("roleId", roleId);
    }
}
 
