package lotto;

import java.util.Map;
import java.util.Objects;

public class LottoNumber {

    private static final int MIN_NUMBER_INCLUDE = 1;
    private static final int MAX_NUMBER_INCLUDE = 45;

    private final int number;

    public LottoNumber(String number) {
        this(parseInt(number));
    }

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 번호는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    public static LottoNumber generate(RandomNumberGenerator generator) {
        int generate = generator.generate(MIN_NUMBER_INCLUDE, MAX_NUMBER_INCLUDE);
        return new LottoNumber(generate);
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER_INCLUDE || MAX_NUMBER_INCLUDE < number) {
            throw new IllegalArgumentException("[ERROR] 번호는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumber());
    }
}
