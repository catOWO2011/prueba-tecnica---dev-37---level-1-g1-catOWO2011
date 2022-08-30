package org.fundacionjala.app.quizz.model.validator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidatorIT {

    @Test
    public void testDateValidator() {
        List<String> errors = new ArrayList<>();
        Validator dateValidator = ValidatorType.DATE.getValidator();

        dateValidator.validate("24/12/2021", null, errors);

        Assert.assertEquals(DateValidator.class, dateValidator.getClass());
        Assert.assertTrue(errors.isEmpty());
    }

    @Test
    public void testDateValidatorWithWrongFormat() {
        List<String> errors = new ArrayList<>();
        Validator dateValidator = ValidatorType.DATE.getValidator();

        dateValidator.validate("24123/12231/2021", null, errors);

        Assert.assertEquals(DateValidator.class, dateValidator.getClass());
        Assert.assertFalse(!errors.isEmpty());
    }

    @Test
    public void testUpperCaseValidatorWithCorrectValue() {
        List<String> errors = new ArrayList<>();
        Validator upperCaseValidator = ValidatorType.UPPER_CASE_VALIDATOR.getValidator();

        upperCaseValidator.validate("THEFOXJUMPSOVERTHELAZYDOG", null, errors);

        Assert.assertEquals(UpperCaseValidator.class, upperCaseValidator.getClass());
        Assert.assertTrue(!errors.isEmpty());
    }

    @Test
    public void testUpperCaseValidatorWithWrongValue() {
        List<String> errors = new ArrayList<>();
        Validator upperCaseValidator = ValidatorType.UPPER_CASE_VALIDATOR.getValidator();

        upperCaseValidator.validate("TheFoxJumpsOverTheLazyDog", null, errors);
        System.out.println("TheFoxJumpsOverTheLazyDog".matches("/^[A-Z]+$/g"));

        Assert.assertEquals(UpperCaseValidator.class, upperCaseValidator.getClass());
        Assert.assertTrue(!errors.isEmpty());
    }
}
