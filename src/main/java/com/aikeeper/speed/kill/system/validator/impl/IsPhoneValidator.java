package com.aikeeper.speed.kill.system.validator.impl;

import com.aikeeper.speed.kill.system.utils.ValidatorUtils;
import com.aikeeper.speed.kill.system.validator.IsPhone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 17:15
 * @Version V1.0
 **/
public class IsPhoneValidator implements ConstraintValidator<IsPhone, String> {

    private Boolean required = Boolean.FALSE;

    @Override
    public void initialize(IsPhone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(required) {
            return ValidatorUtils.isPhone(s);
        }else {
            if(StringUtils.isEmpty(s)) {
                return Boolean.TRUE;
            }else {
                return ValidatorUtils.isPhone(s);
            }
        }
    }

}
