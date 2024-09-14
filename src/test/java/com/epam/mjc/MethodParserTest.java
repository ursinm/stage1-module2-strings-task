package com.epam.mjc;

import org.junit.Test;
import static org.junit.Assert.*;

public class MethodParserTest {

    private MethodParser parser = new MethodParser();

    @Test
    public void testParseFunction() {
        MethodSignature signature = parser.parseFunction("private void log(String logString, LogLevel level, Context context)");
        assertEquals("private", signature.getAccessModifier());
        assertEquals("void", signature.getReturnType());
        assertEquals("log", signature.getMethodName());
        assertEquals(3, signature.getArguments().size());
        assertArgument(signature.getArguments().get(0), "String", "logString");
        assertArgument(signature.getArguments().get(1), "LogLevel", "level");
        assertArgument(signature.getArguments().get(2), "Context", "context");
    }

    @Test
    public void testParseFunctionNoAccessModifier() {
        MethodSignature signature = parser.parseFunction("String repeat(String value, int times)");
        assertNull(signature.getAccessModifier());
        assertEquals("String", signature.getReturnType());
        assertEquals("repeat", signature.getMethodName());
        assertEquals(2, signature.getArguments().size());
        assertArgument(signature.getArguments().get(0), "String", "value");
        assertArgument(signature.getArguments().get(1), "int", "times");
    }

    @Test
    public void testParseFunctionNoArguments() {
        MethodSignature signature = parser.parseFunction("public float mark()");
        assertEquals("public", signature.getAccessModifier());
        assertEquals("float", signature.getReturnType());
        assertEquals("mark", signature.getMethodName());
        assertEquals(0, signature.getArguments().size());
    }

    private void assertArgument(MethodSignature.Argument argument, String type, String name) {
        assertEquals(type, argument.getType());
        assertEquals(name, argument.getName());
    }
}
