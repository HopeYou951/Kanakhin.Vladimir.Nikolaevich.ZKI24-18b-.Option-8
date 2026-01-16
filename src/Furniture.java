public class Furniture {

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
    public Furniture(String type, String material, int weight, double price) {
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

    // Сеттеры с проверкой корректности значений

    public void setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        }
    }

    public void setMaterial(String material) {
        if (material != null && !material.isEmpty()) {
            this.material = material;
        }
    }

    public void setWeight(int weight) {
        if (weight >= 0) {
            this.weight = weight;
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
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
        return "Тип: " + type + ", Материал: " + material + ", Вес: " + weight + ", Цена: " + price + ", Категория: " + getFurnitureCategory();
    }
}
