package com.myschool.demo.myschool.core.exceptions;

public class MaxNumberOfCourseEnrollmentsException extends BusinessException {

  public MaxNumberOfCourseEnrollmentsException() {}

  public MaxNumberOfCourseEnrollmentsException(String message) {
    super(message);
  }

  public MaxNumberOfCourseEnrollmentsException(String message, Throwable cause) {
    super(message, cause);
  }

  public MaxNumberOfCourseEnrollmentsException(Throwable cause) {
    super(cause);
  }
}
