package lotto;

import static lotto.Lotto.MAX_NUMBER_INCLUDE;
import static lotto.Lotto.MIN_NUMBER_INCLUDE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoSeller {

    private static final int PRICE_PER_LOTTO = 1_000;

    public List<Lotto> sell(int amount) {
        validateAmount(amount);
        int count = amount / PRICE_PER_LOTTO;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
        return lottos;
    }

    private Set<Integer> generateLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() != 6) {
            int number = Randoms.pickNumberInRange(MIN_NUMBER_INCLUDE, MAX_NUMBER_INCLUDE);
            numbers.add(number);
        }
        return numbers;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }
        if (amount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}
