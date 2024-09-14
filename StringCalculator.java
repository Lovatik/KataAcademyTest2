import java.util.Scanner;

public class StringCalculator {

    // Главный метод для обработки строкового выражения
    public static String calculate(String input) throws Exception {
        input = input.trim();

        // Проверка на наличие операций сложения, вычитания, умножения и деления
        if (input.contains("+")) {
            String[] parts = input.split("\\+");
            if (parts.length != 2) throw new Exception("Некорректное выражение для сложения.");
            String str1 = extractString(parts[0]);
            String str2 = extractString(parts[1]);
            return limitLength(str1 + str2);
        } else if (input.contains("-")) {
            String[] parts = input.split("-");
            if (parts.length != 2) throw new Exception("Некорректное выражение для вычитания.");
            String str1 = extractString(parts[0]);
            String str2 = extractString(parts[1]);
            return limitLength(str1.replace(str2, ""));
        } else if (input.contains("*")) {
            String[] parts = input.split("\\*");
            if (parts.length != 2) throw new Exception("Некорректное выражение для умножения.");
            String str = extractString(parts[0]);
            int num = extractNumber(parts[1]);
            return limitLength(str.repeat(num));
        } else if (input.contains("/")) {
            String[] parts = input.split("/");
            if (parts.length != 2) throw new Exception("Некорректное выражение для деления.");
            String str = extractString(parts[0]);
            int num = extractNumber(parts[1]);
            if (num > str.length()) throw new Exception("Число слишком большое для деления строки.");
            return limitLength(str.substring(0, str.length() / num));
        } else {
            throw new Exception("Некорректное выражение. Ожидалась одна из операций: +, -, *, /.");
        }
    }

    // Метод для извлечения строки из выражения
    private static String extractString(String part) throws Exception {
        part = part.trim();
        if (!part.startsWith("\"") || !part.endsWith("\"")) {
            throw new Exception("Некорректный формат строки. Строки должны быть в кавычках.");
        }
        String str = part.substring(1, part.length() - 1);
        if (str.length() > 10) throw new Exception("Длина строки не должна превышать 10 символов.");
        return str;
    }

    // Метод для извлечения числа
    private static int extractNumber(String part) throws Exception {
        part = part.trim();
        int num;
        try {
            num = Integer.parseInt(part);
        } catch (NumberFormatException e) {
            throw new Exception("Некорректный формат числа.");
        }
        if (num < 1 || num > 10) throw new Exception("Число должно быть в диапазоне от 1 до 10.");
        return num;
    }

    // Метод для ограничения длины строки до 40 символов
    private static String limitLength(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строковое выражение:");
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}