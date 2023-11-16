package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){

        // Создаем список массивов строк
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1", "", "null", "553зерно29", "22"});
        rows.add(new String[]{"2", "345", "т", "554зерно29", ""});
        rows.add(new String[]{"3", "589", "т", "", "54"});
        rows.add(new String[]{"4", "null", "т", "бананы", "null"});
        rows.add(new String[]{"5", "345", "", "null", "89"});
        rows.add(new String[]{"6", "345", "т", "554зерно28", ""});
        rows.add(new String[]{"7", "345", "т", "553зерна29", ""});

        // Получаем экземпляр класса
        Task1Impl task1Impl = Task1Impl.getInstance();

        // Вызываем метод для сортировки по четвертой колонке
        task1Impl.sort(rows, 3);

        // Выводим отсортированные строки
        for (String[] line : rows) {
            System.out.println(Arrays.toString(line));
        }

    }
}
