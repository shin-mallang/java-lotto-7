package lotto;

import static lotto.Lotto.MAX_NUMBER_INCLUDE;
import static lotto.Lotto.MIN_NUMBER_INCLUDE;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {

    private final Set<Integer> numbers = new HashSet<>();
    private final int bonusNumber;

    public WinningNumbers(Set<Integer> numbers, int bonusNumber) {
        validateNumbers(numbers);
        validateBonusNumber(bonusNumber);
        numbers.addAll(numbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateNumbers(Set<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복 없는 6개의 숫자여야 합니다.");
        }
        for (Integer number : numbers) {
            if (number < MIN_NUMBER_INCLUDE || MAX_NUMBER_INCLUDE < number) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER_INCLUDE || MAX_NUMBER_INCLUDE < bonusNumber) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }
}
