package springboot.application.clinicalrecordstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class ClinicalRecordStatusNotFoundApplicationException extends ApplicationException {
    public ClinicalRecordStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
