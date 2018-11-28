package utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersExpert<T extends Number & Comparable> {
    public T getMin(List<T> numbers) {
        T minNumber = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).compareTo(minNumber) < 0)
                minNumber = numbers.get(i);
        }
        return minNumber;
    }

    public T getMax(List<T> numbers) {
        T maxNumber = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).compareTo(maxNumber) > 0)
                maxNumber = numbers.get(i);
        }
        return maxNumber;
    }

    public Double getAverage(List<T> numbers) {
        BigDecimal sum = new BigDecimal(0.0);
        for (T number : numbers) {
            sum = sum.add(new BigDecimal(number.toString()));
        }
        return (sum.doubleValue() / numbers.size());
    }
}
