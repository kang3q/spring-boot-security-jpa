package com.wisebirds.sap.domain.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wisebirds.sap.domain.form.MemberCreateForm;

@Component
public class MemberCreateFormValidator implements Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberCreateFormValidator.class);
	// @Autowired
	// private MemberService memberService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(MemberCreateForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LOGGER.debug("Validating {}", target);
		// MemberCreateForm form = (MemberCreateForm) target;
		// validatePasswords(errors, form);
		// validateEmail(errors, form);
	}

	// private void validatePasswords(Errors errors, MemberCreateForm form) {
	// if (!form.getPassword().equals(form.getPasswordRepeated())) {
	// errors.reject("password.no_match", "Passwords do not match");
	// }
	// }

	// private void validateEmail(Errors errors, MemberCreateForm form) {
	// if (memberService.getMemberByMemberId(form.getMemberId()).isPresent()) {
	// errors.reject("email.exists", "사용자 아이디가 이미 존재합니다.");
	// }
	// }
}
