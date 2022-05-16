import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("""
                1. Можно ввести только 2 числа от одного до десяти.
                2. Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
                Введите 2 числа:""");
        Scanner scInput = new Scanner(System.in);
        String input = scInput.nextLine();
        System.out.println(calc(input));
    }

    static String findOperator(String input) {

        String[] operators = new String[]{"-", "+", "/", "*"};
        for (String operator : operators) {
            if (input.contains(operator)) {
                return operator;
            }

        }
        return "Нужно указать тип действия: +, -, /, *. ";

    }

    public static String calc(String input) {

        String operator = findOperator(input);
        String[] numOfOperands = input.split("\\" + operator);

        if (numOfOperands.length != 2 || numOfOperands[0].isEmpty() || numOfOperands[1].isEmpty()) {
            throw new RuntimeException("Не верная запись выражения!");
        }

        String stringNum1 = numOfOperands[0].trim().toUpperCase();
        String stringNum2 = numOfOperands[1].trim().toUpperCase();
        int number1 = romanToArab(stringNum1);
        int number2 = romanToArab(stringNum2);

        if (number1 < 0 && number2 < 0) {
            if (stringNum1.matches("\\d+") && stringNum2.matches("\\d+")) {
                int arabResult = doCalculate(Integer.parseInt(stringNum1), Integer.parseInt(stringNum2), findOperator(input));
                System.out.println("Ответ в арабских: " + arabResult);
            } else {
                throw new RuntimeException("Оба числа должны быть арабскими или римскими!");
            }
        } else {
            int result = doCalculate(number1, number2, findOperator(input));
            String romanResult = arabToRoman(result);
            System.out.println("Ответ в римских: " + romanResult);
        }
        return "The end!";
    }

    static int romanToArab(String number) {
        switch (number) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }

    static int doCalculate(int number1, int number2, String input) {
        int result = 0;
        if (number1 > 0 && number1 < 11 && number2 > 0 && number2 < 11) {
            switch (input) {
                case "+":
                    return number1 + number2;
                case "-":
                    return number1 - number2;
                case "/":
                    return number1 / number2;
                case "*":
                    return number1 * number2;
            }
        } else {
            throw new RuntimeException("Введённые числа не соответствуют условиям.");
        }
        return result;
    }

    static String arabToRoman(int arabNumber) {
        String[] romanNumbers = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        if (arabNumber < 0) {
            throw new RuntimeException("В вычислениях с римскими числами ответ не может быть отрицательным!");
        } else if (arabNumber == 0) {
            throw new RuntimeException("В вычислениях с римскими числами не может быть 0, его тогда просто не существовало:)");

        }
        return romanNumbers[arabNumber];
    }
}
