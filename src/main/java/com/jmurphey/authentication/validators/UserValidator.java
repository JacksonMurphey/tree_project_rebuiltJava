package com.jmurphey.authentication.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.jmurphey.authentication.models.User;

import com.jmurphey.authentication.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		if (this.userRepository.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Special");
		}
	}
 

	
}
