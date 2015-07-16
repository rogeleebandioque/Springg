package hibernateactivity.web;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import hibernateactivity.core.model.Person;
import hibernateactivity.core.model.Contacts;
import hibernateactivity.core.service.Service;
import hibernateactivity.core.model.Name;
import hibernateactivity.core.model.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernateactivity.web.Operations;
import hibernateactivity.core.model.FileUpload;

@Controller
public class ActivityController{
    private Operations operations;    
    private Service service;

    public void setOperation(Operations operations){
        this.operations = operations;
    }    

    public void setService(Service service){
        this.service = service;
    }    
        
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @RequestMapping(value = "AddPerson", method = RequestMethod.GET)
	public String userForm(Model model) {
        logger.debug("userForm()");

		Person person = new Person();
		model.addAttribute("personForm", person);
        FileUpload fileModel = new FileUpload();
        model.addAttribute("file", fileModel);
        populateModel(model);
        return "UserForm";
	}

    @RequestMapping(value="/UploadForm",method = RequestMethod.POST)
    public String fileUploaded(Model model, FileUpload file, final RedirectAttributes redirectAttributes) {
        logger.debug("fileUploaded()");           
        MultipartFile multipartFile = file.getFile();
        redirectAttributes.addFlashAttribute("msg", "File Uploaded!");
        
        return "redirect:Persons";
    } 

	@RequestMapping(value ="Persons", method = RequestMethod.GET)
    public String displayPerson(ModelMap model) {
        model.addAttribute("person", service.getPersons());
        logger.debug("displayPerson()");
        return "Main";
    }

	@RequestMapping(value ="SaveUpdate", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("personForm") Person person,
                                    BindingResult result, 
                                    @RequestParam(value="r",required=false) String[] roles,
                                    @RequestParam(value="contactDetail",required=false) String[] detail,
                                    @RequestParam(value="contactType", required=false) String[] type,
                                    Model model,
                                    final RedirectAttributes redirectAttributes) {
        logger.debug("saveOrUpdateUser()");        
        Set<Roles> r = new HashSet();        
        Set<Contacts> c = new HashSet();
        
        if(roles!=null){
            r = operations.addRole(roles);
            person.setRole(r);        
        }
        if(detail!=null){
            c = operations.contactDetails(detail, type);
            person.setContact(c);
        }

        person.setBday(operations.dateValid("1994-11-08"));
        person.setDate_hired(operations.dateValid("1994-11-08"));        

        if(person.isNew()){
            redirectAttributes.addFlashAttribute("msg", "User added successfully!");
            service.addPersons(person);
        }else{
            redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
            service.updatePersons(person);        
        }

        return "redirect:Persons";

    }

  	@RequestMapping(value ="SearchPerson", method = RequestMethod.GET)
    public String searchPerson(String view, @RequestParam(value="listBy",defaultValue="grade") String listBy, 
                @RequestParam(value="order",defaultValue="asc") String order,
                @RequestParam(value="search",defaultValue="") String search, ModelMap model) {
        logger.debug("searchPerson()");
        model.addAttribute("person",service.searchPerson(search,listBy,order));
        return "Main";
    }

  	@RequestMapping(value ="SearchRole", method = RequestMethod.GET)
    public String searchRole(@RequestParam(value="listBy",defaultValue="1") String listBy, ModelMap model) {
        logger.debug("searchRole()");
        Integer orders = Integer.parseInt(listBy);
        Roles pr = service.getByRole(orders);
        Set<Person> persons = pr.getPersonRole();
        model.addAttribute("person", persons);
        return "Main";
    }

    @RequestMapping(value = "{id}delete", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") int id, 
        final RedirectAttributes redirectAttributes) {
        logger.debug("deletePerson()"); 
        String message = service.deletePersons(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is "+ message);

        return "redirect:/Persons";
    }

    @RequestMapping(value = "{id}update", method = RequestMethod.GET)
    public String updatePerson(@PathVariable("id") int id, Model model) {
        logger.debug("updatePerson()");
        Person person = service.getPersons(id);
        Set<Roles> roles = person.getRole();
        Set<Contacts> contact = person.getContact();
        model.addAttribute("personForm", person);
        model.addAttribute("roles", roles);
        model.addAttribute("contact", contact);
        populateModel(model);
        return "UserForm";
    }

    public void populateModel(Model model){
        List<String> roleId = new ArrayList<String>();
        roleId.add("Police");
        roleId.add("Politician");
        roleId.add("Soldier");
        roleId.add("Celebrity");
        roleId.add("Worker");
        model.addAttribute("roleId", roleId);
    }
}
