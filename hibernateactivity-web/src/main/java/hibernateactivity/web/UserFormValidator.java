package hibernateactivity.web;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
import hibernateactivity.core.model.Person;

public class UserFormValidator implements Validator {
 
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
		
	}
 
}
