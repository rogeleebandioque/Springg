package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.web.Operations;

@SuppressWarnings("deprecation")
public class ListPersonController extends SimpleFormController {

    private Service service;
    private final Logger logger = LoggerFactory.getLogger(ListPersonController.class);

    public void setService(Service service){
        this.service = service;
    }      

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
        logger.info("ListPersonController: showForm()");
        return new ModelAndView("Main","person",service.getPerson());
    } 

    protected ModelAndView onSubmit(Object command) throws Exception {
        logger.info("ListPersonController: onSubmit()");
        return new ModelAndView("Main","person",service.getPerson());
    }
}
 
