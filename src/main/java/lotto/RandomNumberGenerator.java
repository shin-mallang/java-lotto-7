package lotto;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator {

    public int generate(int startInclude, int endInclude) {
        return Randoms.pickNumberInRange(startInclude, endInclude);
    }
}
