package org.fundacionjala.app.quizz.console;

import org.fundacionjala.app.quizz.console.util.InputReader;
import org.fundacionjala.app.quizz.model.Question;
import org.fundacionjala.app.quizz.model.validator.ValidatorType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionInputHandler {

    public Set<String> askQuestionValue(Question question) {
        System.out.println("Question: " + question.getTitle());
        return getAnswer(question);
    }

    private Set<String> getAnswer(Question question) {
        Set<String> answers = new HashSet<>();
        if (question.getType().getConfiguration().hasAdditionalData()) {
            answers.add(collectAnswerFromOptions(question));
        } else {
            System.out.println(question.getType().getName() + " value:");
            String value = "";
            List<String> errors;
            do {
                errors = new ArrayList<>();
                value = InputReader.readLine();
                validateAnswer(value, errors, question.getValidations());
                if (!errors.isEmpty()) {
                    System.out.println(errors);
                }
            } while (!errors.isEmpty());
            answers.add(value);
        }

        return answers;
    }

    private void validateAnswer(String value, List<String> errors, List<ValidatorType> validations) {
        for (ValidatorType validatorType: validations) {
            validatorType.getValidator().validate(value, null, errors);
        }
    }

    private String collectAnswerFromOptions(Question question) {
        String answer = null;

        while (true) {
            showOptions(question);
            char option = InputReader.readOption();
            if (option == '0') {
                break;
            }
        }

        return answer;
    }

    private void showOptions(Question question) {
        System.out.println("Select an option: ");
        for (int index = 0; index < question.getAdditionalData().size(); index++) {
            System.out.printf("%d. %s" + System.lineSeparator(), index + 1, question.getAdditionalData().get(index));
        }
        System.out.println("0. To Finish");
    }
}
