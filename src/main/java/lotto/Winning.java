package lotto;

import java.util.Arrays;

public enum Winning {

    FIRST(6, 2_000_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }
    },
    SECOND(5, 30_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount && bonusMatch;
        }
    },
    THIRD(5, 1_500_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount && !bonusMatch;
        }
    },
    FOURTH(4, 50_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }
    },
    FIFTH(3, 5_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }
    },
    NONE(0, 0) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return true;
        }
    };

    private final int matchCount;
    private final int winningAmount;

    Winning(int matchCount, int winningAmount) {
        this.matchCount = matchCount;
        this.winningAmount = winningAmount;
    }

    public abstract boolean match(int matchCount, boolean bonusMatch);

    public static Winning fromMatchCountAndBonusMatch(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(it -> it.match(matchCount, bonusMatch))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unreachable"));
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
