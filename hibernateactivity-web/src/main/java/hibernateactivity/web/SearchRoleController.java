package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.view.RedirectView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.model.Roles;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.web.Operations;

@SuppressWarnings("deprecation")
public class SearchRoleController extends SimpleFormController {
 
    private Service service;

    public void setService(Service service){
        this.service = service;
    } 
    private final Logger logger = LoggerFactory.getLogger(SearchRoleController.class);

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
        logger.info("SearchRoleController: showForm()");
        ModelAndView mav = new ModelAndView();
        String listBy = request.getParameter("listBy");
        Integer orders = Integer.parseInt(listBy);
        Roles pr = service.getByRole(orders);
        Set<Person> persons = pr.getPersonRole();

        return new ModelAndView("Main","person",persons);
    }      
/*
    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{
        logger.info("SearchRoleController: onSubmit()");
        return new ModelAndView("Main","person",service.getPerson());
    }
*/
}
 
