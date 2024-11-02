package lotto;

import java.util.HashMap;
import java.util.Map;

public class Winnings {

    private final Lottos lottos;
    private final Map<Winning, Integer> winnings = new HashMap<>();
    private double rateOfReturn;

    public Winnings(Lottos lottos) {
        this.lottos = lottos;
    }

    public void matchAll(WinningNumbers winningNumbers) {
        winnings.clear();
        for (Lotto lotto : lottos.getLottos()) {
            Winning winning = winningNumbers.match(lotto);
            int count = getCount(winning);
            winnings.put(winning, count + 1);
        }
        this.rateOfReturn = calculateRateOfReturn();
    }

    public int getCount(Winning winning) {
        return winnings.getOrDefault(winning, 0);
    }

    private double calculateRateOfReturn() {
        int totalAmount = 0;
        for (Winning winning : winnings.keySet()) {
            int count = getCount(winning);
            totalAmount += winning.getWinningAmount() * count;
        }
        return (double) totalAmount / lottos.getPurchasePrice();
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
