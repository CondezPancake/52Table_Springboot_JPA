package springboot.application.clinicalrecord.exception;

import springboot.application.common.exception.ApplicationException;

public class ClinicalRecordNotFoundApplicationException extends ApplicationException {
    public ClinicalRecordNotFoundApplicationException(String message) {
        super(message);
    }
}
