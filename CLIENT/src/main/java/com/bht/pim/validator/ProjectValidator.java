package com.bht.pim.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bht.pim.proto.projects.Project;

/**
 * @author bht
 */
public final class ProjectValidator implements Validator {

    // Support method
    // make sure that Validator use for supported class
    // Eg. class UserValidator support class ProjectModel
    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.isAssignableFrom(aClass);
    }


    // Customize Validator for project (model) object
    // Must down-casting from (Object) to (ProjectModel) o
    // Errors argument is used for binding in Controller
    // ... if (bindingResult.hasErrors()) do_sth ...
    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

        // validation go here...
    }
}
