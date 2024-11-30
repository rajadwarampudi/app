package com.voyado.elevate.app.searchservice.services.exception;

/** SearchServiceException is a wrapper exception class
 * specific to this search service
 **/
public class SearchServiceException extends Exception {

    public SearchServiceException(Exception exception) {
        super(exception);
    }

    public SearchServiceException(String errorMessage) {
        super(errorMessage);
    }
}
