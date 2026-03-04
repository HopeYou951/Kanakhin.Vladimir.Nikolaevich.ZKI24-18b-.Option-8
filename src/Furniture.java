import java.util.logging.Logger;
public class Furniture {

	private static final Logger logger =
			Logger.getLogger(Furniture.class.getName());
	private String type;
	private String material;
	private int weight;
	private double price;

	// Конструктор по умолчанию
	public Furniture() {
		this.type = "Неизвестно";
		this.material = "Неизвестно";
		this.weight = 0;
		this.price = 0.0;
	}

	// Конструктор с параметрами
	public Furniture(String type, String material, int weight, double price) throws InvalidFurnitureDataException, InvalidNumericValueException {
		setType(type);
		setMaterial(material);
		setWeight(weight);
		setPrice(price);
	}

	// Геттеры

	public String getType() {
		return type;
	}

	public String getMaterial() {
		return material;
	}

	public int getWeight() {
		return weight;
	}

	public double getPrice() {
		return price;
	}

	// Сеттеры с исключениями

	public void setType(String type) throws InvalidFurnitureDataException {
		if (type == null || type.isEmpty()) {
			logger.warning("Попытка установить пустой тип");
			throw new InvalidFurnitureDataException(
					"Тип мебели не может быть пустым");
		}
		this.type = type;
	}

	public void setMaterial(String material) throws InvalidFurnitureDataException {
		if (material == null || material.isEmpty()) {
			logger.warning("Попытка установить пустой материал");
			throw new InvalidFurnitureDataException("Материал не может быть пустым");
		}
		this.material = material;
	}

	public void setWeight(int weight) throws InvalidNumericValueException {
		assert weight >= -1000 : "Некорректный вес!";
		if (weight < 0) {
			logger.warning("Попытка установить отрицательный вес");
			throw new InvalidNumericValueException(
					"Вес не может быть отрицательным");
		}
		this.weight = weight;
	}

	public void setPrice(double price) throws InvalidNumericValueException {
		if (price < 0) {
			logger.warning("Попытка установить отрицательную цену");
			throw new InvalidNumericValueException("Цена не может быть отрицательной");
		}
		this.price = price;
	}

	// Определение категории мебели по весу
	public String getFurnitureCategory() {
		if (weight < 20) {
			return "Лёгкая мебель";
		} else if (weight <= 80) {
			return "Средняя мебель";
		} else {
			return "Тяжёлая мебель";
		}
	}

	@Override
	public String toString() {
		return "Тип: " + type + ", Материал: " + material + ", Вес : " + weight + " кг" + ", Цена: " + price + ", Категория: " + getFurnitureCategory();
	}
}
