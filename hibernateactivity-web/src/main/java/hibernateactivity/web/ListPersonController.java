package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;

@SuppressWarnings("deprecation")
public class ListPersonController extends SimpleFormController {
 
    private Service service;

    public void setServices(Service service){
        this.service = service;
    }      
	
    protected ModelAndView onSubmit(Object command) throws Exception {
        return new ModelAndView("Main","person",service.getPersons());
    }
}
 
