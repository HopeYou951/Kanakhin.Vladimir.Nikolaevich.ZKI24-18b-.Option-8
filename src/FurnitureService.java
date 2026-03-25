import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервисный класс для работы с коллекцией мебели.
 * Содержит методы обработки данных с использованием Stream API.
 */
public class FurnitureService {

  /**
   * Фильтрация мебели по цене.
   *
   * @param furnitureList     список мебели
   * @param minPriceForFilter минимальная цена
   */
  public static void filterPrice(List<Furniture> furnitureList, double minPriceForFilter) throws InvalidNumericValueException {
    furnitureList.stream()
        .filter(furniture -> furniture.getPrice() > minPriceForFilter)
        .forEach(System.out::println);

    if (furnitureList.isEmpty()) {
      System.out.println("Список пуст.");
    } else {
      System.out.println("Цена от " + minPriceForFilter);
    }
  }

  /**
   * Удаление дубликатов из списка.
   *
   * @param furnitureList список мебели
   */
  public static void deleteDuplicates(ArrayList<Furniture> furnitureList) {
    ArrayList<Furniture> uniqueList = furnitureList.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

    furnitureList.clear();
    furnitureList.addAll(uniqueList);

    if (uniqueList.isEmpty()) {
      System.out.println("Список пуст.");
    } else {
      System.out.println("Дубликаты удалены. Список теперь:");
      furnitureList.forEach(System.out::println);
    }
  }

  /**
   * Вычисляет сумму цен всех объектов.
   *
   * @param furnitureList список мебели
   */
  public static void totalPrice(ArrayList<Furniture> furnitureList) {
    double total = furnitureList.stream().mapToDouble(Furniture::getPrice).sum();
    if (furnitureList.isEmpty()) {
      System.out.println("Список пуст.");
    } else {
      System.out.println("Общая стоймость всей мебели: " + total);
    }
  }

  /**
   * Находит самый дорогой объект (использует Optional).
   * Используем Optional воизбежании исключения, так как список в методе maxPrice может оказаться пустым.
   * Так же делаем и проверку, и выводим результат в консоль, для информирования пользователя.
   *
   * @param furnitureList список мебели
   */
  public static void maxPrice(ArrayList<Furniture> furnitureList) {
    Optional<Furniture> max = furnitureList.stream().max(Comparator.comparingDouble(Furniture::getPrice));

    if (max.isPresent()) {
      System.out.println("Самый дорогой: " + max.get());
    } else {
      System.out.println("Список пуст, максимального элемента нет");
    }
  }

  /**
   * Группирует объекты по материалу.
   *
   * @param furnitureList список мебели
   */
  public static void groupMaterial(ArrayList<Furniture> furnitureList) {
    Map<String, Long> grouped = furnitureList.stream().collect(Collectors.groupingBy(Furniture::getMaterial, Collectors.counting()));
    System.out.println("Группировка по материалу:");
    grouped.forEach((material, count) -> System.out.println(material + ": " + count + " предмет(ов)"));
  }

  /**
   * Выводит статистику по ценам.
   *
   * @param furnitureList список мебели
   */
  public static void statistics(ArrayList<Furniture> furnitureList) {
    DoubleSummaryStatistics statistics = furnitureList.stream().mapToDouble(Furniture::getPrice).summaryStatistics();

    System.out.println("Количество: " + statistics.getCount());
    System.out.println("Сумма: " + statistics.getSum());
    System.out.println("Мин: " + statistics.getMin());
    System.out.println("Макс: " + statistics.getMax());
    System.out.println("Среднее: " + statistics.getAverage());
  }

  /**
   * Сохраняет список в файл CSV.
   *
   * @param furnitureList список мебели
   */
  public static void saveInFile(ArrayList<Furniture> furnitureList) {
    try (PrintWriter write = new PrintWriter("Furniture.csv")) {
      furnitureList.forEach(furniture -> write.println(furniture.getType() + ";" + furniture.getMaterial() + ";" + furniture.getWeight() + ";" + furniture.getPrice()));
      System.out.println("Сохранено в файл");
    } catch (Exception e) {
      System.out.println("Ошибка сохранения: " + e.getMessage());
    }
  }

  /**
   * Загружает список мебели из файла.
   *
   * @return список мебели
   */
  public static ArrayList<Furniture> loadFile() {
    ArrayList<Furniture> list = new ArrayList<>();
    try {
      List<String> lines = Files.readAllLines(Paths.get("Furniture.csv")); // Читаем из файла

      for (String line : lines) {             // Перебираем каждую строку
        String[] p = line.split(";");  // Записываем каждуй строку в массив строк
        list.add(new Furniture(             //  Создаем объект класса Furniture и добавляем в наш список
            p[0], p[1], Integer.parseInt(p[2]),        //   Парсим значения
            Double.parseDouble(p[3])));
      }

      System.out.println("Загружено из файла");
    } catch (Exception e) {
      System.out.println("Ошибка загрузки: " + e.getMessage());
    }
    return list;
  }
}

