package springboot.domain.common.exception;

import springboot.domain.study.model.valueobject.StudyId;

public class StudyNotFoundException extends RuntimeException {
    public StudyNotFoundException(StudyId id) {
        super("Study not found with id: " + id.value());
    }
}
