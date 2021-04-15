package com.tht.demo.validate;

import com.tht.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Date;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        Date date = user.getDateOfBirth();
        String[] dateOfBirth = date.toString().split(" ");
        String[] currentDate = LocalDate.now().toString().split("-");
        String password = user.getPassword();
        if(Integer.parseInt(dateOfBirth[5])+ 18 > Integer.parseInt(currentDate[0]) ){
            errors.rejectValue("dateOfBirth","dateOfBirth.error");
        }
        if(password==null){
            if(password.length()< 8 || password.length() > 16) {
                errors.rejectValue("password","password.length");
            }
        }
    }
}
