package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final int MIN_NUMBER_INCLUDE = 1;
    public static final int MAX_NUMBER_INCLUDE = 45;

    private final List<Integer> numbers;

    public Lotto(Integer... numbers) {
        this(Arrays.asList(numbers));
    }

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        for (Integer number : numbers) {
            if (number < MIN_NUMBER_INCLUDE || MAX_NUMBER_INCLUDE < number) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
            }
        }
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되어서는 안됩니다.");
        }
    }
}
