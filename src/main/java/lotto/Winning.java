package lotto;

public enum Winning {

    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int winningAmount;

    Winning(int matchCount, boolean bonusMatch, int winningAmount) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.winningAmount = winningAmount;
    }

    public static Winning fromMatchCountAndBonusMatch(int matchCount, boolean bonusMatch) {
        for (Winning value : values()) {
            if (value.bonusMatch && !bonusMatch) {
                continue;
            }
            if (value.matchCount == matchCount) {
                return value;
            }
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
