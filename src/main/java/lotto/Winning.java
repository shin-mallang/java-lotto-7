package lotto;

import java.util.Arrays;

public enum Winning {

    FIRST(2_000_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 6;
        }
    },
    SECOND(30_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 5 && bonusMatch;
        }
    },
    THIRD(1_500_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 5 && !bonusMatch;
        }
    },
    FOURTH(50_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 4;
        }
    },
    FIFTH(5_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 3;
        }
    },
    NONE(0) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return true;
        }
    };

    private final int winningAmount;

    Winning(int winningAmount) {
        this.winningAmount = winningAmount;
    }

    public abstract boolean match(int matchCount, boolean bonusMatch);

    public static Winning fromMatchCountAndBonusMatch(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(it -> it.match(matchCount, bonusMatch))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unreachable"));
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
