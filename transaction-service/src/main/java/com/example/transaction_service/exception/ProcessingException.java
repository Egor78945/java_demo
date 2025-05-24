package com.example.transaction_service.exception;

public class ProcessingException extends RuntimeException {
  public ProcessingException(String message) {
    super(message);
  }
}
