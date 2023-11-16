package ru.tandemservice.test.task1;

import java.util.*;

/**
 * Реализация интерфейса {@link IStringRowsListSorter}.
 * <p>
 * Этот класс представляет собой синглтон, который реализует механизм "умной" сортировки строк.
 * Сортировка осуществляется по указанной колонке с учетом определенных правил.
 * </p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // Создаем приватное статическое поле для хранения единственного экземпляра класса
    private static final Task1Impl INSTANCE = new Task1Impl();

    // Приватный конструктор, чтобы предотвратить создание новых экземпляров класса
    private Task1Impl() {
    }

    /**
     * Метод для доступа к единственному экземпляру класса.
     *
     * @return Единственный экземпляр класса Task1Impl
     */
    public static Task1Impl getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для сортировки списка массивов строк по указанной колонке с использованием компаратора.
     *
     * @param rows        Список массивов строк для сортировки
     * @param columnIndex Индекс колонки, по которой нужно провести сортировку
     */
    @Override
    public synchronized void sort(final List<String[]> rows, final int columnIndex) {
        // Используем Collections.sort с нашим компаратором
        rows.sort(new CustomComparator(columnIndex));
    }

    /**
     * Компаратор для сравнения строк по заданным правилам.
     */
    static class CustomComparator implements Comparator<String[]> {
        private final int columnIndex;

        /**
         * Конструктор компаратора.
         *
         * @param columnIndex Индекс колонки, по которой нужно провести сравнение
         */
        public CustomComparator(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        /**
         * Метод сравнения строк.
         *
         * @param line1 Первая строка для сравнения
         * @param line2 Вторая строка для сравнения
         * @return Результат сравнения (-1, 0 или 1)
         */
        @Override
        public int compare(String[] line1, String[] line2) {
            String value1 = line1[columnIndex];
            String value2 = line2[columnIndex];

            // Проверяем на null и помещаем строки с null в самом верху
            if (value1.equals("null") && !value2.equals("null")) {
                return -1;
            } else if (!value1.equals("null") && value2.equals("null")) {
                return 1;
            }

            // Разбиваем строки на подстроки с использованием предложенных правил
            String[] substrings1 = splitString(value1);
            String[] substrings2 = splitString(value2);

            // Определяем минимальную длину для итерации
            int minLength = Math.min(substrings1.length, substrings2.length);

            // Сравниваем строки по подстрокам
            for (int i = 0; i < minLength; i++) {
                int result = compareSubstrings(substrings1[i], substrings2[i]);
                if (result != 0) {
                    return result;
                }
            }

            // Если строки неразличимы, возвращаем разницу по длине
            return substrings1.length - substrings2.length;
        }

        /**
         * Метод разбивки строки на подстроки.
         *
         * @param s Строка для разбивки
         * @return Массив подстрок
         */
        private String[] splitString(String s) {
            return s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        }

        /**
         * Метод сравнения подстрок.
         *
         * @param sub1 Первая подстрока для сравнения
         * @param sub2 Вторая подстрока для сравнения
         * @return Результат сравнения (-1, 0 или 1)
         */
        private int compareSubstrings(String sub1, String sub2) {
            if (sub1.matches("\\d+") && sub2.matches("\\d+")) {
                // Если обе подстроки состоят из цифр, сравниваем как целые числа
                return Integer.compare(Integer.parseInt(sub1), Integer.parseInt(sub2));
            } else {
                // В противном случае сравниваем как строки
                return sub1.compareTo(sub2);
            }
        }
    }
}
