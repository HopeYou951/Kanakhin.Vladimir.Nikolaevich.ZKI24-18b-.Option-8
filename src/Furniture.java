    public class Furniture {
        // Поля класса
        private String type;
        private String material;
        private int weight;
        private double price;

        // Конструктор поумолчанию
        public Furniture() {
        this.type = "Неизвестно";
        this.material = "Неизвестно";
        this.weight = 0;
        this.price = 0.0;
    }

        // Конструктор с параметрами.
        // Инициализирует объект Furniture переданными значениями.
    public Furniture(String type, String material, int weight, double price){
        setType(type);
        setMaterial(material);
        setWeight(weight);
        setPrice(price);
    }

    // Геттеры для получаения информации полей
    public String getType(){
        return type;
    }
    public String getMaterial(){
        return material;
    }
    public int getWeight(){
        return weight;
    }
    public double getPrice(){
        return price;
    }

    // Сеттеры для ввода и проверки значений.
    public void setType(String type){
        if(type != null && !type.isEmpty()){
            this.type = type;
        }
    }
    public void setMaterial(String material){
        if(material != null && !material.isEmpty()){
            this.material = material;
        }
    }
    public void setWeight(int weight){
        if(weight >= 0){
            this.weight = weight;
        }
    }
    public void setPrice(double price){
        if(price >= 0){
            this.price = price;
        }
    }
    // Метод определяющий категорию мебели по весу
    public String getFurnitureCategory() {
        if (weight < 20) {
            return "Лёгкая мебель";
        } else if (weight <= 80) {
            return "Средняя мебель";
        } else {
            return "Тяжёлая мебель";
        }
    }

        // Переопределяем метод класса Object для вывода в консоль информации о мебели
    @Override
    public String toString(){
        return  "Тип: " + type +
                ", Материал: " + material +
                ", Вес: " + weight +
                ", Цена: " + price +
                ", Категория: " + getFurnitureCategory();
    }
}
