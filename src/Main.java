import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// Методы проверки корректности ввода данных
	static int readInt(Scanner scanner, String message) throws InvalidNumericValueException {
		System.out.print(message);

		String input = scanner.nextLine();

		try {
			return Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			throw new InvalidNumericValueException("Введено не целое число", e);
		}
	}

	static double readDouble(Scanner scanner, String message) throws InvalidNumericValueException {
		System.out.print(message);
		String input = scanner.nextLine();

		try {
			return Double.parseDouble(input);
		}
		catch (NumberFormatException e) {
			throw new InvalidNumericValueException("Введено не число", e);
		}
	}

	public static void main(String[] args) throws InvalidFurnitureDataException, InvalidNumericValueException {
		ArrayList<Furniture> furnitureList = new ArrayList<>();
		try (Scanner scanner = new Scanner(System.in)) {
			boolean work = true;

			while (work) {
				System.out.println("\n=== МЕНЮ ===");
				System.out.println("1. Добавить пустой объект");
				System.out.println("2. Добавить объект с данными");
				System.out.println("3. Редактировать поле объекта");
				System.out.println("4. Показать все объекты");
				System.out.println("5. Сортировка по цене (возрастание)");
				System.out.println("6. Выход");

				String choice = scanner.nextLine();

				switch (choice) {
					case "1":
						// Создание объекта с конструктором по умолчанию
						furnitureList.add(new Furniture());
						System.out.println("Пустой объект добавлен.");
						break;

					case "2":
						// Создание объекта с вводом данных
						try {
							System.out.print("Введите тип мебели: ");
							String type = scanner.nextLine();

							System.out.print("Введите материал: ");
							String material = scanner.nextLine();

							int weight = readInt(scanner, "Введите вес (кг): ");
							double price = readDouble(scanner, "Введите цену: ");

							furnitureList.add(new Furniture(type, material, weight, price));
							System.out.println("Объект добавлен.");

						} catch (InvalidFurnitureDataException | InvalidNumericValueException e) {
							System.out.println("Ошибка: " + e.getMessage());

							// вывод причины (цепочки)
							if (e.getCause() != null) {
								System.out.println("Причина: " + e.getCause());
							}
						}
						break;

					case "3":
						// Редактирование выбранного объекта
						if (furnitureList.isEmpty()) {
							System.out.println("Список пуст.");
							break;
						}

						for (int i = 0; i < furnitureList.size(); i++) {
							System.out.println(i + ": " + furnitureList.get(i));
						}

						try {
							int index = readInt(scanner, "Введите индекс объекта: ");

							if (index < 0 || index >= furnitureList.size()) {
								System.out.println("Некорректный индекс.");
								break;
							}

							Furniture f = furnitureList.get(index);
							boolean editing = true;

							while (editing) {
								System.out.println("\nРедактирование объекта:");
								System.out.println("1. Тип");
								System.out.println("2. Материал");
								System.out.println("3. Вес");
								System.out.println("4. Цена");
								System.out.println("5. Удалить объект");
								System.out.println("6. Выход");

								String field = scanner.nextLine();

								switch (field) {
									case "1":
										System.out.println("Новый тип: ");
										f.setType(scanner.nextLine());
										break;

									case "2":
										System.out.println("Новый материал: ");
										f.setMaterial(scanner.nextLine());
										break;

									case "3":
										f.setWeight(readInt(scanner, "Новый вес: "));
										break;

									case "4":
										f.setPrice(readDouble(scanner, "Новая цена: "));
										break;

									case "5":
										furnitureList.remove(f);

									case "6":
										editing = false;
										break;

									default:
										System.out.println("Неверный выбор.");
								}
							}
						} catch (InvalidFurnitureDataException | InvalidNumericValueException e) {
							System.out.println("Ошибка: " + e.getMessage());
							if (e.getCause() != null) {
								System.out.println("Причина: " + e.getCause());
							}
						}
						break;

					case "4":
						// Вывод списка всех объектов
						if (furnitureList.isEmpty()) {
							System.out.println("Список пуст.");
						} else {
							for (int i = 0; i < furnitureList.size(); i++) {
								System.out.println(i + ": " + furnitureList.get(i));
							}
						}
						break;

					case "5":
						// Сортировка объектов по возрастанию цены
						if (furnitureList.size() < 2) {
							System.out.println("Должно быть хотя бы 2 объекта!");
							break;
						}
							for (int i = 0; i < furnitureList.size() - 1; i++) {
								for (int j = 0; j < furnitureList.size() - i - 1; j++) {
									if (furnitureList.get(j).getPrice() > furnitureList.get(j + 1).getPrice()) {
										Furniture temp = furnitureList.get(j);
										furnitureList.set(j, furnitureList.get(j + 1));
										furnitureList.set(j + 1, temp);
									}
								}
							}

						System.out.println("Сортировка выполнена.");
						break;

					case "6":
						// Завершение работы программы
						work = false;
						System.out.println("Завершение программы.");
						break;

					default:
						System.out.println("Неверный пункт меню.");
				}
			}
		} catch (Exception e) {
			Exception suppressed = new Exception("Подавленная ошибка");
			e.addSuppressed(suppressed);

			System.out.println("Критическая ошибка: " + e.getMessage());
		}
	}
}