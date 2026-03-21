import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Главный класс приложения.
 * Реализует меню и взаимодействие с пользователем.
 */
public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  /**
   * Очищает консоль.
   * Реализация зависит от операционной системы.
   */
  public static void clearConsole() {
    for (int i = 0; i < 40; i++) {
      System.out.println();
    }
  }

  /**
   * Вывод заголовка
   */
  public static void printHeader(String title) {
    System.out.println("==================================");
    System.out.println("   " + title);
    System.out.println("==================================");
  }

  /**
   * Приостанавливает выполнение программы до нажатия Enter.
   *
   * @param scanner объект Scanner
   */
  public static void pause(Scanner scanner) {
    System.out.println("\nНажмите Enter для продолжения...");
    scanner.nextLine();
  }

  /**
   * Считывает целое число с проверкой корректности.
   *
   * @param scanner объект Scanner
   * @param message сообщение пользователю
   * @return введённое число
   * @throws InvalidNumericValueException если введено не число
   */
  public static int readInt(Scanner scanner, String message) throws InvalidNumericValueException {
    System.out.print(message);
    String input = scanner.nextLine();

    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new InvalidNumericValueException("Введено не целое число", e);
    }
  }

  /**
   * Считывает число с плавающей точкой.
   *
   * @param scanner объект Scanner
   * @param message сообщение пользователю
   * @return введённое число
   * @throws InvalidNumericValueException если введено некорректное значение
   */
  public static double readDouble(Scanner scanner, String message) throws InvalidNumericValueException {
    System.out.print(message);
    String input = scanner.nextLine();

    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      throw new InvalidNumericValueException("Введено не число", e);
    }
  }

  public static void main(String[] args) {

    ArrayList<Furniture> furnitureList = new ArrayList<>();

    // Данные для демонстрации
    try {
      furnitureList.add(new Furniture("Стул", "Дерево", 10, 1200));
      furnitureList.add(new Furniture("Стол", "Металл", 50, 3500));
      furnitureList.add(new Furniture("Шкаф", "Дерево", 80, 7000));
      furnitureList.add(new Furniture("Стул", "Дерево", 10, 1200)); // дубликат
    } catch (Exception e) {
      logger.warning("Ошибка при создании тестовых данных");
    }

    try (Scanner scanner = new Scanner(System.in)) {

      boolean work = true;

      while (work) {
        clearConsole();
        printHeader("ГЛАВНОЕ МЕНЮ");

        System.out.println("1. Создание объектов");
        System.out.println("2. Редактирование и просмотр");
        System.out.println("3. Работа со Stream API");
        System.out.println("4. Работа с файлами");
        System.out.println("5. Выход");

        String choice = scanner.nextLine();

        switch (choice) {

          // ---------------- СОЗДАНИЕ ----------------
          case "1":
            boolean createMenu = true;

            while (createMenu) {
              clearConsole();
              printHeader("СОЗДАНИЕ ОБЪЕКТОВ");

              System.out.println("1. Добавить пустой объект");
              System.out.println("2. Добавить с данными");
              System.out.println("3. Назад");

              switch (scanner.nextLine()) {
                case "1":
                  furnitureList.add(new Furniture());
                  System.out.println("Добавлено.");
                  pause(scanner);
                  break;

                case "2":
                  try {
                    System.out.print("Тип: ");
                    String type = scanner.nextLine();

                    System.out.print("Материал: ");
                    String material = scanner.nextLine();

                    int weight = readInt(scanner, "Вес: ");
                    double price = readDouble(scanner, "Цена: ");

                    furnitureList.add(new Furniture(type, material, weight, price));
                    System.out.println("Добавлено.");

                  } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                  }

                  pause(scanner);
                  break;

                case "3":
                  createMenu = false;
                  break;
              }
            }
            break;

          // ---------------- РЕДАКТИРОВАНИЕ ----------------
          case "2":
            boolean editMenu = true;

            while (editMenu) {
              clearConsole();
              printHeader("РЕДАКТИРОВАНИЕ");

              System.out.println("1. Редактировать объект");
              System.out.println("2. Показать все");
              System.out.println("3. Сортировка по цене");
              System.out.println("4. Назад");

              switch (scanner.nextLine()) {

                case "1":
                  if (furnitureList.isEmpty()) {
                    System.out.println("Список пуст");
                    pause(scanner);
                    break;
                  }

                  for (int i = 0; i < furnitureList.size(); i++) {
                    System.out.println(i + ": " + furnitureList.get(i));
                  }

                  try {
                    int index = readInt(scanner, "Индекс: ");
                    Furniture f = furnitureList.get(index);

                    f.setPrice(readDouble(scanner, "Новая цена: "));
                  } catch (Exception e) {
                    System.out.println("Ошибка");
                  }

                  pause(scanner);
                  break;

                case "2":
                  furnitureList.forEach(System.out::println);
                  pause(scanner);
                  break;

                case "3":
                  furnitureList.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
                  System.out.println("Отсортировано");
                  pause(scanner);
                  break;

                case "4":
                  editMenu = false;
                  break;
              }
            }
            break;

          // ---------------- STREAM ----------------
          case "3":
            boolean streamMenu = true;

            while (streamMenu) {
              clearConsole();
              printHeader("STREAM API");

              System.out.println("1. Фильтрация");
              System.out.println("2. Удалить дубликаты");
              System.out.println("3. Сумма");
              System.out.println("4. Самый дорогой");
              System.out.println("5. Группировка");
              System.out.println("6. Статистика");
              System.out.println("7. Назад");

              switch (scanner.nextLine()) {

                case "1":
                  double t = readDouble(scanner, "Цена: ");
                  FurnitureService.filterPrice(furnitureList, t);
                  pause(scanner);
                  break;

                case "2":
                  FurnitureService.deleteDuplicates(furnitureList);
                  pause(scanner);
                  break;

                case "3":
                  FurnitureService.totalPrice(furnitureList);
                  pause(scanner);
                  break;

                case "4":
                  FurnitureService.maxPrice(furnitureList);
                  pause(scanner);
                  break;

                case "5":
                  FurnitureService.groupMaterial(furnitureList);
                  pause(scanner);
                  break;

                case "6":
                  FurnitureService.statistics(furnitureList);
                  pause(scanner);
                  break;

                case "7":
                  streamMenu = false;
                  break;
              }
            }
            break;

          // ---------------- ФАЙЛЫ ----------------
          case "4":
            boolean fileMenu = true;

            while (fileMenu) {
              clearConsole();
              printHeader("ФАЙЛЫ");

              System.out.println("1. Сохранить");
              System.out.println("2. Загрузить");
              System.out.println("3. Назад");

              switch (scanner.nextLine()) {

                case "1":
                  FurnitureService.saveInFile(furnitureList);
                  pause(scanner);
                  break;

                case "2":
                  furnitureList = new ArrayList<>(FurnitureService.loadFile());
                  pause(scanner);
                  break;

                case "3":
                  fileMenu = false;
                  break;
              }
            }
            break;

          case "5":
            work = false;
            break;
        }
      }
    } catch (InvalidNumericValueException e) {
      throw new RuntimeException(e);
    }
  }
}