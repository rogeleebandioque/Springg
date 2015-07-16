package hibernateactivity.core.model;

import org.springframework.web.multipart.MultipartFile;
 
public class FileUpload{
 
	MultipartFile file;
	//getter and setter methods

    public void setFile(MultipartFile file){
        this.file = file;
    }

    public MultipartFile getFile(){
        return file;
    }    
 
}
