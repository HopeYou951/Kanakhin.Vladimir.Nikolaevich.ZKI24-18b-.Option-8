import java.util.Objects;
import java.util.logging.Logger;

/**
 * Класс, описывающий объект мебели.
 * Содержит основные характеристики: тип, материал, вес и цену.
 */
public class Furniture {

  private static final Logger logger = Logger.getLogger(Furniture.class.getName());
  private String type;
  private String material;
  private int weight;
  private double price;

  /**
   * Конструктор по умолчанию.
   * Инициализирует поля
   */
  public Furniture() {
    this.type = "Неизвестно";
    this.material = "Неизвестно";
    this.weight = 0;
    this.price = 0.0;
  }

  /**
   * Конструктор с параметрами.
   *
   * @param type     тип мебели
   * @param material материал мебели
   * @param weight   вес мебели
   * @param price    цена мебели
   * @throws InvalidFurnitureDataException если строковые данные некорректны
   * @throws InvalidNumericValueException  если числовые значения некорректны
   */
  public Furniture(String type, String material, int weight, double price) throws InvalidFurnitureDataException, InvalidNumericValueException {
    setType(type);
    setMaterial(material);
    setWeight(weight);
    setPrice(price);
  }

  /**
   * @return тип мебели
   */
  public String getType() {
    return type;
  }

  /**
   * @return материал мебели
   */
  public String getMaterial() {
    return material;
  }

  /**
   * @return вес мебели
   */
  public int getWeight() {
    return weight;
  }

  /**
   * @return цена мебели
   */
  public double getPrice() {
    return price;
  }

  /**
   * Устанавливает тип мебели.
   *
   * @param type тип мебели
   * @throws InvalidFurnitureDataException если тип пустой
   */
  public void setType(String type) throws InvalidFurnitureDataException {
    if (type == null || type.isEmpty()) {
      logger.warning("Попытка установить пустой тип");
      throw new InvalidFurnitureDataException(
          "Тип мебели не может быть пустым");
    }
    this.type = type;
  }

  /**
   * Устанавливает материал мебели.
   *
   * @param material материал
   * @throws InvalidFurnitureDataException если материал пустой
   */
  public void setMaterial(String material) throws InvalidFurnitureDataException {
    if (material == null || material.isEmpty()) {
      logger.warning("Попытка установить пустой материал");
      throw new InvalidFurnitureDataException("Материал не может быть пустым");
    }
    this.material = material;
  }

  /**
   * Устанавливает вес мебели.
   *
   * @param weight вес
   * @throws InvalidNumericValueException если вес отрицательный
   */
  public void setWeight(int weight) throws InvalidNumericValueException {
    assert weight >= -1000 : "Некорректный вес!";
    if (weight < 0) {
      logger.warning("Попытка установить отрицательный вес");
      throw new InvalidNumericValueException(
          "Вес не может быть отрицательным");
    }
    this.weight = weight;
  }

  /**
   * Устанавливает цену мебели.
   *
   * @param price цена
   * @throws InvalidNumericValueException если цена отрицательная
   */
  public void setPrice(double price) throws InvalidNumericValueException {
    if (price < 0) {
      logger.warning("Попытка установить отрицательную цену");
      throw new InvalidNumericValueException("Цена не может быть отрицательной");
    }
    this.price = price;
  }

  /**
   * Определяет категорию мебели по весу.
   *
   * @return строковое описание категории
   */
  public String getFurnitureCategory() {
    if (weight < 20) {
      return "Лёгкая мебель";
    } else if (weight <= 80) {
      return "Средняя мебель";
    } else {
      return "Тяжёлая мебель";
    }
  }

  /**
   * Возвращает строковое представление объекта.
   */
  @Override
  public String toString() {
    return "Тип: " + type + ", Материал: " + material + ", Вес : " + weight + " кг" + ", Цена: " + price + ", Категория: " + getFurnitureCategory();
  }

  /**
   * Возвращает строковое представление объекта.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return false;
    if (!(o instanceof Furniture)) return false;
    Furniture f = (Furniture) o;
    return weight == f.weight &&
        Double.compare(f.price, price) == 0 &&
        type.equals(f.type) &&
        material.equals(f.material);
  }

  /**
   * Хеш-код объекта.
   */
  @Override
  public int hashCode() {
    return Objects.hash(type, material, weight, price);
  }
}

