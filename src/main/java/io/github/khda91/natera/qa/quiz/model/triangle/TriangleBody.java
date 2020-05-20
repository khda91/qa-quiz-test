package io.github.khda91.natera.qa.quiz.model.triangle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TriangleBody {

    private final String input;
    private String separator;

    public TriangleBody(String input) {
        this.input = input;
    }
}
