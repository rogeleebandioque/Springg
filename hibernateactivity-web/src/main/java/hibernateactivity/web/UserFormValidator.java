package hibernateactivity.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import hibernateactivity.core.model.Person;

public class UserFormValidator implements Validator {
    public boolean supports(Class<?> clazz) {
       return Person.class.equals(clazz);
    }
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "names.first_name", "erform.name.firstname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "names.last_name", "erform.name.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "erform.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bday", "erform.bday");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "erform.age");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date_hired", "erform.date_hired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "erform.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currently_employed", "erform.currently_employed");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "grade", "erform.grade");
    }
}
