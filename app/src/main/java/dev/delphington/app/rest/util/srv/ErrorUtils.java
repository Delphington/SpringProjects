package dev.delphington.app.rest.util.srv;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public final class ErrorUtils {
    public String getErrors(BindingResult bindingResult) {
        StringBuilder messageError = new StringBuilder();
        List<FieldError> listErrors = bindingResult.getFieldErrors();
        for (FieldError error : listErrors) {
            messageError.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return messageError.toString();
    }
}
