package jp.gmo.project.exception;

import lombok.Data;

@Data
public class FieldError {
    private final String field;
    private final String message;
}
