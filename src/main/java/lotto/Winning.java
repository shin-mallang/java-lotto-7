package lotto;

import java.util.Arrays;

public enum Winning {

    FIRST(6, 2_000_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }

        @Override
        public String createMessage(int count) {
            return "6개 일치 (2,000,000,000원) - %d개".formatted(count);
        }
    },
    SECOND(5, 30_000_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount && bonusMatch;
        }

        @Override
        public String createMessage(int count) {
            return "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개".formatted(count);
        }
    },
    THIRD(5, 1_500_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount && !bonusMatch;
        }

        @Override
        public String createMessage(int count) {
            return "5개 일치 (1,500,000원) - %d개".formatted(count);
        }
    },
    FOURTH(4, 50_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }

        @Override
        public String createMessage(int count) {
            return "4개 일치 (50,000원) - %d개".formatted(count);
        }
    },
    FIFTH(3, 5_000) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return getMatchCount() == matchCount;
        }

        @Override
        public String createMessage(int count) {
            return "3개 일치 (5,000원) - %d개".formatted(count);
        }
    },
    NONE(0, 0) {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return true;
        }

        @Override
        public String createMessage(int count) {
            return "";
        }
    };

    private final int matchCount;
    private final int winningAmount;

    Winning(int matchCount, int winningAmount) {
        this.matchCount = matchCount;
        this.winningAmount = winningAmount;
    }

    public abstract boolean match(int matchCount, boolean bonusMatch);

    public abstract String createMessage(int count);

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
