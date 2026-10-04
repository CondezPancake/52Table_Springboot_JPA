package springboot.domain.common.exception;

import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public class MentalStatusExamNotFoundException extends RuntimeException {
    public MentalStatusExamNotFoundException(MentalStatusExamId id) {
        super("MentalStatusExam not found with id: " + id.value());
    }
}
