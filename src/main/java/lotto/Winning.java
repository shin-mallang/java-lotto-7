package lotto;

import java.util.Arrays;

public enum Winning {

    FIRST {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 6;
        }
    },
    SECOND {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 5 && bonusMatch;
        }
    },
    THIRD {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 5 && !bonusMatch;
        }
    },
    FOURTH {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 4;
        }
    },
    FIFTH {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return matchCount == 3;
        }
    },
    NONE {
        @Override
        public boolean match(int matchCount, boolean bonusMatch) {
            return true;
        }
    };

    public abstract boolean match(int matchCount, boolean bonusMatch);

    public static Winning fromMatchCountAndBonusMatch(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(it -> it.match(matchCount, bonusMatch))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unreachable"));

    }
}
