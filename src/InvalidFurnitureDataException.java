/**
 * Исключение выбрасывается при некорректных строковых данных мебели.
 */
public class InvalidFurnitureDataException extends Exception {

  public InvalidFurnitureDataException(String message) {
    super(message);
  }

  public InvalidFurnitureDataException(String message, Throwable cause) {
    super(message, cause);
  }
}
