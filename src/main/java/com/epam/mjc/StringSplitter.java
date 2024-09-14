package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        if (source == null || delimiters == null || delimiters.isEmpty()) {
            return new ArrayList<>();  // Возвращаем пустой список, если нет строки или разделителей
        }

        // Строим регулярное выражение для всех разделителей, экранируем специальные символы
        StringBuilder regex = new StringBuilder();
        for (String delimiter : delimiters) {
            if (regex.length() > 0) {
                regex.append("|");  // Добавляем разделение
            }
            regex.append("\\Q").append(delimiter).append("\\E");  // Экранируем разделитель
        }

        // Разделяем строку по регулярному выражению
        String[] parts = source.split(regex.toString());

        // Преобразуем массив строк в список
        List<String> result = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {  // Добавляем только непустые строки
                result.add(part);
            }
        }

        return result;
    }
}
