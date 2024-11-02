package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lottos {

    private static final int PRICE_PER_LOTTO = 1_000;

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    public static Lottos purchase(int amount, RandomNumberGenerator generator) {
        validateAmount(amount);
        int count = amount / PRICE_PER_LOTTO;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(generateLottoNumbers(generator)));
        }
        return new Lottos(lottos);
    }

    private static Set<LottoNumber> generateLottoNumbers(RandomNumberGenerator generator) {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() != 6) {
            LottoNumber generate = LottoNumber.generate(generator);
            numbers.add(generate);
        }
        return numbers;
    }

    private static void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }
        if (amount % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public int getPurchasePrice() {
        return lottos.size() * PRICE_PER_LOTTO;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
