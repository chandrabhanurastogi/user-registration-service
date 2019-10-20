package com.gamesys.user.registration.model.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
