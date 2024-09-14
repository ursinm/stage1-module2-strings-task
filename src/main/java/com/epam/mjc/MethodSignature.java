package com.epam.mjc;

import java.util.List;

public class MethodSignature {

    private String methodName;
    private List<Argument> arguments;
    private String accessModifier;
    private String returnType;

    public MethodSignature(String methodName, List<Argument> arguments, String accessModifier, String returnType) {
        this.methodName = methodName;
        this.arguments = arguments;
        this.accessModifier = accessModifier;
        this.returnType = returnType;
    }

    // Аргумент метода
    public static class Argument {
        private String type;
        private String name;

        public Argument(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

    // Геттеры
    public String getMethodName() {
        return methodName;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public String getReturnType() {
        return returnType;
    }
}
