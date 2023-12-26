package com.practo.exception;

import com.practo.dictionary.APIErrorCode;
import org.springframework.validation.BindingResult;

import java.io.Serializable;

public class InspireException extends Exception implements Serializable {
        private APIErrorCode errorCode;
        private String errorMessage;
//        private BindingResult bindingResult;

    public InspireException(APIErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
//        this.bindingResult=bindingResult;
    }

//        public InspireException(APIErrorCode errorCode, String errorMessage) {
//            this.errorCode = errorCode;
//            this.errorMessage = errorMessage;
//        }

    public InspireException(APIErrorCode errorCode) {
        this.errorCode = errorCode;

    }

    public APIErrorCode getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

}
