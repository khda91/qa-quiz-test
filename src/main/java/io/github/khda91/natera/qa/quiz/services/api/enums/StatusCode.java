package io.github.khda91.natera.qa.quiz.services.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    OK(200, "OK"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    UNPROCESSABLE(422, "Unprocessable");

    int code;
    String error;
}
