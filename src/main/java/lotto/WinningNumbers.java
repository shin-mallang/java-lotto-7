package lotto;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {

    private final Set<LottoNumber> numbers = new HashSet<>();
    private LottoNumber bonusNumber;

    public WinningNumbers(Set<LottoNumber> numbers) {
        validateNumbers(numbers);
        numbers.addAll(this.numbers);
    }

    private void validateNumbers(Set<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복 없는 6개의 숫자여야 합니다.");
        }
    }

    public void withBonusNumber(LottoNumber bonusNumber) {
        validateDuplicated(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicated(LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되어서는 안됩니다.");
        }
    }

    public Winning match(Lotto lotto) {
        int matchCount = 0;
        for (LottoNumber number : numbers) {
            if (lotto.contains(number)) {
                matchCount++;
            }
        }
        boolean bonusMatch = lotto.contains(bonusNumber);
        return Winning.fromMatchCountAndBonusMatch(matchCount, bonusMatch);
    }
}
