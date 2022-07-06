package com.myschool.demo.myschool.core.exceptions;

public class MaxNumberOfStudentEnrollmentsException extends BusinessException{

  public MaxNumberOfStudentEnrollmentsException() {
  }

  public MaxNumberOfStudentEnrollmentsException(String message) {
    super(message);
  }

  public MaxNumberOfStudentEnrollmentsException(String message, Throwable cause) {
    super(message, cause);
  }

  public MaxNumberOfStudentEnrollmentsException(Throwable cause) {
    super(cause);
  }
}
