package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, val) -> acc * 10 + val);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return integers.stream()
                .filter(n -> (sum % 2 == 0 && n % 2 == 0) || (sum % 2 != 0 && n % 2 != 0))
                .collect(Collectors.toList());
    }
}
