package ba.fit.java.spring.mvc.viewmodels;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckBoxValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Customer instances
        return LoginVM.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LoginVM cust = (LoginVM)target;

        if(cust.username.length()< 4){
           errors.reject("", "ne valja");
        }
    }
}
