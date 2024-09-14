package com.epam.mjc;

import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;

public class StringSplitterTest {
    private static final int RANDOM_CASES_COUNT = 10;
    private StringSplitter splitter = new StringSplitter();
    private Random random = new Random();

    private List<String> allTokens = List.of("q", "w", "e", "r", "t", "y", "u",
            "i", "o", "p", "a", "s", "d", "f", "g", "h",
            "j", "k", "l", "z", "x", "c", "v", "b", "n",
            "m", "1", "2", "3", "4", "5", "6", "7");

    @Test
    public void testSplitByDelimiters() {
        List<String> result = splitter.splitByDelimiters("qw3e1rt4yu2i3opa1sd1fg2hj4kl", List.of("1", "2", "3"));
        assertEquals(List.of("qw", "e", "rt4yu", "i", "opa", "sd", "fg", "hj4kl"), result);
    }

    @Test
    public void testRandomCases() {
        for (int i = 0; i < RANDOM_CASES_COUNT; i++) {
            testRandomCase(random.nextInt(5) + 5);
        }
    }

    private void testRandomCase(int delimitersNumber) {
        Set<String> delimiters = selectDelimiters(delimitersNumber);
        StringBuilder all = new StringBuilder();
        List<String> expectedResult = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            String token = allTokens.get(random.nextInt(allTokens.size()));
            all.append(token);
            if (delimiters.contains(token)) {
                if (current.length() > 0) {
                    expectedResult.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(token);
            }
        }
        if (current.length() > 0) {
            expectedResult.add(current.toString());
        }
        
        List<String> actualResult = splitter.splitByDelimiters(all.toString(), delimiters);
        assertEquals(expectedResult, actualResult);
    }

    private Set<String> selectDelimiters(int delimitersNumber) {
        List<String> copy = new ArrayList<>(allTokens);
        Collections.shuffle(copy);
        return new HashSet<>(copy.subList(0, delimitersNumber));
    }
}
