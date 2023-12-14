package com.practo.Exception;

    public class AttendanceNotFoundException extends RuntimeException
    {
        public AttendanceNotFoundException(String message)
        {
            super(message);
        }
    }
