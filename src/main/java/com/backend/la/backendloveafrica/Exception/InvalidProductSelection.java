package com.backend.la.backendloveafrica.Exception;

public class InvalidProductSelection extends RuntimeException {

  public InvalidProductSelection() {
  }

  public InvalidProductSelection(String message) {
      super(message);
  }

  public InvalidProductSelection(String message, Throwable cause) {
      super(message, cause);
  }

}
