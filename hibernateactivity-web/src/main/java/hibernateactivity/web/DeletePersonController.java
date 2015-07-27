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
public class DeletePersonController extends SimpleFormController {
 
    private Service service;

    public void setService(Service service){
        this.service = service;
    } 

    private final Logger logger = LoggerFactory.getLogger(DeletePersonController.class);

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
        logger.debug("DeletePersontroller: showForm()");
        ModelAndView mav = new ModelAndView();
        String message = service.deletePersons(Integer.parseInt(request.getParameter("id")));
        //mav.setViewName("Main");
        mav.setView(new RedirectView("persons"));
        mav.addObject("person",service.getPerson());
        mav.addObject("msg", "User Deleted");        
        return mav;
    }      

/*    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{
        logger.debug("UpdatePersontroller: showForm()");
        return new ModelAndView("Main","person",service.getPerson());
    }
*/
}
 
