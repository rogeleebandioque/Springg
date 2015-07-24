package hibernateactivity.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
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
public class FileUploadController extends SimpleFormController {

    private Service service;
    private Operations operations;
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    public void setOperations(Operations operations){
        this.operations = operations;
    }

    public void setService(Service service){
        this.service = service;
    }      

    protected ModelAndView onSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                Object command,
                                BindException errors)
                         throws Exception{ 
        logger.debug("FileUploadController: onSubmit()");
        String name = request.getParameter("name");
        String path = "";
        FileUpload f = (FileUpload)command;
        MultipartFile file = f.getFile();
         if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                File dir = new File("Uploaded Files");
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                path = dir.getAbsolutePath()
                        + File.separator + name;
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                System.out.println("Errr");            
            }
            return new ModelAndView(new RedirectView("UploadPerson?name="+name));
        }
        return new ModelAndView(new RedirectView("AddPerson"));
        
    }

}
 
