package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoSeller {

    private static final int PRICE_PER_LOTTO = 1_000;

    public List<Lotto> sell(int amount) {
        validateAmount(amount);
        return new ArrayList<>();
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
