package lotto;

import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int MIN_NUMBER_INCLUDE = 1;
    public static final int MAX_NUMBER_INCLUDE = 45;

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(Set<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복 없는 6개의 숫자여야 합니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public Set<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet());
    }
}
