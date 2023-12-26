package com.practo.payload;

import com.practo.dictionary.APIErrorCode;
import org.springframework.validation.BindingResult;

public class ErrorResponse {
        private APIErrorCode errorCode;
        private String message;

    public APIErrorCode getErrorCode() {
        return errorCode;
    }

    public ErrorResponse(APIErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public void setErrorCode(APIErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


