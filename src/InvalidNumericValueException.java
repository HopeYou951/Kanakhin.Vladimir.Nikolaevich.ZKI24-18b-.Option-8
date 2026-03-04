/**
 * Исключение выбрасывается при некорректных числовых значениях.
 */
public class InvalidNumericValueException extends Exception {

  public InvalidNumericValueException(String message) {
    super(message);
  }

  public InvalidNumericValueException(String message, Throwable cause) {
    super(message, cause);
  }
}
