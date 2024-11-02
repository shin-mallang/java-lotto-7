package lotto;

import java.util.Set;

public class Lotto {

    public static final int MIN_NUMBER_INCLUDE = 1;
    public static final int MAX_NUMBER_INCLUDE = 45;

    private final Set<Integer> numbers;

    public Lotto(Set<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(Set<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복 없는 6개의 숫자여야 합니다.");
        }
        for (Integer number : numbers) {
            if (number < MIN_NUMBER_INCLUDE || MAX_NUMBER_INCLUDE < number) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }
}
