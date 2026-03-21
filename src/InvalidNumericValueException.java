/**
 * Исключение выбрасывается при некорректных числовых значениях.
 */
public class InvalidNumericValueException extends Exception {

  /**
   * Конструктор с сообщением.
   *
   * @param message текст ошибки
   */
  public InvalidNumericValueException(String message) {
    super(message);
  }

  /**
   * Конструктор с причиной ошибки.
   *
   * @param message текст ошибки
   * @param cause   причина
   */
  public InvalidNumericValueException(String message, Throwable cause) {
    super(message, cause);
  }
}
