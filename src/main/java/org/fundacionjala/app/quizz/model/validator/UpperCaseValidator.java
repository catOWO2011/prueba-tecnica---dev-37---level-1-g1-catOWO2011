package org.fundacionjala.app.quizz.model.validator;

import java.util.List;

public class UpperCaseValidator implements  Validator {
  private static final String ERROR_MESSAGE = "The value must be upper case";

  @Override
  public void validate(String value, String conditionValue, List<String> errors) {
    if (!value.matches("/^[A-Z]+$/g")) {
      errors.add(ERROR_MESSAGE);
    }
  }
}