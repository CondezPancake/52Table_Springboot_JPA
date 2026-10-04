package springboot.application.relationshiptype.exception;

import springboot.application.common.exception.ApplicationException;

public class RelationshipTypeNotFoundApplicationException extends ApplicationException {
    public RelationshipTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
