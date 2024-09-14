package com.epam.mjc;
import java.util.ArrayList;
import java.util.List;


public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all its members into a {@link MethodSignature} object.
     * 
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        // Удалим пробелы по краям для безопасности
        signatureString = signatureString.trim();

        // Разделим строку на основную часть и параметры (часть внутри скобок)
        String methodBody = signatureString.substring(0, signatureString.indexOf('(')).trim();
        String parametersPart = signatureString.substring(signatureString.indexOf('(') + 1, signatureString.indexOf(')')).trim();

        // Разделим основную часть метода по пробелам
        String[] methodParts = methodBody.split(" ");

        String accessModifier = null;
        String returnType;
        String methodName;

        // Определим, есть ли модификатор доступа
        if (methodParts.length == 3) {
            accessModifier = methodParts[0];  // Если 3 части, первая часть - модификатор
            returnType = methodParts[1];
            methodName = methodParts[2];
        } else {
            returnType = methodParts[0];  // Если 2 части, первая часть - возвращаемый тип
            methodName = methodParts[1];
        }

        // Разберём параметры метода
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!parametersPart.isEmpty()) {
            String[] parameters = parametersPart.split(",\\s*");  // Разделяем параметры
            for (String parameter : parameters) {
                String[] paramParts = parameter.split(" ");
                String type = paramParts[0];
                String name = paramParts[1];
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        // Создаём и возвращаем объект MethodSignature
        return new MethodSignature(methodName, arguments, accessModifier, returnType);
    }
}
