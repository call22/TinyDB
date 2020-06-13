package cn.edu.thssdb.exception;

import java.security.Key;

public class KeyNotExistException extends RuntimeException{
  private final String message;

  public KeyNotExistException(String message) {
    super(message);
    this.message = message;
  }

  public KeyNotExistException() {
    this.message = "Exception: key doesn't exist!";
  }
  @Override
  public String getMessage() {
    return this.message;
  }
}
