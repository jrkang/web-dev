package kr.web.dev.model.validation;

import kr.web.dev.model.dto.LoginFormDto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginFormDtoValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginFormDto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		LoginFormDto dto = (LoginFormDto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required.password");
	}
	
}
