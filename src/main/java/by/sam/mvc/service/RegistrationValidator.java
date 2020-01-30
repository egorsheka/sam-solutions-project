package by.sam.mvc.service;

import by.sam.mvc.entity.user.UserEntity;
import by.sam.mvc.model.PersonDto;
import by.sam.mvc.service.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.NoResultException;


@Component
public class RegistrationValidator implements Validator {

    private UserService userService;

    public RegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> paramClass) {
        return PersonDto.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        PersonDto client = (PersonDto)obj;
        try {
            UserEntity user = userService.read(client.getEmail());
        }catch (NoResultException e){
            errors.rejectValue("exist", "negativeValue", new Object[]{"'exist'"}, "with this email user already exist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "exist", "");
    }
}