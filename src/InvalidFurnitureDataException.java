/**
 * Исключение выбрасывается при некорректных строковых данных мебели.
 */
public class InvalidFurnitureDataException extends Exception {

  /**
   * Конструктор с сообщением.
   *
   * @param message текст ошибки
   */
  public InvalidFurnitureDataException(String message) {
    super(message);
  }

  /**
   * Конструктор с причиной.
   *
   * @param message текст ошибки
   * @param cause   причина
   */
  public InvalidFurnitureDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
