package springboot.application.clinicalnote.exception;

import springboot.application.common.exception.ApplicationException;

public class ClinicalNoteNotFoundApplicationException extends ApplicationException {
    public ClinicalNoteNotFoundApplicationException(String message) {
        super(message);
    }
}
