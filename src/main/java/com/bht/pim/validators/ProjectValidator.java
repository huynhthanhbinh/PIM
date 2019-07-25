package com.bht.pim.validators;

import com.bht.pim.models.Project;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProjectValidator implements Validator {

    // Support method
    // make sure that Validator use for supported class
    // Eg. class UserValidator support class Project
    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.isAssignableFrom(aClass);
    }


    // Customize Validator for project (model) object
    // Must down-casting from (Object) to (Project) o
    // Errors argument is used for binding in Controller
    // ... if (bindingResult.hasErrors()) do_sth ...
    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

        // validation go here...
    }
}
